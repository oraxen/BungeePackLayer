package io.th0rgal.packsmanager.velocity.listeners;

import com.velocitypowered.proxy.protocol.packet.ResourcePackRequest;

import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourcePackRequestListener extends AbstractPacketListener<ResourcePackRequest> {

    private final Map<UUID, String> map = new HashMap<>();

    public ResourcePackRequestListener() {
        super(ResourcePackRequest.class, Direction.DOWNSTREAM, 0);
    }

    @Override
    public void packetReceive(PacketReceiveEvent<ResourcePackRequest> event) {
        final ResourcePackRequest resourcePackRequest = event.packet();
        final UUID uuid = event.player().uniqueId();
        if (map.containsKey(uuid) && map.get(uuid).equals(resourcePackRequest.getHash())) {
            event.cancelled(true);
            return;
        }
        map.put(uuid, resourcePackRequest.getHash());
    }

    @Override
    public void packetSend(PacketSendEvent<ResourcePackRequest> event) {

    }

    public void removePlayer(final UUID uuid) {
        map.remove(uuid);
    }
}
