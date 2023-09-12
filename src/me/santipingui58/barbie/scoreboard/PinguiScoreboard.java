package me.santipingui58.barbie.scoreboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.santipingui58.barbie.BarbieKen;


public class PinguiScoreboard {

	
	
	private BarbieKen barbieKen;
	public PinguiScoreboard(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
	}
	
	
	public void scoreboard() {
		
		String conectadosStaff = "§f● §eConectados: §b"+ Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getServer().getMaxPlayers();
		
		int barbies = 0;
		int kens = 0;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("hika.staff")) continue;
			if (p.hasPermission("hika.ken")) {
				kens++;
			} else if (p.hasPermission("hika.barbie")) {
				barbies++;
			}
		}
		
		int maxKens = (int) ((barbies+kens)*1.6);
		
		
		String conectadasBarbie = "§f● §eConectadas: §b"+ barbies;
		String conectadosKens = "§f● §eConectados: §b"+ kens+"/"+maxKens;
		
		String eventoName = ChatColor.translateAlternateColorCodes('&', barbieKen.getConfig().getString("evento-name"));
		String eventoTime = ChatColor.translateAlternateColorCodes('&', barbieKen.getConfig().getString("evento-time"));
		for (Player p : Bukkit.getOnlinePlayers()) {
		String[] data = null;
		List<String> cache = new ArrayList<String>();
		String displayname = "";
			cache.add(displayname);
			cache.add("§f§f");
			cache.add("§f§f§f");
			cache.add("§f§f§f§f§f");
			cache.add("§f● §eNombre: §a§l" + p.getName());
			cache.add("§f● §eRol: " + getPrefix(p));
			cache.add(p.hasPermission("hika.staff") || p.hasPermission("hika.maxsteel") ? conectadosStaff : p.hasPermission("hika.barbie") ? conectadasBarbie : conectadosKens);
			cache.add("§f● §ePróximo Evento:");	
			if (eventoName.equalsIgnoreCase("N/A")) {
				cache.add(eventoName);	
			} else {
				cache.add(eventoName + "  §7(" + getTimer(eventoTime)+")");	
			}
			cache.add("§f§f§f§f");
			cache.add("§f● §eTimer: §c§l" +formatSeconds(barbieKen.getGameManager().getTimer()));
			cache.add("§f§f");
			cache.add("   §dtwitch.tv/hikarilof");
			for(int i = 0; i < cache.size(); i++) data = cache.toArray(new String[i]);
		BoardAPI.ScoreboardUtil.unrankedSidebarDisplay(p, data);	
	}
	}
		
	
	
	public String getTimer(String time) {
	try {
		Date temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(time);
		Date now = new Date();
		 long diffInMillies = Math.abs(temp.getTime() - now.getTime());
		   int diff = (int) TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		   if (diff<0) diff = 0;  
		    return formatSeconds(diff);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	
		 public  String getPrefix(Player p) {
			  String group = barbieKen.getLuckPerms().getPlayerAdapter(Player.class).getMetaData(p).getPrimaryGroup();
			  
			  switch(group.toLowerCase()) {
			  case "admin": return "§c§lADMIN";
			  case "helper": return "§2§lHELPER";
			  case "capitana": return "§d§lCAPITANA";
			  case "capitan": return "§b§lCAPITAN";
			  case "maxsteel": return "§a§lMAX STEEL";
			  case "barbie": return "§d§lBARBIE";
			  case "ken": return "§b§lKEN";
			  }
			  return "";
	}



		  
	
	public static String formatSeconds(int timeInSeconds)
	{
	    int hours = timeInSeconds / 3600;
	    int secondsLeft = timeInSeconds - hours * 3600;
	    int minutes = secondsLeft / 60;
	    int seconds = secondsLeft - minutes * 60;

	    String formattedTime = "";
	    if (hours < 10)
	        formattedTime += "0";
	    formattedTime += hours + ":";

	    if (minutes < 10)
	        formattedTime += "0";
	    formattedTime += minutes + ":";

	    if (seconds < 10)
	        formattedTime += "0";
	    formattedTime += seconds ;

	    return formattedTime;
	}
	
}
