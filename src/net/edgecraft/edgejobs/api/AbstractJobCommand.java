package net.edgecraft.edgejobs.api;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.job.Job;
import net.edgecraft.edgejobs.job.JobCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractJobCommand extends AbstractCommand {

	private final AbstractJob job;
	
	public AbstractJobCommand( AbstractJob  job ) {
		
		if( job == null ) this.job = Job.DEFAULT_JOB.getJob();
		else this.job = job;
		
		JobCommands.getInstance().registerCommand( this );
	}
	
	public AbstractJob getJob() {
		return job;
	}
	
	public String getJobName(){
		return job.getName();
	}
	
	@Override
	public Level getLevel() {
		return Level.USER; // Every user can do Job-Commands
	}
	
	public final boolean run( Player player, User user, String[] args ) throws Exception {
		
		AbstractJob job = JobManager.getJob( user );
		
		if( getJobName().equalsIgnoreCase( job.getName() ) || job.equals( Job.DEFAULT_JOB.getJob() ) )
			return super.run( player, args );
		
		player.sendMessage(lang.getColoredMessage("de", "job_nojob"));
		
		return true;
		
	}
	
	@Override
	public final boolean sysAccess(CommandSender sender, String[] args) {
		sender.sendMessage(lang.getColoredMessage("de", "noconsole"));
		return true; // Console cant do Job-Commands
	}
}
