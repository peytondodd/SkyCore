package me.simonm34.skycore;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.simonm34.skyblock.Skyblock;
import me.simonm34.skycore.command.CommandListener;
import me.simonm34.skycore.command.CommandManager;
import me.simonm34.skycore.commands.*;
import me.simonm34.skycore.commands.blocked.PluginCMD;
import me.simonm34.skycore.events.*;
import me.simonm34.skycore.kit.KitManager;
import me.simonm34.skycore.namestorage.NameStorage;
import me.simonm34.skycore.punishments.PunishmentManager;
import me.simonm34.skycore.rank.RankManager;
import me.simonm34.skycore.scoreboard.ScoreboardsManager;
import me.simonm34.skycore.sender.SenderManager;
import me.simonm34.skycore.settings.Settings;
import me.simonm34.skycore.shop.Shop;
import me.simonm34.skycore.user.UserFile;
import me.simonm34.skycore.user.UserManager;
import me.simonm34.skycore.utils.FileManager;
import me.simonm34.skycore.utils.Utils;
import me.simonm34.skycore.warp.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * TODO - add /condence to turn ingots etc to blocks
 * TODO - /tpa, /tpaccept, /tpdeny
 * TODO - /tp
 * TODO - Antiadvert
 * TODO - Fully test out rank system
 * TODO - Add kick event which saves data if user is kicked
 */

public class Core extends JavaPlugin {
    private static Core core;
    private WorldEditPlugin worldEdit;
    private PunishmentManager punishmentManager;
    private ScoreboardsManager scoreboardsManager;
    private FileManager fileManager;
    private RankManager rankManager;
    private CommandManager commandManager;
    private SenderManager senderManager;
    private UserManager userManager;
    private WarpManager warpManager;
    private NameStorage nameStorage;
    private KitManager kitManager;
    private Skyblock skyblock;
    private UserFile userFile;
    private Settings settings;
    private Utils utils;
    private Shop shop;

    public void onEnable() {
        loadDepends();
        loadConfigs();
        loadCommands();
        loadEvents();

        getSkyblock().onEnable();
        getSettings().setSettings();
        getUserFile().setupDir();
        getFileManager().setupRanks();
        getFileManager().setupWarps();
        getFileManager().setupNameUUIDs();
        getNameStorage().loadNameUUIDs();
        getRankManager().loadRanks();
        getWarpManager().loadWarps();
        getKitManager().loadKits();

        loadPlayers();
    }
    public void onDisable() {
        getSkyblock().onDisable();
        getWarpManager().saveWarps();
        getNameStorage().saveNameUUIDs();
        savePlayers();
    }

    private void loadDepends() {
        core = this;
        worldEdit = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        punishmentManager = new PunishmentManager();
        scoreboardsManager = new ScoreboardsManager();
        fileManager = new FileManager();
        rankManager = new RankManager();
        commandManager = new CommandManager();
        senderManager = new SenderManager();
        userManager = new UserManager();
        warpManager = new WarpManager();
        nameStorage = new NameStorage();
        kitManager = new KitManager();
        skyblock = new Skyblock();
        userFile = new UserFile();
        settings = new Settings();
        utils = new Utils();
        //shop = new Shop();
    }
    private void loadConfigs() {
        saveDefaultConfig();
        reloadConfig();
    }
    private void loadCommands() {
        new CoreCMD();
        new RankCMD();
        new AnnounceCMD();
        new SetWarpCMD();
        new DelWarpCMD();
        new ClearchatCMD();
        new ClearInventoryCMD();
        new GamemodeCMD();
        new GamemodeSurvivalCMD();
        new GamemodeCreativeCMD();
        new TimeCMD();
        new SetSpawnCMD();
        new ItemCMD();
        new PunishCMD();
        new EnchantCMD();
        new TopCMD();
        new EconomyCMD();
        new SpectateCMD();
        //new PlayerInfoCMD(); //test

        new EnderchestCMD();
        new WorkbenchCMD();
        new AnvilCMD();
        new FlyCMD();
        new GodCMD();
        new HatCMD();
        new RepairCMD();
        new RenameCMD();
        new SkullCMD();
        new NameColorCMD();

        new ShopCMD();
        new ChatCMD();
        new MessageCMD();
        new ReplyCMD();
        new WarpCMD();
        new SpawnCMD();
        new HomeCMD();
        new SetHomeCMD();
        new DelHomeCMD();
        new BalanceCMD();
        new PayCMD();
        new TpaCMD();
        new TpAcceptCMD();
        new TpDenyCMD();
        new SuicideCMD();

        new PluginCMD();
    }
    private void loadEvents() {
        new CommandListener();
        new ServerListPing();
        new PlayerLogin();
        new PlayerJoin();
        new PlayerChat();
        new PlayerDamage();
        new PlayerMove();
        new PlayerRankChange();
        new PlayerBalanceChange();
        new PlayerQuit();
        new IslandLevelChange();
        new IslandMemberChange();
        new InventoryClick();
    }
    private void loadPlayers() {
        getServer().getOnlinePlayers().forEach(player -> {
            getUserManager().loadUser(player);
            getSenderManager().loadSender(player);
        });
    }
    private void savePlayers() {
        getUserManager().getUsers().forEach(user -> getUserManager().saveUser(user.getPlayer()));
    }

    public static Core getCore() {
        return core;
    }
    public WorldEditPlugin getWorldEdit() {
        return worldEdit;
    }
    public PunishmentManager getPunishmentManager() {
        return punishmentManager;
    }
    public ScoreboardsManager getScoreboardsManager() {
        return scoreboardsManager;
    }
    public FileManager getFileManager() {
        return fileManager;
    }
    public RankManager getRankManager() {
        return rankManager;
    }
    public CommandManager getCommandManager() {
        return commandManager;
    }
    public SenderManager getSenderManager() {
        return senderManager;
    }
    public UserManager getUserManager() {
        return userManager;
    }
    public WarpManager getWarpManager() {
        return warpManager;
    }
    public NameStorage getNameStorage() {
        return nameStorage;
    }
    public KitManager getKitManager() {
        return kitManager;
    }
    public Skyblock getSkyblock() {
        return skyblock;
    }
    public UserFile getUserFile() {
        return userFile;
    }
    public Settings getSettings() {
        return settings;
    }
    public Utils getUtils() {
        return utils;
    }
    public Shop getShop() {
        return shop;
    }
}
