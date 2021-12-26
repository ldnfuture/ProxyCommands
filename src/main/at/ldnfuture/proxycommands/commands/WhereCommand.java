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
public class WhereCommand extends Command {
    public WhereCommand() {
        super("where", (String)null, new String[]{"whe"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        } else {
            if (!sender.hasPermission("proxy.where")) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
                return;
            }

            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Where.Where").replace("&", "§").replace("{server}", ((ProxiedPlayer)sender).getServer().getInfo().getName()));
        }

    }
}
