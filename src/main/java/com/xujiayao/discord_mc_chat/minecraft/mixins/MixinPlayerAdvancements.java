package com.xujiayao.discord_mc_chat.minecraft.mixins;

import com.xujiayao.discord_mc_chat.minecraft.MinecraftEvents;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Xujiayao
 */
@Mixin(PlayerAdvancementTracker.class)
public abstract class MixinPlayerAdvancements {

	@Shadow
	private ServerPlayerEntity owner;

	@Shadow
	public abstract AdvancementProgress getProgress(AdvancementEntry advancement);

	@Inject(method = "grantCriterion(Lnet/minecraft/advancement/AdvancementEntry;Ljava/lang/String;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementRewards;apply(Lnet/minecraft/server/network/ServerPlayerEntity;)V", shift = At.Shift.AFTER))
	private void grantCriterion(AdvancementEntry advancementHolder, String string, CallbackInfoReturnable<Boolean> cir) {
		MinecraftEvents.PLAYER_ADVANCEMENT.invoker().advancement(owner, advancementHolder, getProgress(advancementHolder).isDone());
	}
}
