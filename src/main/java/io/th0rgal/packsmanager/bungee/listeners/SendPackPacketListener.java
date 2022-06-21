package io.th0rgal.packsmanager.bungee.listeners;

import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

import io.th0rgal.packsmanager.bungee.packets.SendPackPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SendPackPacketListener extends AbstractPacketListener<SendPackPacket> {

    private final Map<UUID, String> map = new HashMap<>();

    public SendPackPacketListener() {
        super(SendPackPacket.class, Direction.DOWNSTREAM, 0);
    }

    @Override
    public void packetReceive(final PacketReceiveEvent<SendPackPacket> event) {
        final SendPackPacket packet = event.packet();
        final UUID uuid = event.player().uniqueId();
        if (map.containsKey(uuid) && map.get(uuid).equals(packet.getSha1())) {
            event.cancelled(true);
            return;
        }
        map.put(uuid, packet.getSha1());
    }

    @Override
    public void packetSend(PacketSendEvent<SendPackPacket> event) {

    }

    public void removePlayer(final UUID uuid) {
        map.remove(uuid);
    }
}