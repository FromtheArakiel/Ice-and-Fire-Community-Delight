package dev.arakiel.iceandfirecommunitydelight;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import dev.architectury.platform.Platform;

import java.nio.file.Path;

/**
 * Shared config of the mod, stored as TOML through NightConfig.
 *
 * <p>NightConfig is declared as a maven dependency and shipped inside the mod jar (a nested jar on
 * Fabric, JarJar on NeoForge), so both loaders read and write the same file:
 * {@code config/iceandfirecommunitydelight-common.toml}.</p>
 */
public final class CommonConfig {
    private static final String FILE_NAME = "iceandfirecommunitydelight-common.toml";

    public static boolean giveBookOnStartup = true;

    public static boolean enableExplosionsWhenEatingSpecialPie = true;
    public static double chanceExplosionsWhenEatingSpecialPie = 0.2D;
    public static double deathsBeforeExplosionsStopSpecialPie = 1.0D;

    public static boolean enableExplosionsWhenEatingSpecialPieSlice = true;
    public static double chanceExplosionsWhenEatingSpecialPieSlice = 0.2D;
    public static double deathsBeforeExplosionsStopSpecialPieSlice = 1.0D;

    public static boolean enableExplosionsWhenEatingSpecialSausage = true;
    public static double chanceExplosionsWhenEatingSpecialSausage = 0.2D;
    public static double deathsBeforeExplosionsStopSpecialSausage = 1.0D;

    private CommonConfig() {
    }

    public static void load() {
        Path file = Platform.getConfigFolder().resolve(FILE_NAME);
        try (CommentedFileConfig config = CommentedFileConfig.builder(file).preserveInsertionOrder().build()) {
            config.load();

            giveBookOnStartup = bool(config, "general.give_book_on_startup", giveBookOnStartup);

            enableExplosionsWhenEatingSpecialPie = bool(config, "special_pie.enable_explosions_when_eat_special_pie",
                    enableExplosionsWhenEatingSpecialPie);
            chanceExplosionsWhenEatingSpecialPie = number(config, "special_pie.chance_explosions_when_eat_special_pie",
                    chanceExplosionsWhenEatingSpecialPie);
            deathsBeforeExplosionsStopSpecialPie = number(config,
                    "special_pie.number_death_before_explosions_stop_when_eating_special_pie",
                    deathsBeforeExplosionsStopSpecialPie);

            enableExplosionsWhenEatingSpecialPieSlice = bool(config,
                    "special_pie_slice.enable_explosions_when_eat_special_pie_slice", enableExplosionsWhenEatingSpecialPieSlice);
            chanceExplosionsWhenEatingSpecialPieSlice = number(config,
                    "special_pie_slice.chance_explosions_when_eat_special_pie_slice", chanceExplosionsWhenEatingSpecialPieSlice);
            deathsBeforeExplosionsStopSpecialPieSlice = number(config,
                    "special_pie_slice.number_death_before_explosions_stop_when_eating_special_pie_slice",
                    deathsBeforeExplosionsStopSpecialPieSlice);

            enableExplosionsWhenEatingSpecialSausage = bool(config,
                    "special_sausage.enable_explosions_when_eat_special_sausage", enableExplosionsWhenEatingSpecialSausage);
            chanceExplosionsWhenEatingSpecialSausage = number(config,
                    "special_sausage.chance_explosions_when_eat_special_sausage", chanceExplosionsWhenEatingSpecialSausage);
            deathsBeforeExplosionsStopSpecialSausage = number(config,
                    "special_sausage.number_death_before_explosions_stop_when_eating_special_sausage",
                    deathsBeforeExplosionsStopSpecialSausage);

            writeBack(config);
            config.save();
        } catch (Exception exception) {
            IceAndFireDelight.LOGGER.warn("Could not read or write the config file {}", file, exception);
        }
    }

    /** Writes every option back so the file always contains the complete, documented option list. */
    private static void writeBack(CommentedConfig config) {
        config.setComment("general", "General settings.");
        config.set("general.give_book_on_startup", giveBookOnStartup);
        config.setComment("general.give_book_on_startup", "Give the cookbook on the first join. Default: true");

        config.setComment("special_pie", "Eating a placed Dragon Special Pie.");
        config.set("special_pie.enable_explosions_when_eat_special_pie", enableExplosionsWhenEatingSpecialPie);
        config.setComment("special_pie.enable_explosions_when_eat_special_pie",
                "Will there be an explosion when eating a special pie. Default: true");
        config.set("special_pie.chance_explosions_when_eat_special_pie", chanceExplosionsWhenEatingSpecialPie);
        config.setComment("special_pie.chance_explosions_when_eat_special_pie",
                "Chance of the explosion. 0.0 = 0%, 1.0 = 100%. Default: 0.2");
        config.set("special_pie.number_death_before_explosions_stop_when_eating_special_pie",
                deathsBeforeExplosionsStopSpecialPie);
        config.setComment("special_pie.number_death_before_explosions_stop_when_eating_special_pie",
                "How many explosions may trigger before they stop. Default: 1");

        config.setComment("special_pie_slice", "Eating a slice of Dragon Special Pie.");
        config.set("special_pie_slice.enable_explosions_when_eat_special_pie_slice", enableExplosionsWhenEatingSpecialPieSlice);
        config.setComment("special_pie_slice.enable_explosions_when_eat_special_pie_slice",
                "Will there be an explosion when eating a special pie slice. Default: true");
        config.set("special_pie_slice.chance_explosions_when_eat_special_pie_slice",
                chanceExplosionsWhenEatingSpecialPieSlice);
        config.setComment("special_pie_slice.chance_explosions_when_eat_special_pie_slice",
                "Chance of the explosion. 0.0 = 0%, 1.0 = 100%. Default: 0.2");
        config.set("special_pie_slice.number_death_before_explosions_stop_when_eating_special_pie_slice",
                deathsBeforeExplosionsStopSpecialPieSlice);
        config.setComment("special_pie_slice.number_death_before_explosions_stop_when_eating_special_pie_slice",
                "How many explosions may trigger before they stop. Default: 1");

        config.setComment("special_sausage", "Eating a Dragon Special Sausage.");
        config.set("special_sausage.enable_explosions_when_eat_special_sausage", enableExplosionsWhenEatingSpecialSausage);
        config.setComment("special_sausage.enable_explosions_when_eat_special_sausage",
                "Will there be an explosion when eating a special sausage. Default: true");
        config.set("special_sausage.chance_explosions_when_eat_special_sausage", chanceExplosionsWhenEatingSpecialSausage);
        config.setComment("special_sausage.chance_explosions_when_eat_special_sausage",
                "Chance of the explosion. 0.0 = 0%, 1.0 = 100%. Default: 0.2");
        config.set("special_sausage.number_death_before_explosions_stop_when_eating_special_sausage",
                deathsBeforeExplosionsStopSpecialSausage);
        config.setComment("special_sausage.number_death_before_explosions_stop_when_eating_special_sausage",
                "How many explosions may trigger before they stop. Default: 1");
    }

    private static boolean bool(CommentedConfig config, String path, boolean fallback) {
        Object value = config.get(path);
        if (value instanceof Boolean bool) {
            return bool;
        }
        if (value instanceof String text) {
            return Boolean.parseBoolean(text);
        }
        return fallback;
    }

    private static double number(CommentedConfig config, String path, double fallback) {
        Object value = config.get(path);
        if (value instanceof Number numeric) {
            return numeric.doubleValue();
        }
        if (value instanceof String text) {
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException ignored) {
                // fall through to the default
            }
        }
        return fallback;
    }
}
