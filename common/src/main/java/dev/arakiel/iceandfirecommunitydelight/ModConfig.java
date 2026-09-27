package dev.arakiel.iceandfirecommunitydelight;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.architectury.platform.Platform;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Loader independent replacement for the old Forge {@code ModConfigSpec}.
 *
 * <p>The file lives next to every other mod config as
 * {@code config/iceandfirecommunitydelight-common.json} on both Fabric and NeoForge. Missing or
 * broken entries fall back to the defaults and the file is rewritten on every launch so the whole
 * option list is always visible.</p>
 */
public final class ModConfig {
    private static final String FILE_NAME = "iceandfirecommunitydelight-common.json";

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

    private ModConfig() {
    }

    public static void load() {
        Path file = Platform.getConfigFolder().resolve(FILE_NAME);
        JsonObject root = new JsonObject();

        if (Files.isRegularFile(file)) {
            try (Reader reader = Files.newBufferedReader(file)) {
                root = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (Exception exception) {
                IceAndFireDelight.LOGGER.warn("Could not read {}, falling back to defaults", file, exception);
                root = new JsonObject();
            }
        }

        JsonObject general = section(root, "general");
        giveBookOnStartup = bool(general, "give_book_on_startup", giveBookOnStartup);

        JsonObject specialPie = section(root, "special_pie");
        enableExplosionsWhenEatingSpecialPie = bool(specialPie, "enable_explosions_when_eat_special_pie", enableExplosionsWhenEatingSpecialPie);
        chanceExplosionsWhenEatingSpecialPie = number(specialPie, "chance_explosions_when_eat_special_pie", chanceExplosionsWhenEatingSpecialPie);
        deathsBeforeExplosionsStopSpecialPie = number(specialPie, "number_death_before_explosions_stop_when_eating_special_pie", deathsBeforeExplosionsStopSpecialPie);

        JsonObject specialPieSlice = section(root, "special_pie_slice");
        enableExplosionsWhenEatingSpecialPieSlice = bool(specialPieSlice, "enable_explosions_when_eat_special_pie_slice", enableExplosionsWhenEatingSpecialPieSlice);
        chanceExplosionsWhenEatingSpecialPieSlice = number(specialPieSlice, "chance_explosions_when_eat_special_pie_slice", chanceExplosionsWhenEatingSpecialPieSlice);
        deathsBeforeExplosionsStopSpecialPieSlice = number(specialPieSlice, "number_death_before_explosions_stop_when_eating_special_pie_slice", deathsBeforeExplosionsStopSpecialPieSlice);

        JsonObject specialSausage = section(root, "special_sausage");
        enableExplosionsWhenEatingSpecialSausage = bool(specialSausage, "enable_explosions_when_eat_special_sausage", enableExplosionsWhenEatingSpecialSausage);
        chanceExplosionsWhenEatingSpecialSausage = number(specialSausage, "chance_explosions_when_eat_special_sausage", chanceExplosionsWhenEatingSpecialSausage);
        deathsBeforeExplosionsStopSpecialSausage = number(specialSausage, "number_death_before_explosions_stop_when_eating_special_sausage", deathsBeforeExplosionsStopSpecialSausage);

        writeBack(file);
    }

    private static void writeBack(Path file) {
        JsonObject root = new JsonObject();

        JsonObject general = new JsonObject();
        general.addProperty("give_book_on_startup", giveBookOnStartup);
        root.add("general", general);

        JsonObject specialPie = new JsonObject();
        specialPie.addProperty("enable_explosions_when_eat_special_pie", enableExplosionsWhenEatingSpecialPie);
        specialPie.addProperty("chance_explosions_when_eat_special_pie", chanceExplosionsWhenEatingSpecialPie);
        specialPie.addProperty("number_death_before_explosions_stop_when_eating_special_pie", deathsBeforeExplosionsStopSpecialPie);
        root.add("special_pie", specialPie);

        JsonObject specialPieSlice = new JsonObject();
        specialPieSlice.addProperty("enable_explosions_when_eat_special_pie_slice", enableExplosionsWhenEatingSpecialPieSlice);
        specialPieSlice.addProperty("chance_explosions_when_eat_special_pie_slice", chanceExplosionsWhenEatingSpecialPieSlice);
        specialPieSlice.addProperty("number_death_before_explosions_stop_when_eating_special_pie_slice", deathsBeforeExplosionsStopSpecialPieSlice);
        root.add("special_pie_slice", specialPieSlice);

        JsonObject specialSausage = new JsonObject();
        specialSausage.addProperty("enable_explosions_when_eat_special_sausage", enableExplosionsWhenEatingSpecialSausage);
        specialSausage.addProperty("chance_explosions_when_eat_special_sausage", chanceExplosionsWhenEatingSpecialSausage);
        specialSausage.addProperty("number_death_before_explosions_stop_when_eating_special_sausage", deathsBeforeExplosionsStopSpecialSausage);
        root.add("special_sausage", specialSausage);

        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, new GsonBuilder().setPrettyPrinting().create().toJson(root));
        } catch (Exception exception) {
            IceAndFireDelight.LOGGER.warn("Could not write config {}", file, exception);
        }
    }

    private static JsonObject section(JsonObject root, String key) {
        if (root.has(key) && root.get(key).isJsonObject()) {
            return root.getAsJsonObject(key);
        }
        return new JsonObject();
    }

    private static boolean bool(JsonObject json, String key, boolean fallback) {
        try {
            return json.has(key) ? json.get(key).getAsBoolean() : fallback;
        } catch (Exception exception) {
            return fallback;
        }
    }

    private static double number(JsonObject json, String key, double fallback) {
        try {
            return json.has(key) ? json.get(key).getAsDouble() : fallback;
        } catch (Exception exception) {
            return fallback;
        }
    }
}
