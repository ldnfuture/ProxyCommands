package at.ldnfuture.proxycommands.commands;

import java.util.Iterator;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class BungeeCommand extends Command {
    public BungeeCommand() {
        super("gplugins", (String)null, new String[]{"gpl", "bpl", "bplugins", "Bungee"});
    }

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("(ProxyEditor created by merofunk)");
        StringBuilder builder = new StringBuilder();
        builder.append("§ePlugins §7(").append(ProxyServer.getInstance().getPluginManager().getPlugins().size()).append(")§e: §a");
        Iterator var5 = ProxyServer.getInstance().getPluginManager().getPlugins().iterator();

        while(var5.hasNext()) {
            Plugin plugin = (Plugin)var5.next();
            builder.append(plugin.getDataFolder().getName()).append("§f, §a");
        }

        sender.sendMessage(builder.toString());
    }
}
