package me.santipingui58.barbie.command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;




public class LoginCommand implements CommandExecutor {

	private Plugin plugin;
	
	public LoginCommand(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("login")){
			Player p = (Player) sender;
			
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
			out.writeUTF("Connect");
			out.writeUTF("login");
			} catch (IOException eee) {
			}
			p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
			}
			

}
		
		
		return false;
	}
	
	

	
}