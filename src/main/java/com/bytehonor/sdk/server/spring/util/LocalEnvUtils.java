package com.bytehonor.sdk.server.spring.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalEnvUtils {

    private static final Logger LOG = LoggerFactory.getLogger(LocalEnvUtils.class);

    public static String localIp() {
        String localIp = "unknown";
        try {
            Enumeration<NetworkInterface> list = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (list.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) list.nextElement();
                if (ni.isLoopback() || ni.isVirtual() || !ni.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            localIp = ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("error", e);
            localIp = "unknown";
        }
        return localIp;
    }
}
