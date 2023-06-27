package dev.igalaxy.createorigins.mixin;

import com.simibubi.create.content.equipment.armor.DivingHelmetItem;

import io.github.apace100.origins.power.OriginsPowerTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DivingHelmetItem.class)
public class DivingHelmetItemMixin {
	@Redirect(method = "breatheUnderwater", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
	private static boolean modifyBreatheUnderwater(LivingEntity instance, TagKey tagKey) {
		if (OriginsPowerTypes.WATER_BREATHING.isActive(instance)) {
			return !instance.isEyeInFluid(tagKey);
		}
		return instance.isEyeInFluid(tagKey);
	}
}
