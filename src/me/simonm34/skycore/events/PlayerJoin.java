package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.namestorage.NameUUID;
import me.simonm34.skycore.user.User;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    public PlayerJoin() {
        Bukkit.getServer().getPluginManager().registerEvent(PlayerJoinEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            PlayerJoinEvent e = (PlayerJoinEvent) event;
            Player player = e.getPlayer();
            e.setJoinMessage(null);

            User user = Core.getCore().getUserManager().getUser(player.getUniqueId());
            Core.getCore().getScoreboardsManager().getNewScoreboard(user);

            if (user.hasPerm("skycore.staff"))
                Core.getCore().getUserManager().getUsers().forEach(target -> target.sendMsg(user.getRank().getPrefix() + user.getName() + " &7joined the game!"));

            for (String string : Core.getCore().getSettings().getMotd())
                user.sendRawMsg(string);

            BaseComponent[] header = new ComponentBuilder(Core.getCore().getUtils().getColor(
                    "&f-&e✦&f-&f&l»   &6&lSky&e&lTreasure   &f&l«&f-&6✦&f-" +
                            "\n")).create();
            BaseComponent[] footer = new ComponentBuilder(Core.getCore().getUtils().getColor(
                    "\n" +
                            "&6Website: &ewww.skytreasure.net" +
                            "\n" +
                            "&6You are playing on &e" + player.getAddress().getHostName() + "&6!")).create();
            player.setPlayerListHeaderFooter(header, footer);
            player.setPlayerListName(Core.getCore().getUtils().getColor(user.getRank().getPrefix() + user.getRank().getChatColor() + user.getName()));

            loadNameStorage(user);
        }, Core.getCore());
    }

    private void loadNameStorage(User user) {
        if (Core.getCore().getNameStorage().getName(user.getUUID()) == null) {
            Core.getCore().getNameStorage().addName(new NameUUID(user.getUUID(), user.getName()));
            return;
        }
        Core.getCore().getNameStorage().getName(user.getUUID()).setName(user.getName());
    }
}
