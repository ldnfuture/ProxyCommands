package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class PingCommand extends Command {
    public PingCommand() {
        super("ping", (String)null, new String[0]);
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        } else if (args.length == 0) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Ping.Ping").replace("&", "§").replace("{ping}", String.valueOf(((ProxiedPlayer)sender).getPing())));
        } else {
            if (!sender.hasPermission("bungeeel.ping.other")) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
            } else {
                if (Main.getInstance().getProxy().getPlayer(args[0]) == null) {
                    sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
                    return;
                }

                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Ping.Other").replace("&", "§").replace("{ping}", String.valueOf(Main.getInstance().getProxy().getPlayer(args[0]).getPing())).replace("{player}", args[0]));
            }

        }
    }
}
