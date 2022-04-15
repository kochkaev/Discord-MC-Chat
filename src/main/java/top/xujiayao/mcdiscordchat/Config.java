package top.xujiayao.mcdiscordchat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xujiayao
 */
public class Config {

	// More information + Docs: https://blog.xujiayao.top/posts/4ba0a17a/

	public int version = 2;

	public Generic generic = new Generic();
	public MultiServer multiServer = new MultiServer();
	public TextsZH textsZH = new TextsZH();
	public TextsEN textsEN = new TextsEN();

	public static class Generic {
		public boolean useEngInsteadOfChin = true;

		public String botToken = "";
		public String botPlayingStatus = "Minecraft";
		public String botListeningStatus = "";

		public String webhookUrl = "";

		public String channelId = "";
		public String consoleLogChannelId = "";

		public boolean useUuidInsteadOfName = true;

		public String avatarApi = "https://mc-heads.net/avatar/%player%.png";

		public boolean broadcastCommandExecution = true;

		public boolean modifyChatMessages = true;

		public boolean useServerNickname = true;

		public boolean announceHighMspt = true;
		public int msptCheckInterval = 5000;
		public int msptLimit = 50;

		public boolean mentionAdmins = true;

		public boolean updateChannelTopic = true;
		public int channelTopicUpdateInterval = 600000;

		public List<String> excludedCommands = List.of("/tell");

		public List<String> adminsIds = new ArrayList<>();

		// TODO Link players to Discord accounts
	}

	public static class MultiServer {
		public boolean enable = false;
		public String host = "127.0.0.1";
		public int port = 5000;
		public String name = "SMP";
	}

	public static class TextsZH {
		public String unformattedResponseMessage = "    ┌──── <%name%> %message%";
		public String unformattedChatMessage = "[%server%] <%name%> %message%";
		public String unformattedOtherMessage = "[%server%] %message%";

		public String formattedResponseMessage = "[{\"text\":\"    ┌──── \",\"bold\":true,\"color\":\"dark_gray\"},{\"text\":\"<%name%> \",\"bold\":false,\"color\":\"%roleColor%\"},{\"text\":\"%message%\",\"bold\":false,\"color\":\"dark_gray\"}]";
		public String formattedChatMessage = "[{\"text\":\"[%server%] \",\"bold\":true,\"color\":\"blue\"},{\"text\":\"<%name%> \",\"bold\":false,\"color\":\"%roleColor%\"},{\"text\":\"%message%\",\"bold\":false,\"color\":\"gray\"}]";
		public String formattedOtherMessage = "[{\"text\":\"[%server%] \",\"bold\":true,\"color\":\"blue\"},{\"text\":\"%message%\",\"bold\":false,\"color\":\"gray\"}]";

		public String serverStarted = "**服务器已启动！**";
		public String serverStopped = "**服务器已关闭！**";

		public String joinServer = "**%playerName% 加入了服务器**";
		public String leftServer = "**%playerName% 离开了服务器**";

		public String deathMessage = "**%deathMessage%**";

		public String advancementTask = "**%playerName% 达成了进度 [%advancement%]**";
		public String advancementChallenge = "**%playerName% 完成了挑战 [%advancement%]**";
		public String advancementGoal = "**%playerName% 达成了目标 [%advancement%]**";

		public String highMspt = "**服务器 MSPT (%mspt%) 高于 %msptLimit%！**";

		public String consoleLogMessage = "[%time%] [INFO] %message%";

		public String offlineChannelTopic = ":x: Server offline | Last updated: <t:%lastUpdateTime%:f>";
		public String onlineChannelTopic = ":white_check_mark: %onlinePlayerCount%/%maxPlayerCount% player(s) online | %uniquePlayerCount% unique player(s) ever joined | Server started <t:%serverStartedTime%:R> | Last updated: <t:%lastUpdateTime%:f>";
		public String onlineChannelTopicForMultiServer = ":white_check_mark: %onlinePlayerCount%/%maxPlayerCount% player(s) online | %uniquePlayerCount% unique player(s) ever joined | %onlineServerCount% server(s) online [%onlineServerList%] | Server started <t:%serverStartedTime%:R> | Last updated: <t:%lastUpdateTime%:f>";
	}

	public static class TextsEN {
		public String unformattedResponseMessage = "    ┌──── <%name%> %message%";
		public String unformattedChatMessage = "[%server%] <%name%> %message%";
		public String unformattedOtherMessage = "[%server%] %message%";

		public String formattedResponseMessage = "[{\"text\":\"    ┌──── \",\"bold\":true,\"color\":\"dark_gray\"},{\"text\":\"<%name%> \",\"bold\":false,\"color\":\"%roleColor%\"},{\"text\":\"%message%\",\"bold\":false,\"color\":\"dark_gray\"}]";
		public String formattedChatMessage = "[{\"text\":\"[%server%] \",\"bold\":true,\"color\":\"blue\"},{\"text\":\"<%name%> \",\"bold\":false,\"color\":\"%roleColor%\"},{\"text\":\"%message%\",\"bold\":false,\"color\":\"gray\"}]";
		public String formattedOtherMessage = "[{\"text\":\"[%server%] \",\"bold\":true,\"color\":\"blue\"},{\"text\":\"%message%\",\"bold\":false,\"color\":\"gray\"}]";

		public String serverStarted = "**Server started!**";
		public String serverStopped = "**Server stopped!**";

		public String joinServer = "**%playerName% joined the game**";
		public String leftServer = "**%playerName% left the game**";

		public String deathMessage = "**%deathMessage%**";

		public String advancementTask = "**%playerName% has made the advancement [%advancement%]**";
		public String advancementChallenge = "**%playerName% has completed the challenge [%advancement%]**";
		public String advancementGoal = "**%playerName% has reached the goal [%advancement%]**";

		public String highMspt = "**Server MSPT (%mspt%) is above %msptLimit%!**";

		public String consoleLogMessage = "[%time%] [INFO] %message%";

		public String offlineChannelTopic = ":x: Server offline | Last updated: <t:%lastUpdateTime%:f>";
		public String onlineChannelTopic = ":white_check_mark: %onlinePlayerCount%/%maxPlayerCount% player(s) online | %uniquePlayerCount% unique player(s) ever joined | Server started <t:%serverStartedTime%:R> | Last updated: <t:%lastUpdateTime%:f>";
		public String onlineChannelTopicForMultiServer = ":white_check_mark: %onlinePlayerCount%/%maxPlayerCount% player(s) online | %uniquePlayerCount% unique player(s) ever joined | %onlineServerCount% server(s) online [%onlineServerList%] | Server started <t:%serverStartedTime%:R> | Last updated: <t:%lastUpdateTime%:f>";
	}
}

