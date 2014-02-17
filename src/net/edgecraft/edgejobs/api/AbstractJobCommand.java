package net.edgecraft.edgejobs.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;

//TODO: Finish this class
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
		
		
		
		return false;
	}
	
	public abstract boolean run(User user, String[] args);
	
	@Override
	public final boolean sysAccess(CommandSender sender, String[] args) {
		sender.sendMessage("Keine Unterstuetzung von Job-Commands fuer die Konsole!");
		return true; // Console cant do Job-Commands
	}

}
