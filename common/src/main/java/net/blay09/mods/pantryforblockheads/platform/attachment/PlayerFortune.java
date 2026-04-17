package net.blay09.mods.pantryforblockheads.platform.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.blay09.mods.balm.platform.attachment.DataAttachmentLookup;
import net.blay09.mods.pantryforblockheads.PantryForBlockheads;

public class PlayerFortune {
    private int fortuneCooldownTicks;

    public static final Codec<PlayerFortune> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("fortuneCooldownTicks").forGetter(PlayerFortune::getFortuneCooldownTicks)
    ).apply(instance, PlayerFortune::new));

    public PlayerFortune() {
        this(0);
    }

    public PlayerFortune(int fortuneCooldownTicks) {
        this.fortuneCooldownTicks = fortuneCooldownTicks;
    }

    public static DataAttachmentLookup<PlayerFortune> lookup() {
        return PantryForBlockheads.dataAttachments().fortune;
    }

    public int getFortuneCooldownTicks() {
        return fortuneCooldownTicks;
    }

    public void setFortuneCooldownTicks(int fortuneCooldownTicks) {
        this.fortuneCooldownTicks = fortuneCooldownTicks;
    }
}
