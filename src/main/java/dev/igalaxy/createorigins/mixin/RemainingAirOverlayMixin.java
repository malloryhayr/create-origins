package dev.igalaxy.createorigins.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;

import io.github.apace100.origins.power.OriginsPowerTypes;
import net.minecraft.client.player.LocalPlayer;

import net.minecraft.tags.TagKey;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RemainingAirOverlay.class)
public class RemainingAirOverlayMixin {
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
	private static boolean modifyRender(LocalPlayer player, TagKey key) {
		if(OriginsPowerTypes.WATER_BREATHING.isActive(player)) {
			return !player.isEyeInFluid(key);
		} else {
			return player.isEyeInFluid(key);
		}
	}
}
