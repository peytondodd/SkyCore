package me.simonm34.skyblock.invite;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InviteManager {
    private List<Invite> invites = new ArrayList<>();

    public Invite getInvite(UUID uuid) {
        for (Invite invite : invites)
            if (invite.getUUID() == uuid)
                return invite;
        return null;
    }

    public void addInvite(Invite invite) {
        invites.add(invite);
    }
    public void removeInvite(Invite invite) {
        invites.remove(invite);
    }
}
