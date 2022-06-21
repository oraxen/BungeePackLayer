package io.th0rgal.packsmanager.bungee;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;

import io.th0rgal.packsmanager.bungee.listeners.PlayerDisconnectListener;
import io.th0rgal.packsmanager.bungee.listeners.SendPackPacketListener;
import io.th0rgal.packsmanager.bungee.packets.SendPackPacket;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeePackLayerBungeePlugin extends Plugin {

    private static BungeePackLayerBungeePlugin INSTANCE;
    private SendPackPacketListener packsListener;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Protocolize.protocolRegistration().registerPacket(
                SendPackPacket.MAPPINGS,
                Protocol.PLAY,
                PacketDirection.CLIENTBOUND,
                SendPackPacket.class);
        this.packsListener = new SendPackPacketListener();
        Protocolize.listenerProvider().registerListener(packsListener);
        this.getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener());
    }

    public static BungeePackLayerBungeePlugin getINSTANCE() {
        return INSTANCE;
    }

    public SendPackPacketListener getPacksListener() {
        return this.packsListener;
    }
}
