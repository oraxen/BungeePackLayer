package io.th0rgal.packsmanager;

import de.exceptionflug.protocolize.api.protocol.ProtocolAPI;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;

public class BungeePacksLayerPlugin extends Plugin {

    @Override
    public void onEnable() {
        ProtocolAPI.getPacketRegistration().registerPacket(
                Protocol.GAME,
                ProtocolConstants.Direction.TO_CLIENT,
                SendPackPacket.class,
                SendPackPacket.MAPPING);
        PacksListeners listeners = new PacksListeners();
        ProtocolAPI.getEventManager().registerListener(listeners);
        this.getProxy().getPluginManager().registerListener(this, listeners);
    }

}
