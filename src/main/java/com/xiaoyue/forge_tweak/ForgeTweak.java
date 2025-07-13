package com.xiaoyue.forge_tweak;

import com.mojang.logging.LogUtils;
import com.xiaoyue.forge_tweak.data.FTModConfig;
import com.xiaoyue.forge_tweak.event.FTCurioEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ForgeTweak.MODID)
public class ForgeTweak
{
    public static final String MODID = "forge_tweak";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public ForgeTweak() {
        FTModConfig.init();
        FTCurioEventHandler.register();
    }

    public static ResourceLocation loc(String s) {
        return new ResourceLocation(MODID, s);
    }
}
