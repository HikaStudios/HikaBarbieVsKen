package me.santipingui58.barbie;

import org.bukkit.plugin.java.JavaPlugin;

import me.santipingui58.barbie.command.AdminCommand;
import me.santipingui58.barbie.command.GlobalCommand;
import me.santipingui58.barbie.command.LoginCommand;
import me.santipingui58.barbie.command.SetTimerCommand;
import me.santipingui58.barbie.game.GameManager;
import me.santipingui58.barbie.listener.ChatListener;
import me.santipingui58.barbie.listener.PlayerJoinListener;
import me.santipingui58.barbie.listener.ServerListener;
import me.santipingui58.barbie.scoreboard.PinguiScoreboard;
import me.santipingui58.barbie.task.TimeTask;
import net.luckperms.api.LuckPerms;

public class BarbieKen extends JavaPlugin {

	private GameManager gameManager;
	private PinguiScoreboard scoreboard;
	private TimeTask timeTask;
	private LuckPerms luckPerms;
	//SCOREBOARD 
	// TIMER FRANJAS 
	// RANDOM TP
	// SISTEMA DE SOLO 20% MÁS DE KENS
	
	
	
	
	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		 getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);
		 getServer().getPluginManager().registerEvents(new ServerListener(this),this);
		 getServer().getPluginManager().registerEvents(new ChatListener(this),this);
		 getCommand("global").setExecutor(new GlobalCommand());
		 getCommand("settimer").setExecutor(new SetTimerCommand(this));
		 getCommand("admin").setExecutor(new AdminCommand(this));
		 getCommand("login").setExecutor(new LoginCommand(this));
			this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);
		init();
		
		 this.timeTask = new TimeTask(this);
	}
	
	@Override
	public void onDisable() {
		this.timeTask.cancel();
	}
	
	
	public  LuckPerms getLuckPerms() {
		return this.luckPerms;
	}
	
	
	public void init() {
		reloadConfig();
		 this.scoreboard = new PinguiScoreboard(this);
	
		 this.gameManager = new GameManager(this);
	}
	
	public GameManager getGameManager() {
		return this.gameManager;
	}
	
	
	public PinguiScoreboard getScoreboard() {
		return this.scoreboard;
	}
	
}
