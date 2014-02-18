package net.edgecraft.edgejobs.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;

public abstract class AbstractJobCommand extends AbstractCommand {

	private String job;
	private boolean isJob;
	
	public AbstractJobCommand(IJob job){
		this.job = job.getName();
		if(job instanceof AbstractJob)
			isJob = true;
		else
			isJob = false;
	}
	
	public String getJobName(){
		return this.job;
	}
	
	@Override
	public Level getLevel() {
		return Level.USER; // Every user can do Job-Commands
	}
	
	@Override
	public final boolean runImpl(Player player, User user, String[] args) throws Exception {
		
		boolean hasJob = false;
		
		if(isJob){
			
			AbstractJob j = JobManager.getUserJob(user);
			
			if(j.getName().equalsIgnoreCase(job))
				hasJob = true;
			
		}
		else{
			
			AbstractSidejob j = JobManager.getUserSidejob(user);
			
			if(j.getName().equalsIgnoreCase(job))
				hasJob = true;
			
		}
		
		if(!hasJob){
			
			player.sendMessage(EdgeCore.errorColor + "Du darfst das nicht ohne den Job!");
			
			return true;
		}
		
		return run(user, args);
	}
	
	public abstract boolean run(User user, String[] args);
	
	@Override
	public final boolean sysAccess(CommandSender sender, String[] args) {
		sender.sendMessage("Keine Unterstuetzung von Job-Commands fuer die Konsole!");
		return true; // Console cant do Job-Commands
	}

}
