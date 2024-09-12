package com.xujiayao.discord_mc_chat.minecraft.mixins;

import com.xujiayao.discord_mc_chat.minecraft.MinecraftEvents;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Xujiayao
 */
@Mixin(PlayerManager.class)
public class MixinPlayerList {

	//#if MC >= 12002
	@Inject(method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/server/network/ConnectedClientData;)V", at = @At("RETURN"))
	private void placeNewPlayer(ClientConnection connection, ServerPlayerEntity serverPlayer, ConnectedClientData commonListenerCookie, CallbackInfo ci) {
		MinecraftEvents.PLAYER_JOIN.invoker().join(serverPlayer);
	}

	@Inject(method = "remove(Lnet/minecraft/server/network/ServerPlayerEntity;)V", at = @At("RETURN"))
	private void remove(ServerPlayerEntity serverPlayer, CallbackInfo ci) {
		MinecraftEvents.PLAYER_QUIT.invoker().quit(serverPlayer);
	}
}
