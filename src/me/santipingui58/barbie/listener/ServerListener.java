package me.santipingui58.barbie.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.barbie.BarbieKen;

public class ServerListener implements Listener {

	
	private BarbieKen barbieKen;
	public ServerListener(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
	}
	
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		
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
		if (barbieKen.getConfig().getBoolean("lobby")) {
			p.setGameMode(GameMode.ADVENTURE);
			if (p.hasPermission("hika.staff") || p.hasPermission("hika.maxsteel")) {
				p.teleport(new Location(Bukkit.getWorld("world"),881,302,1020));
			} else if (p.hasPermission("hika.barbie")) {
				p.teleport(new Location(Bukkit.getWorld("world"),1000,302,1096));
			} else if (p.hasPermission("hika.ken")) {
				p.teleport(new Location(Bukkit.getWorld("world"),1000,302,954));
				
			}
		} else {

		new BukkitRunnable() {
			public void run() {
				Location l = new Location(Bukkit.getWorld("world"),0,100,0);
				if (p.getBedSpawnLocation()==null || p.getLocation().distanceSquared(l)<=100*100) barbieKen.getGameManager().randomTP(p);
			}
		}.runTaskLater(barbieKen, 5L);
		

		
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
		}
	}
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (!barbieKen.getGameManager().isDeath()) return;
		Player p = e.getEntity();
		if (!p.hasPermission("hika.staff")) {
			barbieKen.getGameManager().getDeath().add(p.getUniqueId());
			p.teleport(Bukkit.getWorld("world").getSpawnLocation());
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("hika.staff") || barbieKen.getGameManager().getDeath().contains(p.getUniqueId())) {
					p.showPlayer(barbieKen,pl);
					pl.showPlayer(barbieKen,p);
				} else {
					pl.hidePlayer(barbieKen,p);
				}
			}
		}
	}
	
	
	@EventHandler
	public void onPickUp(EntityPickupItemEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		if (e.getItem().getItemStack().getType().equals(Material.DRAGON_EGG)) {
			Bukkit.broadcastMessage("§a§l" + p.getName() + " §5§lHA RECOGIDO EL HUEVO DEL DRAGÓN!");
		} else if (e.getItem().getItemStack().getType().equals(Material.NETHER_STAR)) {
			Bukkit.broadcastMessage("§a§l" + p.getName() + " §5§lHA RECOGIDO UNA ESTRELLA DEL NETHER!");
		}
	}
	
}
	

