package io.th0rgal.packsmanager;

import de.exceptionflug.protocolize.api.protocol.AbstractPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static de.exceptionflug.protocolize.api.util.ProtocolVersions.*;

public class SendPackPacket extends AbstractPacket {


    private String url;
    private String sha1;
    private boolean forced;
    private boolean hasPromptMessage;
    private String promptMessage;

    public static final Map<Integer, Integer> MAPPING = new HashMap<>();

    static {
        MAPPING.put(MINECRAFT_1_14, 0x3C);
        MAPPING.put(MINECRAFT_1_14_1, 0x3C);
        MAPPING.put(MINECRAFT_1_14_2, 0x3C);
        MAPPING.put(MINECRAFT_1_14_3, 0x3C);
        MAPPING.put(MINECRAFT_1_14_4, 0x3C);
        MAPPING.put(MINECRAFT_1_15, 0x3C);
        MAPPING.put(MINECRAFT_1_15_1, 0x3C);
        MAPPING.put(MINECRAFT_1_15_2, 0x3C);
        MAPPING.put(MINECRAFT_1_16, 0x3C);
        MAPPING.put(MINECRAFT_1_16_1, 0x3C);
        MAPPING.put(MINECRAFT_1_16_2, 0x3C);
        MAPPING.put(MINECRAFT_1_16_3, 0x3C);
        MAPPING.put(MINECRAFT_1_16_4, 0x3C);
        MAPPING.put(MINECRAFT_1_17, 0x3C);
        MAPPING.put(MINECRAFT_1_17_1, 0x3C);
    }

    public SendPackPacket(String url, String sha1, boolean forced,
                          boolean hasPromptMessage, String promptMessage) {
        this.url = url;
        this.sha1 = sha1;
        this.forced = forced;
        this.hasPromptMessage = hasPromptMessage;
        this.promptMessage = promptMessage;
    }

    public SendPackPacket() {

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha1() {
        return sha1;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public boolean isForced() {
        return forced;
    }

    public void setHasPromptMessage(boolean hasPromptMessage) {
        this.hasPromptMessage = hasPromptMessage;
    }

    public boolean hasPromptMessage() {
        return hasPromptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
    }

    public String getPromptMessage() {
        return promptMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPackPacket that = (SendPackPacket) o;
        return that.sha1.equals(sha1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha1);
    }

    @Override
    public String toString() {
        return sha1;
    }
}
