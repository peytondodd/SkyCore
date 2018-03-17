package me.simonm34.skycore.command;

import java.util.HashSet;
import java.util.Set;

public class CommandManager {
    private Set<Command> commands = new HashSet<>();

    public Command getCommand(String cmd) {
        for (Command command : commands)
            if (command.getCommands().contains(cmd.toLowerCase()))
                return command;
        return null;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
}
