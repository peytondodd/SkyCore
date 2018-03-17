package me.simonm34.skycore.rank;

import java.util.List;

public class Rank {
    private boolean isDefault;
    private String name;
    private String prefix;
    private String chatColor;
    private List<String> permissions;
    private List<Rank> children;

    public Rank(boolean isDefault, String name, String prefix, String chatColor, List<String> permissions) {
        this.isDefault = isDefault;
        this.name = name;
        this.prefix = prefix;
        this.chatColor = chatColor;
        this.permissions = permissions;
    }

    public boolean isDefault() {
        return isDefault;
    }
    public String getName() {
        return name;
    }
    public String getPrefix() {
        return prefix;
    }
    public String getChatColor() {
        return chatColor;
    }
    public List<String> getPermissions() {
        return permissions;
    }
    public List<Rank> getChildren() {
        return children;
    }

    public void setChildren(List<Rank> children) {
        this.children = children;
    }
}
