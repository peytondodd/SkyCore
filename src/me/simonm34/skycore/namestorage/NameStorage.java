package me.simonm34.skycore.namestorage;

import me.simonm34.skycore.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NameStorage {
    private List<NameUUID> nameUUIDS;

    public void loadNameUUIDs() {
        nameUUIDS = new ArrayList<>();

        FileConfiguration data = Core.getCore().getFileManager().setupNameUUIDs();
        for (String string : data.getStringList("namestorage")) {
            String[] strings = string.split(":");
            UUID uuid = UUID.fromString(strings[0]);
            String name = strings[1];
            nameUUIDS.add(new NameUUID(uuid, name));
        }
    }
    public void saveNameUUIDs() {
        FileConfiguration data = Core.getCore().getFileManager().getNameUUIDs();
        List<String> nameUUIDsStrings = new ArrayList<>();
        for (NameUUID nameUUID : nameUUIDS)
            nameUUIDsStrings.add(nameUUID.getUUID() + ":" + data.getName());

        data.set("namestorage", nameUUIDsStrings);
        Core.getCore().getFileManager().saveNameUUIDs();
    }

    public NameUUID getName(String string) {
        for (NameUUID nameUUID : nameUUIDS)
            if (nameUUID.getName().equalsIgnoreCase(string))
                return nameUUID;
        return null;
    }
    public NameUUID getName(UUID uuid) {
        for (NameUUID nameUUID : nameUUIDS)
            if (nameUUID.getUUID() == uuid)
                return nameUUID;
        return null;
    }

    public void addName(NameUUID nameUUID) {
        nameUUIDS.add(nameUUID);
    }
}
