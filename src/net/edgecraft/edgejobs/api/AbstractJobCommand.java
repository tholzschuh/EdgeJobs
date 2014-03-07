package net.edgecraft.edgejobs.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;

public abstract class AbstractJobCommand extends AbstractCommand {

	private String job;
	
	public AbstractJobCommand( AbstractJob  job ){
		
		this.job = job.getName();
	}
	
	public String getJobName(){
		return this.job;
	}
	
	@Override
	public Level getLevel() {
		return Level.USER; // Every user can do Job-Commands
	}
	
	public final boolean run( Player player, User user, String[] args ) throws Exception {
		
		AbstractJob job = JobManager.getJob( user );
		
		if( getJobName().equalsIgnoreCase( job.getName() ) )
			return super.run( player, args );
		
		player.sendMessage( EdgeCore.errorColor + "Du darfst das nicht ohne den Job!" );
		return true;
		
	}
	
	@Override
	public final boolean sysAccess(CommandSender sender, String[] args) {
		sender.sendMessage("Keine Unterstuetzung von Job-Commands fuer die Konsole!");
		return true; // Console cant do Job-Commands
	}

}
