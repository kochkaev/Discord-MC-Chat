package com.xujiayao.discord_mc_chat.minecraft.mixins;

import com.xujiayao.discord_mc_chat.minecraft.MinecraftEvents;
import net.minecraft.network.message.LastSeenMessageList;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.c2s.play.ChatCommandSignedC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.network.message.MessageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.Optional;

import static com.xujiayao.discord_mc_chat.Main.SERVER;

/**
 * @author Xujiayao
 */
@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerGamePacketListenerImpl {

	@Shadow
	private ServerPlayerEntity player;

	@Inject(method = "handleDecoratedMessage(Lnet/minecraft/network/message/SignedMessage;)V", at = @At("HEAD"), cancellable = true)
	private void broadcastChatMessage(SignedMessage playerChatMessage, CallbackInfo ci) {
		Optional<Text> result = MinecraftEvents.PLAYER_MESSAGE.invoker().message(player, playerChatMessage.getContent().getString());
		if (result.isPresent()) {
			SERVER.getPlayerManager().broadcast(playerChatMessage.withUnsignedContent(result.get()), this.player, MessageType.params(MessageType.CHAT, player));
			ci.cancel();
		}
	}

	@Inject(method = "executeCommand(Ljava/lang/String;)V", at = @At("HEAD"))
	private void performUnsignedChatCommand(String string, CallbackInfo ci) {
		MinecraftEvents.PLAYER_COMMAND.invoker().command(player, "/" + string);
	}

	@Inject(method = "handleCommandExecution(Lnet/minecraft/network/packet/c2s/play/ChatCommandSignedC2SPacket;Lnet/minecraft/network/message/LastSeenMessageList;)V", at = @At("HEAD"))
	private void performSignedChatCommand(ChatCommandSignedC2SPacket serverboundChatCommandSignedPacket, LastSeenMessageList lastSeenMessages, CallbackInfo ci) {
		MinecraftEvents.PLAYER_COMMAND.invoker().command(player, "/" + serverboundChatCommandSignedPacket.command());
	}
}
