package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.CommandHandler;

public class JobCommands extends CommandHandler 
{

	private static final JobCommands instance = new JobCommands();
	
	private JobCommands() 
	{
		
		for( Job j : Job.getJobs() ) 
			for( AbstractCommand cmd : j.getJob().jobCommands() )
				super.registerCommand( cmd );
	}
	
	public static final JobCommands getInstance() 
	{
		return instance;
	}
}
