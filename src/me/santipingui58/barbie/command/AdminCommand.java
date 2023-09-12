package me.santipingui58.barbie.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.barbie.BarbieKen;


public class AdminCommand implements CommandExecutor {

	
	private BarbieKen barbieKen;
	public AdminCommand(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
	}
	
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

           if (sender.hasPermission("hika.admin")) {
        	  if (args[0].equalsIgnoreCase("reload")) {
        		  sender.sendMessage("HikaBarbieKen recargado");
        		  barbieKen.init();
        	  }else if (args[0].equalsIgnoreCase("death")) {
        		barbieKen.getGameManager().death();
        	  } else if (args[0].equalsIgnoreCase("pausetimer")) {
        		  boolean b = barbieKen.getConfig().getBoolean("timerpaused");
        		  barbieKen.getGameManager().setTimerPaused(!b);
        		  barbieKen.getConfig().set("timerpaused", b);
        		  barbieKen.saveConfig();
        		  sender.sendMessage("Timer pausado: " + !b);
        	  }else if (args[0].equalsIgnoreCase("start")) {
        		  
        		  new BukkitRunnable() {
  					int time = 60;
  					public void run() {
  						if (time<=0) {
  							cancel();
  							barbieKen.getConfig().set("lobby", false);
  							barbieKen.saveConfig();
  							 List<Player> players = new ArrayList<Player>();
  							for (Player p :  Bukkit.getOnlinePlayers()) {
  								if (!p.hasPermission("hika.staff")) players.add(p);
  								p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.8F, 1F);
  							}
  						 
  						  
  						  
  		        		  new BukkitRunnable() {
  		        			  int i = 0;
  		        			  public void run() {
  		        				  if (i>=players.size())  {
  		        					 sender.sendMessage("Todos los jugadores tepeados!");
  		        					  cancel();
  		        				  }
  		        				  
  		        				  Player p = players.get(i);
  		        				 barbieKen.getGameManager().randomTP(p);	
  		        				 p.setGameMode(GameMode.SURVIVAL);
  		        				 i++;
  		        			  }
  		        		  }.runTaskTimer(barbieKen, 0L, 25L);
  							
  						}  else  {
  							
  							Bukkit.broadcastMessage("§7Comenzando en §b"+time +" §7segundos!");
  							
  							if (time ==  60  || time == 30  || time == 20 || time ==10 || time<=5 ) {
  							float d = 0.85F;
  							switch(time) {
  							case 1: d=0.95F;break;
  							default: break;
  							}	
  							
  							if (time<=5) {
  								for (Player p :  Bukkit.getOnlinePlayers()) 
  									p.sendTitle("§d§BARBIE §4§lVS §b§lKEN COMIENZA EN " + time, "§aDisfruta el evento", 1, 20, 1);
  							}
  							
  							for (Player p :  Bukkit.getOnlinePlayers()) 
  								p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, d);
  							
  						}
  							
  							time--;
  							}
  						
  						
  					}
  				}.runTaskTimer(barbieKen, 0L, 20L);
  				
  				
        		

        		
        		}
           }

			return false;
			
		}
}