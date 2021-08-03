package io.th0rgal.packsmanager;

import de.exceptionflug.protocolize.api.event.PacketReceiveEvent;
import de.exceptionflug.protocolize.api.handler.PacketAdapter;
import de.exceptionflug.protocolize.api.protocol.Stream;

public class PacksListeners extends PacketAdapter<SendPackPacket> {

    public PacksListeners() {
        super(Stream.DOWNSTREAM, SendPackPacket.class);
    }

    @Override
    public void receive(PacketReceiveEvent<SendPackPacket> event) {
        SendPackPacket packet = event.getPacket();
        System.out.println(packet);
    }
}