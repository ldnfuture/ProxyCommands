package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class ReportCommand extends Command {
    public ReportCommand() {
        super("report", (String)null, new String[0]);
    }

    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("proxy.report")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Report").replace("&", "§"));
        }
    }
}
