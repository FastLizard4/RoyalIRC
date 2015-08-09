package org.fastlizard4.dragonirc.bukkitlistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import org.fastlizard4.dragonirc.Config;
import org.fastlizard4.dragonirc.DragonIRC;

public class BChatListener implements Listener {

    private final DragonIRC plugin;

    public BChatListener(DragonIRC instance) {
        plugin = instance;
    }

    private String replaceVars(PlayerEvent e, String s) {
        s = s.replace("{name}", e.getPlayer().getName());
        return s;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent e) {
        if (Config.useSyncChat) return;
        if (e.isCancelled() || e.getRecipients().isEmpty()) return;
        String message = Config.btiMessage;
        message = replaceVars(e, message);
        message = message.replace("{message}", e.getMessage());
        plugin.bh.sendMessage(message);
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.MONITOR)
    public void syncOnChat(PlayerChatEvent e) {
        if (!Config.useSyncChat) return;
        if (e.isCancelled() || e.getRecipients().isEmpty()) return;
        String message = Config.btiMessage;
        message = replaceVars(e, message);
        message = message.replace("{message}", e.getMessage());
        plugin.bh.sendMessage(message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAction(PlayerCommandPreprocessEvent e) {
        if (e.isCancelled()) return;
        boolean isAction = false;
        String commandUsed = "";
        for (String start : Config.actionAliases) {
            if (!e.getMessage().startsWith(start)) continue;
            isAction = true;
            commandUsed = start;
            break;
        }
        if (!isAction) return;
        String message = Config.btiAction;
        message = replaceVars(e, message);
        message = message.replace("{message}", e.getMessage().substring(commandUsed.length()));
        plugin.bh.sendMessage(message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSay(PlayerCommandPreprocessEvent e) {
        boolean isAction = false;
        String commandUsed = "";
        for (String start : Config.sayAliases) {
            if (!e.getMessage().startsWith(start)) continue;
            commandUsed = start;
            isAction = true;
            break;
        }
        if (!isAction) return;
        String message = Config.btiSay;
        message = replaceVars(e, message);
        message = message.replace("{message}", e.getMessage().substring(commandUsed.length()));
        plugin.bh.sendMessage(message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e) { // login too early for vanish
        String message = Config.btiLogin;
        message = replaceVars(e, message);
        plugin.bh.sendMessage(message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e) {
        String message = Config.btiQuit;
        message = replaceVars(e, message);
        plugin.bh.sendMessage(message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onKick(PlayerKickEvent e) {
        String message = Config.btiKick;
        final String reason = (e.getReason().isEmpty()) ? Config.defaultReason : e.getReason();
        message = replaceVars(e, message);
        message = message.replace("{message}", reason.replaceAll("\\r?\\n", " "));
        plugin.bh.sendMessage(message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDeath(PlayerDeathEvent e) {
        if (!Config.reportPlayerDeaths) return;
        String message = Config.btiDeath;
        message = message.replace("{name}", e.getEntity().getDisplayName());
        message = message.replace("{message}", e.getDeathMessage());
        plugin.bh.sendMessage(message);
    }

}