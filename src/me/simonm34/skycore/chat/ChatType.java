package me.simonm34.skycore.chat;

public enum ChatType {
    PUBLIC("Public"),
    STAFF("Staff"),
    TEAM("Team");

    private String name;

    ChatType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
