package net.edgecraft.edgejobs.api.tasks;

import net.edgecraft.edgeconomy.EdgeConomyAPI;
import net.edgecraft.edgeconomy.economy.BankAccount;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.JobManager;

import org.bukkit.scheduler.BukkitRunnable;

public class SidejobPayTask extends BukkitRunnable {

	@Override
	public void run() {
		
		for(User u : EdgeCoreAPI.userAPI().getUsers().values()){
			
			AbstractSidejob job = JobManager.getUserSidejob(u);
			
			if(job == null) continue;
			
			if(job.hasDoneWork(u)){
				
				BankAccount state = EdgeConomyAPI.economyAPI().getAccount(0); // TODO: Change to account from state @Panjab :P
				BankAccount user = EdgeConomyAPI.economyAPI().getAccount(u.getID());
				String message = EdgeCoreAPI.languageAPI().getColoredMessage("de", "job_transaction");
				
				EdgeConomyAPI.transactionAPI().addTransaction(state, user, job.getPay(), message);
				
			}
			
		}
		
	}
	
}
