package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class SendCommand extends Command {
    public SendCommand() {
        super("send");
    }

    public void execute(CommandSender sender, String[] args) {
        String displayName = sender.getName();
        if (sender instanceof ProxiedPlayer) {
            displayName = ((ProxiedPlayer)sender).getDisplayName();
        }

        if (!sender.hasPermission("proxy.send")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else {
            if (args.length != 2) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Send.Usage").replace("&", "§"));
            } else {
                ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
                if (player != null && player.getServer() != null) {
                    if (ProxyServer.getInstance().getServerInfo(args[1]) == null) {
                        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.NoExists").replace("&", "§"));
                    } else {
                        ServerInfo server = ProxyServer.getInstance().getServerInfo(args[1]);
                        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Send.From").replace("&", "§").replace("{player}", player.getName()).replace("{server}", server.getName()));
                        player.sendMessage(Main.getInstance().getConfig().getString("Messages.Send.To").replace("&", "§").replace("{sender}", sender.getName()).replace("{server}", server.getName()));
                        player.connect(server);
                    }
                } else {
                    sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
                }
            }

        }
    }
}
