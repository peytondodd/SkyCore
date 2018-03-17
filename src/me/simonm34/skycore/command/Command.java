package me.simonm34.skycore.command;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.sender.Sender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Command {
    private String command;
    private String[] aliases;

    public Command(String command, String... aliases) {
        this.command = command;
        this.aliases = aliases;
        Core.getCore().getCommandManager().addCommand(this);
    }

    public abstract void execute(Sender sender, String cmd, String[] args);

    public String getCommand() {
        return command;
    }
    public List<String> getCommands() {
        List<String> list = new ArrayList<>();
        list.add(command);
        Collections.addAll(list, aliases);
        return list;
    }
}
