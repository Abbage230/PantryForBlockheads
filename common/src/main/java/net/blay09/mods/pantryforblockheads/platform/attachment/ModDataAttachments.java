package net.blay09.mods.pantryforblockheads.platform.attachment;

import net.blay09.mods.balm.platform.attachment.BalmDataAttachmentTypeRegistrar;
import net.blay09.mods.balm.platform.attachment.DataAttachmentLookup;

public class ModDataAttachments {
    public final DataAttachmentLookup<PlayerFortune> fortune;

    public ModDataAttachments(BalmDataAttachmentTypeRegistrar registrar) {
        fortune = registrar.register("fortune", PlayerFortune.CODEC, PlayerFortune::new, true).asLookup();
    }
}
