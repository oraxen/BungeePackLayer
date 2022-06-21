package io.th0rgal.packsmanager.bungee.listeners;

import io.th0rgal.packsmanager.bungee.BungeePackLayerBungeePlugin;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnectEvent(final PlayerDisconnectEvent event) {
        BungeePackLayerBungeePlugin.getINSTANCE().getPacksListener().removePlayer(event.getPlayer().getUniqueId());
    }
}
