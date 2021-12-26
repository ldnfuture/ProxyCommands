package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class FindCommand extends Command {
    public FindCommand() {
        super("find", (String)null, new String[]{"search"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("proxy.find")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else {
            if (args.length == 0) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Find.Usage").replace("&", "§"));
            } else if (Main.getInstance().getProxy().getPlayer(args[0]) != null) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Find.Find").replace("&", "§").replace("{player}", args[0]).replace("{server}", Main.getInstance().getProxy().getPlayer(args[0]).getServer().getInfo().getName()));
            } else {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
            }

        }
    }
}
