package at.ldnfuture.proxycommands.main;

import java.util.Iterator;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import at.ldnfuture.proxycommands.commands.AlertCommand;
import at.ldnfuture.proxycommands.commands.BungeeCommand;
import at.ldnfuture.proxycommands.commands.EndCommand;
import at.ldnfuture.proxycommands.commands.FindCommand;
import at.ldnfuture.proxycommands.commands.HubCommand;
import at.ldnfuture.proxycommands.commands.IgnoreCommand;
import at.ldnfuture.proxycommands.commands.IgnoreallCommand;
import at.ldnfuture.proxycommands.commands.MessageCommand;
import at.ldnfuture.proxycommands.commands.OnlineCommand;
import at.ldnfuture.proxycommands.commands.PingCommand;
import at.ldnfuture.proxycommands.commands.ReloadCommand;
import at.ldnfuture.proxycommands.commands.ReportCommand;
import at.ldnfuture.proxycommands.commands.RulesCommand;
import at.ldnfuture.proxycommands.commands.SendCommand;
import at.ldnfuture.proxycommands.commands.ServerCommand;
import at.ldnfuture.proxycommands.commands.WhereCommand;
import at.ldnfuture.proxycommands.events.EventListener;
import at.ldnfuture.proxycommands.utils.ConfigUtil;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class Main extends Plugin {
    private static Main instance;

    public Main() {
        instance = this;
    }

    public Configuration getConfig() {
        return ConfigUtil.getConfig();
    }

    public void onEnable() {
        ConfigUtil.saveDefaultConfig();
        boolean server = this.getConfig().getBoolean("Messages.Server.Allow");
        boolean find = this.getConfig().getBoolean("Messages.Find.Allow");
        boolean ping = this.getConfig().getBoolean("Messages.Ping.Allow");
        boolean ignore = this.getConfig().getBoolean("Messages.Ignore.Allow");
        boolean ignoreall = this.getConfig().getBoolean("Messages.IgnoreAll.Allow");
        boolean message = this.getConfig().getBoolean("Messages.Message.Allow");
        boolean where = this.getConfig().getBoolean("Messages.Where.Allow");
        boolean send = this.getConfig().getBoolean("Messages.Send.Allow");
        boolean online = this.getConfig().getBoolean("Messages.Online.Allow");
        boolean alert = this.getConfig().getBoolean("Messages.Alert.Allow");
        boolean hub = this.getConfig().getBoolean("Messages.Hub.Allow");
        if (alert) {
            this.getProxy().getPluginManager().registerCommand(this, new AlertCommand());
        }

        this.getProxy().getPluginManager().registerCommand(this, new BungeeCommand());
        this.getProxy().getPluginManager().registerCommand(this, new EndCommand());
        if (find) {
            this.getProxy().getPluginManager().registerCommand(this, new FindCommand());
        }

        this.getProxy().getPluginManager().registerCommand(this, new RulesCommand());
        if (ignoreall) {
            this.getProxy().getPluginManager().registerCommand(this, new IgnoreallCommand());
        }

        if (ignore) {
            this.getProxy().getPluginManager().registerCommand(this, new IgnoreCommand());
        }

        if (message) {
            this.getProxy().getPluginManager().registerCommand(this, new MessageCommand());
        }

        if (online) {
            this.getProxy().getPluginManager().registerCommand(this, new OnlineCommand());
        }

        this.getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        if (send) {
            this.getProxy().getPluginManager().registerCommand(this, new SendCommand());
        }

        if (server) {
            this.getProxy().getPluginManager().registerCommand(this, new ServerCommand());
        }

        if (where) {
            this.getProxy().getPluginManager().registerCommand(this, new WhereCommand());
        }

        if (ping) {
            this.getProxy().getPluginManager().registerCommand(this, new PingCommand());
        }

        this.getProxy().getPluginManager().registerCommand(this, new ReloadCommand());
        if (hub) {
            Iterator var13 = ConfigUtil.getConfig().getStringList("HubAliases").iterator();

            while(var13.hasNext()) {
                String s = (String)var13.next();
                this.getProxy().getPluginManager().registerCommand(this, new HubCommand(s));
            }
        }

        this.getProxy().getPluginManager().registerListener(this, new EventListener());
    }

    public static Main getInstance() {
        return instance;
    }
}