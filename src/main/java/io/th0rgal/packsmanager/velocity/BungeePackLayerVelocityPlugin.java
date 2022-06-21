package io.th0rgal.packsmanager.velocity;

import com.google.inject.Inject;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.simplix.protocolize.api.Protocolize;

import io.th0rgal.packsmanager.velocity.listeners.DisconnectListener;
import io.th0rgal.packsmanager.velocity.listeners.ResourcePackRequestListener;

import org.slf4j.Logger;

@Plugin(
        id = "bungeepacklayer",
        name = "Bungee Pack Layer",
        version = "${version}",
        description = "${description}",
        authors = {"th0rgal"},
        dependencies = {
                @Dependency(id = "protocolize")
        }
)
public class BungeePackLayerVelocityPlugin {

    private final ProxyServer proxyServer;
    private final Logger logger;
    private static BungeePackLayerVelocityPlugin INSTANCE;
    private ResourcePackRequestListener resourcePackRequestListener;

    @Inject
    public BungeePackLayerVelocityPlugin(ProxyServer proxyServer, Logger logger) {
        this.proxyServer = proxyServer;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        INSTANCE = this;
        this.resourcePackRequestListener = new ResourcePackRequestListener();
        Protocolize.listenerProvider().registerListener(resourcePackRequestListener);
        this.proxyServer.getEventManager().register(this, new DisconnectListener());
    }

    public ProxyServer getProxyServer() {
        return this.proxyServer;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public static BungeePackLayerVelocityPlugin getINSTANCE() {
        return INSTANCE;
    }

    public ResourcePackRequestListener getResourcePackRequestListener() {
        return this.resourcePackRequestListener;
    }
}
