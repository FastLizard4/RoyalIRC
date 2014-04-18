package org.royaldev.royalirc;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;

public class Config {

    private final RoyalIRC plugin;

    public Config(RoyalIRC instance) {
        plugin = instance;
        File config = new File(plugin.getDataFolder(), "config.yml");
        if (!config.exists()) {
            if (!config.getParentFile().mkdirs()) plugin.getLogger().warning("Could not create config.yml directory.");
            plugin.saveDefaultConfig();
        }
        reloadConfiguration();
    }

    public void reloadConfiguration() {
        plugin.reloadConfig();
        FileConfiguration c = plugin.getConfig();

        itbMessage = RUtils.colorize(c.getString("messages.itb.message"));
        itbAction = RUtils.colorize(c.getString("messages.itb.action"));
        itbJoin = RUtils.colorize(c.getString("messages.itb.join"));
        itbPart = RUtils.colorize(c.getString("messages.itb.part"));
        itbQuit = RUtils.colorize(c.getString("messages.itb.quit"));
        itbKick = RUtils.colorize(c.getString("messages.itb.kick"));
        itbNick = RUtils.colorize(c.getString("messages.itb.nick"));

        btiMessage = RUtils.colorize(c.getString("messages.bti.message"));
        btiAction = RUtils.colorize(c.getString("messages.bti.action"));
        btiLogin = RUtils.colorize(c.getString("messages.bti.login"));
        btiQuit = RUtils.colorize(c.getString("messages.bti.quit"));
        btiKick = RUtils.colorize(c.getString("messages.bti.kick"));
        btiSay = RUtils.colorize(c.getString("messages.bti.say"));
        btiDeath = RUtils.colorize(c.getString("messages.bti.death"));

        itiMessage = c.getString("messages.iti.message");
        itiAction = c.getString("messages.iti.action");
        itiJoin = c.getString("messages.iti.join");
        itiPart = c.getString("messages.iti.part");
        itiQuit = c.getString("messages.iti.quit");
        itiKick = c.getString("messages.iti.kick");

        btuMessage = c.getString("messages.btu.message");
        btuConfirm = RUtils.colorize(c.getString("messages.btu.confirm"));

        ituMessage = RUtils.colorize(c.getString("messages.itu.message"));

        defaultReason = c.getString("messages.default_reason");

        actionAliases = c.getStringList("commands.bukkit.action");
        admins = c.getStringList("settings.auth.admins");
        sayAliases = c.getStringList("commands.bukkit.say");
        mods = c.getStringList("settings.auth.mods");

        commentChar = c.getString("settings.comment_character").charAt(0);
        fantasyChar = c.getString("settings.fantasy_character").charAt(0);

        linkChannels = c.getBoolean("settings.link_channels");
        allowColors = c.getBoolean("settings.colors.allow_colors");
        parseMinecraftColors = c.getBoolean("settings.colors.parse_minecraft_colors");
        parseIRCToMinecraftColors = c.getBoolean("settings.colors.parse_irc_minecraft_colors");
        defaultConfig = c.getBoolean("default_config");
        rejoinOnKick = c.getBoolean("settings.on_kick.rejoin");
        useSyncChat = c.getBoolean("settings.use_sync_chat");
        reportPlayerDeaths = c.getBoolean("settings.report_player_deaths");

        rejoinWaitTime = c.getInt("settings.on_kick.time");
    }

    public static String itbMessage;
    public static String itbAction;
    public static String itbJoin;
    public static String itbPart;
    public static String itbQuit;
    public static String itbKick;
    public static String itbNick;

    public static String btiMessage;
    public static String btiAction;
    public static String btiLogin;
    public static String btiQuit;
    public static String btiKick;
    public static String btiSay;
    public static String btiDeath;

    public static String itiMessage;
    public static String itiAction;
    public static String itiJoin;
    public static String itiPart;
    public static String itiQuit;
    public static String itiKick;

    public static String btuMessage;
    public static String btuConfirm;

    public static String ituMessage;

    public static String defaultReason;

    public static List<String> actionAliases;
    public static List<String> admins;
    public static List<String> sayAliases;
    public static List<String> mods;

    public static char commentChar;
    public static char fantasyChar;

    public static boolean linkChannels;
    public static boolean allowColors;
    public static boolean parseMinecraftColors;
    public static boolean parseIRCToMinecraftColors;
    public static boolean defaultConfig;
    public static boolean rejoinOnKick;
    public static boolean useSyncChat;
    public static boolean reportPlayerDeaths;

    public static int rejoinWaitTime;

}

