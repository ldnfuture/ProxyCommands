package at.ldnfuture.proxycommands.commands;

import java.util.Iterator;
import net.md_5.bungee.BungeeTitle;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class AlertCommand extends Command {
    public AlertCommand() {
        super("alert", (String)null, new String[]{"bc", "broadcast"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("proxy.alert")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else {
            if (args.length == 0) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Alert.Usage").replace("&", "§"));
            } else {
                StringBuilder msg = new StringBuilder();
                String[] arrayOfString = args;
                int i = args.length;

                for(byte b = 0; b < i; ++b) {
                    String arg = arrayOfString[b];
                    msg.append(String.valueOf(String.valueOf(arg)) + " ");
                }

                Iterator var8 = Main.getInstance().getProxy().getPlayers().iterator();

                while(var8.hasNext()) {
                    ProxiedPlayer players = (ProxiedPlayer)var8.next();
                    players.sendTitle((new BungeeTitle()).title(new TextComponent(Main.getInstance().getConfig().getString("Messages.Alert.Title.header").replace("&", "§").replace("{message}", msg.toString().replace("&", "§")))).subTitle(new TextComponent(Main.getInstance().getConfig().getString("Messages.Alert.Title.footer").replace("&", "§").replace("{message}", msg.toString().replace("&", "§")))));
                    players.sendMessage(Main.getInstance().getConfig().getString("Messages.Alert.Chat").replace("&", "§").replace("{message}", msg.toString().replace("&", "§")));
                }
            }

        }
    }
}
