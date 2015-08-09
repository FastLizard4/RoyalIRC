package org.fastlizard4.dragonirc.irclisteners;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.KickEvent;

import org.fastlizard4.dragonirc.Config;

public class IKickListener extends ListenerAdapter {

    @Override
    public void onKick(KickEvent e) {
        if (!Config.rejoinOnKick) return;
        if (!e.getRecipient().getNick().equalsIgnoreCase(e.getBot().getNick())) return;
        try {
            Thread.sleep(Config.rejoinWaitTime * 1000L);
        } catch (InterruptedException ignored) {
        }
        e.getBot().sendIRC().joinChannel(e.getChannel().getName());
    }
}