package me.santipingui58.barbie.task;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.santipingui58.barbie.BarbieKen;
import me.santipingui58.barbie.game.GameManager;

public class TimeTask {

	private static int save = 60;
	
	private BukkitTask task;
	public TimeTask(BarbieKen barbieKen) {
		
		task = new BukkitRunnable() {
			int i = 0;
			public void run() {
				GameManager gm = barbieKen.getGameManager();
				if (gm.getTimer()<=0) {
					barbieKen.getConfig().set("timer", -1);
					barbieKen.getConfig().set("timerpaused", true);
					barbieKen.saveConfig();
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.kickPlayer("§dMuchas gracias a todos! Espero que lo hayan disfrutado y nos vemos en el próximo evento <3 \n §7§o- hikarilof");
			        	}
					Bukkit.getServer().setWhitelist(true);
					Bukkit.getServer().shutdown();
				
				}
				
				barbieKen.getScoreboard().scoreboard();
				i++;
				if (i>=save) {
					i = 0;
					barbieKen.saveConfig();
				}
				
		
				
				
				if (!gm.isTimerPaused() && gm.getTimer()>=0 && !barbieKen.getConfig().getBoolean("lobby")) {
				int timer = gm.getTimer()-1;
				gm.setTimer(timer);
				
				
				if (timer==1 || timer ==2 || timer==3 || timer==4 || timer ==5 || timer==10 || timer==30 || timer == 60 || timer == 120 || timer == 3*60 || timer == 5*60 || timer == 60*10
						|| timer == 60*30 || timer == 3600 || timer == 3600*2 || timer == 3600*3) {
					String tiempo = timer>3600 ? "horas" : timer ==3600 ? "hora" : timer> 60 ? "minutos" : timer==60 ? "minuto" : timer>1 ?  "segundos" : "segundo";
					int t = tiempo.startsWith("hora") ? timer/60/60 : tiempo.startsWith("minuto") ? timer/60 : timer;
					
					
					String msg = "§a§lEL SERVER CIERRA EN "+t + " " + tiempo.toUpperCase() +"!";
					
					for (Player p :  Bukkit.getOnlinePlayers()) {
						p.sendMessage(msg);
						p.sendTitle(msg, "", 1, 20*5, 1);
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.0F);
				}
	
				
				
				}
				}
			}

		}.runTaskTimer(barbieKen, 0L, 20L);
		
	}

	public void cancel() {
		this.task.cancel();
		
	}
	
	
	
	
}
