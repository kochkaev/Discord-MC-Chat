package com.xujiayao.discord_mc_chat.minecraft;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.xujiayao.discord_mc_chat.utils.MarkdownParser;
import com.xujiayao.discord_mc_chat.utils.Translations;
import com.xujiayao.discord_mc_chat.utils.Utils;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.xujiayao.discord_mc_chat.Main.CONFIG;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author Xujiayao
 */
public class MinecraftCommands {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(literal("dmcc").executes(context -> {
					context.getSource().sendFeedback(() ->
							Text.literal(MarkdownParser.parseMarkdown(Utils.getHelpCommandMessage(false) + Translations.translate("minecraft.mcCommands.register.helpMessageExplanation"))), false);
					return 0;
				})
				.then(literal("help").executes(context -> {
					context.getSource().sendFeedback(() ->
							Text.literal(MarkdownParser.parseMarkdown(Utils.getHelpCommandMessage(false) + Translations.translate("minecraft.mcCommands.register.helpMessageExplanation"))), false);
					return 0;
				}))
				.then(literal("info").executes(context -> {
					context.getSource().sendFeedback(() ->
							Text.literal(Utils.getInfoCommandMessage()), false);
					return 0;
				}))
				.then(literal("stats")
						.then(argument("type", StringArgumentType.word())
								.then(argument("name", StringArgumentType.word())
										.executes(context -> {
											String type = StringArgumentType.getString(context, "type");
											String name = StringArgumentType.getString(context, "name");
											context.getSource().sendFeedback(() ->
													Text.literal(Utils.getStatsCommandMessage(type, name, false)), false);
											return 0;
										}))))
				.then(literal("update").executes(context -> {
					context.getSource().sendFeedback(() ->
							Text.literal(MarkdownParser.parseMarkdown(Utils.checkUpdate(true))), false);
					return 0;
				}))
				.then(literal("whitelist")
						.requires(source -> source.hasPermissionLevel(CONFIG.generic.whitelistRequiresAdmin ? 4 : 0))
						.then(argument("player", StringArgumentType.word())
								.executes(context -> {
									String player = StringArgumentType.getString(context, "player");
									context.getSource().sendFeedback(() ->
											Text.literal(MarkdownParser.parseMarkdown(Utils.whitelist(player))), false);
									return 0;
								})))
				.then(literal("reload")
						.requires(source -> source.hasPermissionLevel(4))
						.executes(context -> {
							context.getSource().sendFeedback(() ->
									Text.literal(MarkdownParser.parseMarkdown(Utils.reload())), false);
							return 0;
						})));
	}
}
