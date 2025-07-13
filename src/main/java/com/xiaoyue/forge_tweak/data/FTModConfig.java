package com.xiaoyue.forge_tweak.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class FTModConfig {

    public static class Client {

        Client(ForgeConfigSpec.Builder builder) {

        }
    }

    public static class Common {

        public final ForgeConfigSpec.BooleanValue checkFistTime;

        Common(ForgeConfigSpec.Builder builder) {
            checkFistTime = builder
                    .comment("Is it only automatically adding modifier when first equipping")
                    .define("checkFistTime", false);
        }
    }

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static String COMMON_PATH;

    static {
        final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = client.getRight();
        CLIENT = client.getLeft();

        final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = common.getRight();
        COMMON = common.getLeft();
    }

    public static void init() {
        register(ModConfig.Type.CLIENT, CLIENT_SPEC);
        COMMON_PATH = register(ModConfig.Type.COMMON, COMMON_SPEC);
    }

    private static String register(ModConfig.Type type, IConfigSpec<?> spec) {
        var mod = ModLoadingContext.get().getActiveContainer();
        String path = "celestial_configs/" + mod.getModId() + "-" + type.extension() + ".toml";
        ModLoadingContext.get().registerConfig(type, spec, path);
        return path;
    }

}
