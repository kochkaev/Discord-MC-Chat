package com.xujiayao.discord_mc_chat.minecraft.mixins;

import com.mojang.brigadier.context.CommandContext;
import com.xujiayao.discord_mc_chat.minecraft.MinecraftEvents;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TellRawCommand;
import net.minecraft.command.argument.TextArgumentType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Xujiayao
 */
@Mixin(TellRawCommand.class)
public class MixinTellRawCommand {

	@Inject(method = "method_13777(Lcom/mojang/brigadier/context/CommandContext;)I", at = @At("HEAD"))
	private static void method_13777(CommandContext<ServerCommandSource> context, CallbackInfoReturnable<Integer> cir) {
		String input = context.getInput();

		if (input.startsWith("/tellraw @a ") || input.startsWith("tellraw @a ")) {
			MinecraftEvents.COMMAND_MESSAGE.invoker().message(TextArgumentType.getTextArgument(context, "message").getString(), context.getSource());
		}
	}
}
