package at.ldnfuture.proxycommands.utils;

import net.md_5.bungee.api.config.ServerInfo;
import at.ldnfuture.proxycommands.main.Main;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 29.01.2021
 */
public final class ServerUtil {
    public ServerUtil() {
    }

    public static ServerInfo getServer(String server) {
        return Main.getInstance().getProxy().getServerInfo(server);
    }
}
