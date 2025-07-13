package com.xiaoyue.forge_tweak.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

public class FTCurioEventHandler {

    public static void register() {
        if (ModList.get().isLoaded("curios")) {
            MinecraftForge.EVENT_BUS.register(FTCurioEventHandler.class);
        }
    }

    @SubscribeEvent
    public static void onChangeCurio(CurioChangeEvent event) {
        ItemStack to = event.getTo();
        FTGeneralEventHandler.checkAndAccept(to, event.getEntity().getRandom());
    }
}
