package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;
import at.ldnfuture.proxycommands.utils.ServerUtil;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class ServerCommand extends Command {
    public ServerCommand() {
        super("server", (String)null, new String[]{"serv"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        } else if (!sender.hasPermission("proxy.server")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else if (args.length == 0) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.Usage").replace("&", "§"));
        } else if (ProxyServer.getInstance().getServerInfo(args[0]) == null) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.NoExists").replace("&", "§").replace("{server}", args[0]));
        } else if (((ProxiedPlayer)sender).getServer().getInfo().getName().toLowerCase().equals(args[0].toLowerCase())) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.AlreadyConnected").replace("&", "§").replace("{server}", args[0]));
        } else {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.Server").replace("&", "§").replace("{server}", args[0]));
            ((ProxiedPlayer)sender).connect(ServerUtil.getServer(args[0]));
        }
    }
}
