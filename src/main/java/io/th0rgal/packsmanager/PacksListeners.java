package io.th0rgal.packsmanager;

import de.exceptionflug.protocolize.api.event.PacketReceiveEvent;
import de.exceptionflug.protocolize.api.handler.PacketAdapter;
import de.exceptionflug.protocolize.api.protocol.Stream;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacksListeners extends PacketAdapter<SendPackPacket> implements Listener {

    private final Map<UUID, String> map = new HashMap<>();

    public PacksListeners() {
        super(Stream.DOWNSTREAM, SendPackPacket.class);
    }

    @Override
    public void receive(final PacketReceiveEvent<SendPackPacket> event) {
        final SendPackPacket packet = event.getPacket();
        final UUID uuid = event.getPlayer().getUniqueId();
        if (map.containsKey(uuid) && map.get(uuid).equals(packet.getSha1())) {
            event.setCancelled(true);
            return;
        }
        map.put(uuid, packet.getSha1());
    }

    @EventHandler
    public void onDisconnect(final PlayerDisconnectEvent event) {
        map.remove(event.getPlayer().getUniqueId());
    }

}