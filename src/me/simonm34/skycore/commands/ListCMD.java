package me.simonm34.skycore.commands;

import me.simonm34.skycore.command.Command;
import me.simonm34.skycore.sender.Sender;

import java.util.ArrayList;
import java.util.List;

public class ListCMD extends Command {
    public ListCMD() {
        super("list");
    }
    public void execute(Sender sender, String cmd, String[] args) {
        List<String> staff = new ArrayList<>();
        List<String> donators = new ArrayList<>();
        List<String> guests = new ArrayList<>();
    }
}
