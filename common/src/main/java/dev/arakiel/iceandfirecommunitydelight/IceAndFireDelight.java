package dev.arakiel.iceandfirecommunitydelight;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common entry point of Ice and Fire Community Delight.
 *
 * <p>Mod id, asset namespace and data namespace are all {@code iceandfirecommunitydelight}.</p>
 */
public final class IceAndFireDelight {
    public static final String MOD_ID = "iceandfirecommunitydelight";
    public static final Logger LOGGER = LoggerFactory.getLogger("IceAndFireDelight");

    private IceAndFireDelight() {
    }

    /** A resource location inside the mod's namespace. */
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        ModConfig.load();
        ModTiers.init();
        ModEffects.register();
        ModBlocks.register();
        ModItems.register();
        ModTabs.register();
        ModEvents.register();
    }
}
