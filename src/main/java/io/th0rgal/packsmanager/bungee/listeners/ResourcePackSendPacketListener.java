package io.th0rgal.packsmanager.bungee.listeners;

import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

import io.th0rgal.packsmanager.bungee.packets.ResourcePackSendPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourcePackSendPacketListener extends AbstractPacketListener<ResourcePackSendPacket> {

    private final Map<UUID, String> map = new HashMap<>();

    public ResourcePackSendPacketListener() {
        super(ResourcePackSendPacket.class, Direction.DOWNSTREAM, 0);
    }

    @Override
    public void packetReceive(final PacketReceiveEvent<ResourcePackSendPacket> event) {
        final ResourcePackSendPacket packet = event.packet();
        final UUID uuid = event.player().uniqueId();
        if (map.containsKey(uuid) && map.get(uuid).equals(packet.getSha1())) {
            event.cancelled(true);
            return;
        }
        map.put(uuid, packet.getSha1());
    }

    @Override
    public void packetSend(PacketSendEvent<ResourcePackSendPacket> event) {

    }

    public void removePlayer(final UUID uuid) {
        map.remove(uuid);
    }
}