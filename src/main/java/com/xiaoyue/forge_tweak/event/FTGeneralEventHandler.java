package com.xiaoyue.forge_tweak.event;

import com.xiaoyue.celestial_forge.content.modifier.ModifierInstance;
import com.xiaoyue.celestial_forge.utils.ModifierUtils;
import com.xiaoyue.celestial_forge.utils.TypeTestUtils;
import com.xiaoyue.forge_tweak.data.FTModConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.forge_tweak.ForgeTweak.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FTGeneralEventHandler {

    public static final String checkFirstTime = "ForgeTweak_checkFirstTime";

    public static void checkAndAccept(ItemStack stack, RandomSource source) {
        if (TypeTestUtils.getType(stack) != null) {
            if (stack.getTag() != null && ModifierUtils.getModifier(stack) == null) {
                if (FTModConfig.COMMON.checkFistTime.get()) {
                    if (!stack.getTag().getBoolean(checkFirstTime)) {
                        ModifierInstance ins = ModifierUtils.rollModifier(stack, source);
                        if (ins == null) return;
                        ModifierUtils.setModifier(stack, ins);
                        stack.getOrCreateTag().putBoolean(checkFirstTime, true);
                    }
                } else {
                    ModifierInstance ins = ModifierUtils.rollModifier(stack, source);
                    if (ins == null) return;
                    ModifierUtils.setModifier(stack, ins);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onChangeTool(LivingEquipmentChangeEvent event) {
        ItemStack to = event.getTo();
        checkAndAccept(to, event.getEntity().getRandom());
    }
}
