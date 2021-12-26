package at.ldnfuture.proxycommands.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import at.ldnfuture.proxycommands.main.Main;
import at.ldnfuture.proxycommands.utils.MessageUtil;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class MessageCommand extends Command {
    public MessageCommand() {
        super("msg", (String)null, new String[]{"message", "m", "t", "tell"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            if (args.length > 1) {
                ProxiedPlayer msgSender = (ProxiedPlayer)sender;
                ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(args[0]);
                if (player != null && player.getServer() != null) {
                    StringBuilder builder = new StringBuilder();

                    for(int i = 1; i < args.length; ++i) {
                        builder.append(args[i]).append(' ');
                    }

                    String msg = builder.toString();
                    MessageUtil.sendMessage(msgSender, player, msg);
                } else {
                    msgSender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
                }
            } else {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Message.Usage").replace("&", "§"));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        }

    }
}