package io.th0rgal.packsmanager;

import de.exceptionflug.protocolize.api.protocol.ProtocolAPI;
import net.md_5.bungee.api.plugin.Plugin;

public class PacksManagerPlugin extends Plugin {

    @Override
    public void onEnable() {
        ProtocolAPI.getEventManager().registerListener(new PacksListeners());
    }

}
