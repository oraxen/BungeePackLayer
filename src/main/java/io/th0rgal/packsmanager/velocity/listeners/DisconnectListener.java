package io.th0rgal.packsmanager.velocity.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;

import io.th0rgal.packsmanager.velocity.BungeePackLayerVelocityPlugin;

public class DisconnectListener {

    @Subscribe
    public void onDisconnectEvent(DisconnectEvent event) {
        BungeePackLayerVelocityPlugin.getINSTANCE().getResourcePackRequestListener().removePlayer(event.getPlayer().getUniqueId());
    }
}
