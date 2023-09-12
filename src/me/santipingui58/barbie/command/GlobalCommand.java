package me.santipingui58.barbie.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GlobalCommand implements CommandExecutor {


		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

           if (sender.hasPermission("hika.staff")) {
        	   String name = sender instanceof Player ? "" : ((Player) sender).getName()+ "§8:";
        	   
        		StringBuilder builder = new StringBuilder();
        	    for (int i = 0; i < args.length; i++)
        	    {
        	      builder.append(args[i]).append(" ");
        	    }
        	    
        	  String message = builder.toString();
        	   Bukkit.broadcastMessage("§c§l[ANUNCIO] " + name + "§a§l"  + message);
        	   
           }

			return false;
			
		}
}