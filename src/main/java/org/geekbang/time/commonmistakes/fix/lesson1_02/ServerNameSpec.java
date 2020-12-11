package org.geekbang.time.commonmistakes.fix.lesson1_02;

import java.util.Collections;
import java.util.List;
import javax.net.ssl.SNIServerName;

class ServerNameSpec {
    final List<SNIServerName> serverNames;

    ServerNameSpec(List serverNames) {
        this.serverNames = Collections.unmodifiableList(serverNames);
    }

    public void addServerName(SNIServerName serverName) {
        serverNames.add(serverName);
    }

    @Override
    public String toString() {
        if (serverNames == null || serverNames.isEmpty()) {
            return "<no server name indicator specified>";
        }

        StringBuilder builder = new StringBuilder(512);
        for (SNIServerName sn : serverNames) {
            builder.append(sn.toString());
            builder.append("\n");
        }

        return builder.toString();
    }

    public static void main(String[] args) {
    }
}
