package me.simonm34.skycore.sender;

import net.md_5.bungee.api.chat.BaseComponent;

public interface Sender {
    String getName();
    boolean hasPerm(String string);

    void sendMsg(String string);
    void sendMsg(BaseComponent[] string);
    void sendRawMsg(String string);
    void sendUsage(String cmd, String usage);
}
