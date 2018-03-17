package me.simonm34.skycore.utils;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.punishments.PunishType;
import me.simonm34.skycore.sender.Sender;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public String getColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    public Integer parseInteger(String string) {
        Integer i;
        try {
            i = Integer.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
        return i;
    }
    public Double parseDouble(String string) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return d;
    }
    public Material parseMaterial(String string) {
        Material m;
        try {
            m = Material.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
        return m;
    }
    public Enchantment parseEnchantment(String string) {
        Enchantment m;
        try {
            m = Enchantment.getByName(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
        return m;
    }
    public PunishType parsePunishType(String string) {
        PunishType p;
        try {
            p = PunishType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
        return p;
    }
    public String formatString(char charactor, String string) {
        List<String> strings = new ArrayList<>();
        for (String s1 : string.split(charactor + ""))
            strings.add(s1.substring(0, 1).toUpperCase() + s1.substring(1).toLowerCase());
        return String.join(" ", strings);
    }

    public String formatTime(Long lo) {
        lo = lo - System.currentTimeMillis();

        long secondsMs = 1000;
        long minutesMs = secondsMs * 60;
        long hoursMs = minutesMs * 60;
        long daysMs = hoursMs * 24;
        long weeksMs = daysMs * 7;

        long weeks = lo / weeksMs;
        lo = lo % weeksMs;
        long days = lo / daysMs;
        lo = lo % daysMs;
        long hours = lo / hoursMs;
        lo = lo % hoursMs;
        long minutes = lo / minutesMs;
        lo = lo % minutesMs;
        long seconds = lo / secondsMs;

        List<String> s = new ArrayList<>();
        if (weeks > 0)
            s.add(weeks + " week" + (weeks > 1 ? "s" : ""));
        if (weeks > 0 || days > 0)
            s.add(days + " day" + (days == 0 || days > 1 ? "s" : ""));
        if (weeks > 0 || days > 0 || hours > 0)
            s.add(hours + " hour" + (hours == 0 || hours > 1 ? "s" : ""));
        if (weeks > 0 || days > 0 || hours > 0 ||  minutes > 0)
            s.add(minutes + " minute" + (minutes == 0 || minutes > 1 ? "s" : ""));
        if (weeks > 0 || days > 0 || hours > 0 || minutes > 0 || seconds > 0)
            s.add(seconds + " second" + (seconds == 0 || seconds > 1 ? "s" : ""));

        return String.join(", ", s);
    }

    public void broadcastMsg(String string) {
        for (Sender sender : Core.getCore().getSenderManager().getSenders())
            sender.sendMsg(string);
    }
    public void broadcastRawMsg(String string) {
        for (Sender sender : Core.getCore().getSenderManager().getSenders())
            sender.sendRawMsg(string);
    }
}
