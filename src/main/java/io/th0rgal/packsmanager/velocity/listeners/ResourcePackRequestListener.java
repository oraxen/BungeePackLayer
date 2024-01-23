package io.th0rgal.packsmanager.velocity.listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerResourcePackSendEvent;
import com.velocitypowered.api.proxy.player.ResourcePackInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourcePackRequestListener {

    private final Map<UUID, byte[]> map = new HashMap<>();

    @Subscribe(order = PostOrder.FIRST)
    public void packetReceive(final ServerResourcePackSendEvent event) {
        final ResourcePackInfo resourcePackRequest = event.getProvidedResourcePack();
        final UUID uuid = event.getServerConnection().getPlayer().getUniqueId();
        if (map.containsKey(uuid) && Arrays.equals(map.get(uuid), resourcePackRequest.getHash())) {
            event.setResult(ResultedEvent.GenericResult.denied());
            return;
        }
        map.put(uuid, resourcePackRequest.getHash());
    }
    public void removePlayer(final UUID uuid) {
        map.remove(uuid);
    }

}
