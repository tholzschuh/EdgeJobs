package net.edgecraft.edgejobs.api.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.edgecraft.edgeconomy.EdgeConomyAPI;
import net.edgecraft.edgeconomy.economy.BankAccount;
import net.edgecraft.edgeconomy.economy.Economy;
import net.edgecraft.edgeconomy.transactions.TransactionManager;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.scheduler.BukkitRunnable;

public class JobPayTask extends BukkitRunnable {

	private static final UserManager users = EdgeCoreAPI.userAPI();
	private static final Economy economy = EdgeConomyAPI.economyAPI();
	private static final TransactionManager transactions = EdgeConomyAPI.transactionAPI();
	
	@Override
	public void run() {
		
		String time = new SimpleDateFormat( "HH" ).format( new Date( System.currentTimeMillis() ) );
		
		if( time.equals( ConfigHandler.getPayHour() ) ) {
			
			for( User u : users.getUsers().values() ) {
				
				AbstractJob job = JobManager.getJob( u );
				
				if(job == null) continue;
				
				BankAccount state = economy.getAccount(0); // TODO: Change to account from state @Panjab :P
				BankAccount useracc = economy.getAccount( u.getID() );
				String message = EdgeCoreAPI.languageAPI().getColoredMessage( u.getLanguage(), "job_transaction");
				
				transactions.addTransaction( state, useracc, job.getPay(), message );
				
			}
			
		}
		
	}
	
}
