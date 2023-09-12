package me.santipingui58.barbie.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.santipingui58.barbie.BarbieKen;



public class SetTimerCommand implements CommandExecutor {
	
	private BarbieKen barbieKen;
	public SetTimerCommand(BarbieKen barbieKen) {
		this.barbieKen = barbieKen;
	}

		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            if (sender.isOp()) {
            	int timer = Integer.parseInt(args[0]);
            	timer = timer/60;
            	barbieKen.getGameManager().setTimer(timer);
            	sender.sendMessage("Timer actualizado");
            }

			return false;
			
		}
}