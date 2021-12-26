package at.ldnfuture.proxycommands.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;
import at.ldnfuture.proxycommands.utils.ConfigUtil;
import at.ldnfuture.proxycommands.utils.ServerUtil;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class HubCommand extends Command {
    public HubCommand(String name) {
        super("hub", (String)null, new String[]{name});
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        } else {
            List<String> hubs = ConfigUtil.getConfig().getStringList("Hubs");
            boolean b = false;
            Iterator var6 = hubs.iterator();

            String hub2;
            while(var6.hasNext()) {
                hub2 = (String)var6.next();
                if (ServerUtil.getServer(hub2) == null) {
                    b = true;
                    return;
                }
            }

            if (b) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Hub.NeDostupnoNiOdnogo").replace("&", "§"));
                return;
            }

            if (hubs.contains(((ProxiedPlayer)sender).getServer().getInfo().getName())) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Hub.Already").replace("&", "§"));
                return;
            }

            hub2 = (String)hubs.get((new Random()).nextInt(hubs.size()));
            ((ProxiedPlayer)sender).connect(ServerUtil.getServer(hub2));
        }

    }
}
