package me.simonm34.skyblock.commands;

import me.simonm34.skyblock.islandcommands.*;
import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;

public class IslandCMD extends Command {
    public IslandCMD() {
        super("island", "is");
    }

    private IslandCommand createCMD = new IslandCreateCMD();
    private IslandCommand goCMD = new IslandGoCMD();
    private IslandCommand inviteCMD = new IslandInviteCMD();
    private IslandCommand acceptCMD = new IslandAcceptCMD();
    private IslandCommand denyCMD = new IslandDenyCMD();
    private IslandCommand kickCMD = new IslandKickCMD();
    private IslandCommand lockCMD = new IslandLockCMD();
    private IslandCommand coopCMD = new IslandCoopCMD();
    private IslandCommand uncoopCMD = new IslandUncoopCMD();
    private IslandCommand banCMD = new IslandBanCMD();
    private IslandCommand unbanCMD = new IslandUnbanCMD();
    private IslandCommand makeLeaderCMD = new IslandMakeLeaderCMD();
    private IslandCommand deleteCMD = new IslandDeleteCMD();
    private IslandCommand expelCMD = new IslandExpelCMD();
    private IslandCommand setSpawnCMD = new IslandSetSpawnCMD();
    private IslandCommand warpCMD = new IslandWarpCMD();
    private IslandCommand teamCMD = new IslandTeamCMD();
    private IslandCommand teamChatCMD = new IslandTeamChatCMD();
    private IslandCommand upgradesCMD = new IslandUpgradesCMD();

    public void execute(Sender sender, String cmd, String[] args) {
        if (sender instanceof User) {
            User user = (User) sender;
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "create":
                        createCMD.execute(user, cmd, args);
                        return;
                    case "go":
                        goCMD.execute(user, cmd, args);
                        return;
                    case "invite":
                        inviteCMD.execute(user, cmd, args);
                        return;
                    case "accept":
                        acceptCMD.execute(user, cmd, args);
                        return;
                    case "deny":
                        denyCMD.execute(user, cmd, args);
                        return;
                    case "kick":
                        kickCMD.execute(user, cmd, args);
                        return;
                    case "lock":
                        lockCMD.execute(user, cmd, args);
                        return;
                    case "coop":
                        coopCMD.execute(user, cmd, args);
                        return;
                    case "uncoop":
                        uncoopCMD.execute(user, cmd, args);
                        return;
                    case "ban":
                        banCMD.execute(user, cmd, args);
                        return;
                    case "unban":
                        unbanCMD.execute(user, cmd, args);
                        return;
                    case "makeleader":
                        makeLeaderCMD.execute(user, cmd, args);
                        return;
                    case "delete":
                        deleteCMD.execute(user, cmd, args);
                        return;
                    case "expel":
                        expelCMD.execute(user, cmd, args);
                        return;
                    case "setspawn":
                        setSpawnCMD.execute(user, cmd, args);
                        return;
                    case "warp":
                        warpCMD.execute(user, cmd, args);
                        return;
                    case "team":
                        teamCMD.execute(user, cmd, args);
                        return;
                    case "chat":
                    case "teamchat":
                        teamChatCMD.execute(user, cmd, args);
                        return;
                    case "upgrade":
                    case "upgrades":
                        upgradesCMD.execute(user, cmd, args);
                        return;
                }
            }
            //open gui
            //user.sendMsg("&7Opened your &eIsland &7menu!");
            user.sendUsage(cmd, "(subcommand)");
            return;
        }
        sender.sendMsg("&cThis can only be executed by a player!");
    }
}
