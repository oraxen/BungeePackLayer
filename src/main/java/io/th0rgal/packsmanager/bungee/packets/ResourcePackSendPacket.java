package io.th0rgal.packsmanager.bungee.packets;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.packet.AbstractPacket;
import dev.simplix.protocolize.api.mapping.ProtocolIdMapping;
import dev.simplix.protocolize.api.mapping.AbstractProtocolMapping;
import static dev.simplix.protocolize.api.util.ProtocolVersions.*;
import dev.simplix.protocolize.api.util.ProtocolUtil;

import io.netty.buffer.ByteBuf;

import java.util.*;

public class ResourcePackSendPacket extends AbstractPacket {

    private String url;
    private String sha1;
    private boolean forced;
    private boolean hasPromptMessage;
    private String message;

    public static final List<ProtocolIdMapping> MAPPINGS = Arrays.asList(
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_8, MINECRAFT_1_8, 0x48),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_9, MINECRAFT_1_11_2, 0x32),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_12, MINECRAFT_1_12, 0x33),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_12_1, MINECRAFT_1_12_2, 0x34),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_13, MINECRAFT_1_13_2, 0x37),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_14_4, 0x39),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_15, MINECRAFT_1_15_2, 0x3A),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16, MINECRAFT_1_16_1, 0x39),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16_2, MINECRAFT_1_16_5, 0x38),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_17, MINECRAFT_1_18_2, 0x3C),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19, MINECRAFT_1_19, 0x3A),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19_1, MINECRAFT_1_19_2, 0x3D),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19_3, MINECRAFT_1_19_3, 0x3C),
            AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_19_4, MINECRAFT_1_19_4, 0x40)
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
        final ResourcePackSendPacket that = (ResourcePackSendPacket) o;
        return that.sha1.equals(this.sha1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sha1);
    }

    @Override
    public String toString() {
        return "Url: " + this.url +
                ", Sha1: " + this.sha1 +
                ", Is forced: " + this.forced +
                ", Has prompt message: " + this.hasPromptMessage +
                ", Message: " + this.message;
    }
}
