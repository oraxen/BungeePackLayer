package io.th0rgal.packsmanager.bungee;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;

import io.th0rgal.packsmanager.bungee.listeners.PlayerDisconnectListener;
import io.th0rgal.packsmanager.bungee.listeners.ResourcePackSendPacketListener;
import io.th0rgal.packsmanager.bungee.packets.ResourcePackSendPacket;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeePackLayerBungeePlugin extends Plugin {

    private static BungeePackLayerBungeePlugin INSTANCE;
    private ResourcePackSendPacketListener packsListener;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Protocolize.protocolRegistration().registerPacket(
                ResourcePackSendPacket.MAPPINGS,
                Protocol.PLAY,
                PacketDirection.CLIENTBOUND,
                ResourcePackSendPacket.class);
        this.packsListener = new ResourcePackSendPacketListener();
        Protocolize.listenerProvider().registerListener(packsListener);
        this.getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener());
    }

    public static BungeePackLayerBungeePlugin getINSTANCE() {
        return INSTANCE;
    }

    public ResourcePackSendPacketListener getPacksListener() {
        return this.packsListener;
    }
}
