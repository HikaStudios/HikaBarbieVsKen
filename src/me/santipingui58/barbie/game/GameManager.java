package me.santipingui58.barbie.game;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.santipingui58.barbie.BarbieKen;

public class GameManager {

	
	private int timer;
	private boolean timerPaused;
	
	
	private Set<UUID> death = new HashSet<UUID>();
	private boolean isDeath;
	
	private BarbieKen barbieKen;
	public GameManager(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
		this.timer = barbieKen.getConfig().contains("timer") ? barbieKen.getConfig().getInt("timer") : 0;
		if (this.timer <= 0) this.timer = 60*60*8;
	}
	
	
	public Set<UUID> getDeath() {
		return this.death;
	}

	public boolean isTimerPaused() {
		return this.timerPaused;
	}

	public void setTimerPaused(boolean b) {
		this.timerPaused = b;
	}
	

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
		barbieKen.getConfig().set("timer", this.timer);
	}
	
	
	
	public void randomTP(Player p)  {
		
		World w = Bukkit.getWorld("world");
		Location minKen = new Location(w,100,300,-2025);
		Location maxKen = new Location(w,1800,300,1600);
		Location minBarbie = new Location(w,-1500,300,-1700);
		Location maxBarbie = new Location(w,-100,300,1400);
		
		Location min = p.hasPermission("hika.maxsteel") || p.hasPermission("hika.barbie") ? minBarbie : minKen; 
		Location max = p.hasPermission("hika.maxsteel") || p.hasPermission("hika.ken") ? maxKen : maxBarbie; 
		Location random = randomLocation(min,max);
		random = random.getWorld().getHighestBlockAt(random).getLocation();
		p.teleport(random);
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20*30,100));
		p.setBedSpawnLocation(random);
}


	
	public Location randomLocation(Location min, Location max)
    {
        Location range = new Location(min.getWorld(), Math.abs(max.getX() - min.getX()), min.getY(), Math.abs(max.getZ() - min.getZ()));
        return new Location(min.getWorld(), (Math.random() * range.getX()) + (min.getX() <= max.getX() ? min.getX() : max.getX()), range.getY(), (Math.random() * range.getZ()) + (min.getZ() <= max.getZ() ? min.getZ() : max.getZ()));
    }


	public void death() {
	this.isDeath = true;
	}


	public boolean isDeath() {
		return this.isDeath;
	}
	
}
