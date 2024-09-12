package com.xujiayao.discord_mc_chat.minecraft.mixins;

import com.mojang.brigadier.context.CommandContext;
import com.xujiayao.discord_mc_chat.minecraft.MinecraftEvents;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.command.SayCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Xujiayao
 */
@Mixin(SayCommand.class)
public class MixinSayCommand {

	@Inject(method = "method_43657(Lcom/mojang/brigadier/context/CommandContext;Lnet/minecraft/network/message/SignedMessage;)V", at = @At("HEAD"))
	private static void method_43657(CommandContext<ServerCommandSource> context, SignedMessage playerChatMessage, CallbackInfo ci) {
		MinecraftEvents.COMMAND_MESSAGE.invoker().message(playerChatMessage.getContent().getString(), context.getSource());
	}
}
