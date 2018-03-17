package me.simonm34.skyblock.upgrades;

import me.simonm34.skycore.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class UpgradesManager {
    private List<Generator> generators;
    private List<Members> members;
    private List<Size> sizes;

    public void loadUpgrades() {
        generators = new ArrayList<>();
        members = new ArrayList<>();
        sizes = new ArrayList<>();

        FileConfiguration data = Core.getCore().getSkyblock().getFileManager().setupUpgrades();
        for (String string : data.getStringList("upgrades.generator")) {
            Double doub = Core.getCore().getUtils().parseDouble(string);
            if (doub != null)
                generators.add(new Generator(generators.size() + 1, doub));
        }
        for (String string : data.getStringList("upgrades.members")) {
            Double doub = Core.getCore().getUtils().parseDouble(string);
            if (doub != null)
                members.add(new Members(members.size() + 1, doub));
        }
        for (String string : data.getStringList("upgrades.size")) {
            Double doub = Core.getCore().getUtils().parseDouble(string);
            if (doub != null)
                sizes.add(new Size(sizes.size() + 1, doub));
        }
    }

    public Generator getGenerator(int i) {
        return generators.get(i - 1);
    }
    public Members getMember(int i) {
        return members.get(i - 1);
    }
    public Size getSize(int i) {
        return sizes.get(i - 1);
    }
}
