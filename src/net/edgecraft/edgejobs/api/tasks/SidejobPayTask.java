package net.edgecraft.edgejobs.api.tasks;

import net.edgecraft.edgeconomy.EdgeConomyAPI;
import net.edgecraft.edgeconomy.economy.BankAccount;
import net.edgecraft.edgeconomy.economy.Economy;
import net.edgecraft.edgeconomy.transactions.TransactionManager;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.JobManager;

import org.bukkit.scheduler.BukkitRunnable;


public class SidejobPayTask extends BukkitRunnable 
{
	private static final UserManager users = EdgeCoreAPI.userAPI();
	private static final Economy economy = EdgeConomyAPI.economyAPI();
	private static final TransactionManager transactions = EdgeConomyAPI.transactionAPI();
	private static final JobManager jobs = EdgeJobs.getJobs();
	
	@Override
	public void run() 
	{
		
		for( User u : users.getUsers().values() )
		{
			final AbstractSidejob job = jobs.getSidejobByUser( u );
			
			if(job == null) continue;
			
			if( job.hasDoneWork( u ) )
			{
				final BankAccount state = economy.getAccount(0); // TODO: Change to account from state @Panjab :P
				final BankAccount user = economy.getAccount(u.getID());
				final String message = EdgeCoreAPI.languageAPI().getColoredMessage( u.getLanguage(), "job_transaction");
				
				transactions.addTransaction(state, user, job.getPay(), message);
			}
		}
	}
}
