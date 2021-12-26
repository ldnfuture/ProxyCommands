package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import vk.me.merofunk.proxeed.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class EndCommand extends Command {
    public EndCommand() {
        super("end");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("proxy.end")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else {
            if (!sender.hasPermission("proxy.end")) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
                return;
            }

            Main.getInstance().getProxy().stop();
        }

    }
}