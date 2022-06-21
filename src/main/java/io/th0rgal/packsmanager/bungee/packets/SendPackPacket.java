package io.th0rgal.packsmanager.bungee.packets;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.packet.AbstractPacket;
import dev.simplix.protocolize.api.mapping.ProtocolIdMapping;
import dev.simplix.protocolize.api.mapping.AbstractProtocolMapping;
import static dev.simplix.protocolize.api.util.ProtocolVersions.*;
import dev.simplix.protocolize.api.util.ProtocolUtil;

import io.netty.buffer.ByteBuf;

import java.util.*;

public class SendPackPacket extends AbstractPacket {

    private String url;
    private String sha1;
    private boolean forced;
    private boolean hasPromptMessage;
    private String message;

    public static final List<ProtocolIdMapping> MAPPINGS = Arrays.asList(
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_18_2, 0x3C),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19, MINECRAFT_1_19, 0x3A)
    );

    @Override
    public void read(final ByteBuf byteBuf, final PacketDirection packetDirection, final int protocolVersion) {
        this.url = ProtocolUtil.readString(byteBuf);
        this.sha1 = ProtocolUtil.readString(byteBuf);
        this.forced = byteBuf.readBoolean();
        this.hasPromptMessage = byteBuf.readBoolean();
        if (this.hasPromptMessage)
            this.message = ProtocolUtil.readString(byteBuf);
    }

    @Override
    public void write(final ByteBuf byteBuf, final PacketDirection packetDirection, final int protocolVersion) {
        ProtocolUtil.writeString(byteBuf, this.url);
        ProtocolUtil.writeString(byteBuf, this.sha1);
        byteBuf.writeBoolean(this.forced);
        byteBuf.writeBoolean(this.hasPromptMessage);
        ProtocolUtil.writeString(byteBuf, this.message);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getSha1() {
        return this.sha1;
    }

    public void setSha1(final String sha1) {
        this.sha1 = sha1;
    }

    public boolean isForced() {
        return this.forced;
    }

    public void setForced(final boolean forced) {
        this.forced = forced;
    }

    public boolean hasPromptMessage() {
        return this.hasPromptMessage;
    }

    public void setHasPromptMessage(final boolean hasPromptMessage) {
        this.hasPromptMessage = hasPromptMessage;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPackPacket that = (SendPackPacket) o;
        return that.sha1.equals(this.sha1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sha1);
    }

    @Override
    public String toString() {
        return this.sha1;
    }
}
