package me.santipingui58.barbie.listener;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.santipingui58.barbie.BarbieKen;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;

public class ChatListener implements Listener {

	private BarbieKen barbieKen;
	public ChatListener(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
	}
	
	
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
 		if (barbieKen.getGameManager().getDeath().contains(p.getUniqueId())) return;
		Set<Player> recipents = new HashSet<Player>();
		
		 if (p.hasPermission("hika.barbie")) {
			 for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.hasPermission("hika.barbie") || pl.hasPermission("hika.maxsteel") || pl.hasPermission("hika.staff"))	 recipents.add(pl);
			 }
		} else  if (p.hasPermission("hika.ken")) {
			 for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.hasPermission("hika.ken") || pl.hasPermission("hika.maxsteel") || pl.hasPermission("hika.staff"))	 recipents.add(pl);
			 }
		} 
		 
		 
		 String prefix = getPrefix(p);
		 String msg = e.getMessage().replace("%", "%%");
 		String name = p.getName();	
 		String finalMsg = "";
 		if (p.hasPermission("hika.staff")) {
 			finalMsg = prefix + "§3"+name+"§8:§a " + ChatColor.translateAlternateColorCodes('&', msg);
 		} else if (prefix.equalsIgnoreCase("§7")) {
 			finalMsg = "§f"+name+"§8:§7 " + msg;
 		} else {
 			finalMsg = prefix + "§f"+name+"§8:§7 " + msg;
 		}

 		
 		for (Player pl : recipents) {
 			pl.sendMessage(finalMsg);
 		}
	}
	
	
	
    public String getPrefix(Player p) {
    	String luckperms = LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId()).getCachedData().getMetaData().getPrefix();
		String prefix = colorize(translateHexColorCodes(luckperms));
    	 return prefix;
    }
    
	
	private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
	
	
	private String colorize(final String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	private String translateHexColorCodes(final String message) {
		final char colorChar = ChatColor.COLOR_CHAR;

		final Matcher matcher = HEX_PATTERN.matcher(message);
		final StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

		while (matcher.find()) {
			final String group = matcher.group(1);

			matcher.appendReplacement(buffer, colorChar + "x"
					+ colorChar + group.charAt(0) + colorChar + group.charAt(1)
					+ colorChar + group.charAt(2) + colorChar + group.charAt(3)
					+ colorChar + group.charAt(4) + colorChar + group.charAt(5));
		}

		return matcher.appendTail(buffer).toString();
	}
}
