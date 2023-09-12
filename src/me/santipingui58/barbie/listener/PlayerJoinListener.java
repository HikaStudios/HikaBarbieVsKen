package me.santipingui58.barbie.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.santipingui58.barbie.BarbieKen;
import me.santipingui58.barbie.utils.LuckPermsUtils;


public class PlayerJoinListener implements Listener {

	
	private BarbieKen barbieKen;
	public PlayerJoinListener(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
	}
	
	@EventHandler
	public void onPreJoin(PlayerLoginEvent  e) {
		Player p = e.getPlayer();
		if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL) &&   e.getPlayer().hasPermission("hika.fulljoin"))
			      e.allow(); 
			  
	
		if (LuckPermsUtils.hasPermission(p.getUniqueId(), "hika.staff") || LuckPermsUtils.hasPermission(p.getUniqueId(), "hika.ken") ||
				LuckPermsUtils.hasPermission(p.getUniqueId(), "hika.barbie") || LuckPermsUtils.hasPermission(p.getUniqueId(), "hika.maxsteel")) {
			
			if (Bukkit.getOnlinePlayers().size()<=10) return;
		if (p.hasPermission("hika.staff") || p.hasPermission("hika.streamer") || p.hasPermission("hika.sub")) return;
		if (p.hasPermission("hika.ken") && !p.hasPermission("hika.capitan")) {
			int barbiesConectadas = 0;
			int kensConectados = 0;
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("hika.barbie") && !pl.hasPermission("hika.staff")) barbiesConectadas++;
			}
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("hika.ken") && !pl.hasPermission("hika.staff")) kensConectados++;
			}
			;
			
			double maxKens = barbiesConectadas*1.5;
					if (maxKens>kensConectados) {
						//e.disallow(Result.KICK_FULL, "§cEl servidor se encuentra lleno!");
					}
		}
		} else {
			e.disallow(Result.KICK_OTHER, "§cNo tienes ningún rol asignado! Esto es probablemente un error, repórtalo en Discord.");
			return;
		}
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		if (barbieKen.getGameManager().getDeath().contains(p.getUniqueId())) {
			p.teleport(Bukkit.getWorld("world").getSpawnLocation());
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("hika.staff") || barbieKen.getGameManager().getDeath().contains(p.getUniqueId())) {
					p.showPlayer(barbieKen,pl);
					pl.showPlayer(barbieKen,p);
				} else {
					pl.hidePlayer(barbieKen,p);
				}
			}
		} else {
		
		if (!barbieKen.getConfig().getBoolean("lobby")) {
		
		if (p.hasPermission("hika.staff")) {
			p.setGameMode(GameMode.SPECTATOR);
		} else {
			
		if (!p.hasPlayedBefore())barbieKen.getGameManager().randomTP(p);
			
			if (p.hasPermission("hika.capitana")) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.hasPermission("hika.barbie")) {
				pl.sendMessage("§a[+] §d§l[CAPITANA] §f§l" + p.getName());
			}
		}	
		} else if (p.hasPermission("hika.capitan")) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("hika.ken")) {
					pl.sendMessage("§b[+] §9§l[CAPITAN] §f§l" + p.getName());
				}
			}	
			}else if (p.hasPermission("hika.barbie")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (pl.hasPermission("hika.barbie")) {
						pl.sendMessage("§d[+] §7" + p.getName());
					}
				}	
				}else if (p.hasPermission("hika.ken")) {
					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("hika.ken")) {
							pl.sendMessage("§b[+] §7" + p.getName());
						}
					}	
					}
		
		if (p.hasPermission("hika.barbie") && !p.hasPermission("hika.ken") && !p.hasPermission("hika.maxsteel")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,-1,0));
		} else if (!p.hasPermission("hika.barbie") && p.hasPermission("hika.ken") && !p.hasPermission("hika.maxsteel")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,-1,0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,0));
		} else if (!p.hasPermission("hika.barbie") && !p.hasPermission("hika.ken") && p.hasPermission("hika.maxsteel")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,-1,1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,2));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,-1,3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,-1,0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,0));
		} 
		}
		} else {
			p.setGameMode(GameMode.ADVENTURE);
			if (p.hasPermission("hika.staff") || p.hasPermission("hika.maxsteel")) {
				p.teleport(new Location(Bukkit.getWorld("world"),881,302,1020));
			} else if (p.hasPermission("hika.barbie")) {
				p.teleport(new Location(Bukkit.getWorld("world"),1000,302,1096));
			} else if (p.hasPermission("hika.ken")) {
				p.teleport(new Location(Bukkit.getWorld("world"),1000,302,954));
				
			}
		}
		}
	}
	
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		
		if (p.hasPermission("hika.staff") || barbieKen.getGameManager().getDeath().contains(p.getUniqueId())) {
			return;
		} else {
			if (p.hasPermission("hika.capitana")) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.hasPermission("hika.barbie")) {
				pl.sendMessage("§c[-] §d§l[CAPITANA] §f§l" + p.getName());
			}
		}	
		} else if (p.hasPermission("hika.capitan")) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("hika.ken")) {
					pl.sendMessage("§c[-] §9§l[CAPITAN] §f§l" + p.getName());
				}
			}	
			}else if (p.hasPermission("hika.barbie")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (pl.hasPermission("hika.barbie")) {
						pl.sendMessage("§c[-] §7" + p.getName());
					}
				}	
				}else if (p.hasPermission("hika.ken")) {
					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("hika.ken")) {
							pl.sendMessage("§c[-] §7" + p.getName());
						}
					}	
					}
		
		}
	}
	
	}
	

