package at.ldnfuture.proxycommands.commands;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;
import at.ldnfuture.proxycommands.utils.ConfigUtil;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("bereload", (String)null, new String[]{"greload", "preload", "berl", "grl", "prl"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("proxy.reload")) {
            sender.sendMessage(ConfigUtil.getConfig().getString("Messages.NoPermissions").replace("&", "§"));
        } else {
            sender.sendMessage(ConfigUtil.getConfig().getString("Messages.Reload").replace("&", "§"));

            try {
                Configuration config = YamlConfiguration.getProvider(YamlConfiguration.class).load(new File("config.yml"));
                YamlConfiguration.getProvider(YamlConfiguration.class).save(config, new File("config.yml"));
            } catch (IOException var4) {
                var4.printStackTrace();
            }

            ConfigUtil.reloadConfig();
            ProxyServer.getInstance().getPluginManager().enablePlugins();
        }
    }
}
