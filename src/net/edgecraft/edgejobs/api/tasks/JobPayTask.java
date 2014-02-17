package net.edgecraft.edgejobs.api.tasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.edgecraft.edgeconomy.EdgeConomyAPI;
import net.edgecraft.edgeconomy.economy.BankAccount;
import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.scheduler.BukkitRunnable;

public class JobPayTask extends BukkitRunnable {

	@Override
	public void run() {
		
		Date d = new Date(System.currentTimeMillis());
		DateFormat date = new SimpleDateFormat("HH");;
		
		String time = date.format(d);
		
		if(time.equals(ConfigHandler.getPayHour())){
			
			for(User u : EdgeCore.getUsers().getUsers().values()){
				
				AbstractJob job = JobManager.getUserJob(u);
				
				if(job == null) continue;
				
				BankAccount state = EdgeConomyAPI.economyAPI().getAccount(0); // TODO: Change to account from state @Panjab :P
				BankAccount useracc = EdgeConomyAPI.economyAPI().getAccount(u.getID());
				String message = EdgeCoreAPI.languageAPI().getColoredMessage("de", "job_transaction");
				
				EdgeConomyAPI.transactionAPI().addTransaction(state, useracc, job.getPay(), message);
				
			}
			
		}
		
	}
	
}
