package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobCommand extends AbstractCommand 
{
	private static final JobManager jobs = EdgeJobs.getJobs();

	private static final String[] jobnames = { "job" };
	private static final String[] sidejobnames = { "sidejob" };
	
	private static final JobCommand instance = new JobCommand();
	
	private JobCommand() { /* ... */ }
	
	public static final JobCommand getInstance()
	{
		return instance;
	}
	
	@Override
	public Level getLevel() 
	{
		return Level.USER;
	}

	@Override
	public String[] getNames() 
	{
		return (String[]) ArrayUtils.addAll( jobnames, sidejobnames );
	}
	
	@Override
	public boolean validArgsRange(String[] args) 
	{
		return ( args.length >= 2 && args.length <= 4 );
	}
	
	@Override
	public void sendUsageImpl( CommandSender sender ) 
	{		
		if( !(sender instanceof Player) ) return;
						
		sender.sendMessage( EdgeCore.usageColor + "/job join [job]" );
		sender.sendMessage( EdgeCore.usageColor + "/job leave");
		
		User u = users.getUser( ((Player)sender).getName() );
		
		if( !Level.canUse( u, Level.MODERATOR ) ) return;
	
		sender.sendMessage( EdgeCore.usageColor + "/job setjob <user> <job>" );
		return;
	}



	@Override
	public boolean runImpl( Player player, User user, String[] args ) 
	{
		final String userLang = user.getLanguage();
		
		if( args[1].equalsIgnoreCase( "join" ) && ( args.length == 2 || args.length == 3 ) ) 
		{
			
			if( jobs.isWorking( player ) ) 
			{
				player.sendMessage(lang.getColoredMessage("de", "job_isworking"));
				return true;
			}
			
			AbstractJob job = jobs.getJob( user );
			
			if( args.length == 3 ) 
			{
				job = null;
				job = jobs.getJobByName( args[2] );
		
				if( job == null ) job = jobs.getSidejobByName( args[1] );
			} 	
			
			if( job == null ) 
			{
				player.sendMessage(lang.getColoredMessage("de", "job_nojob"));
				return true;
			}
			
			job.join( player );
			
			player.sendMessage(lang.getColoredMessage("de", "job_joinjob"));
			return true;
		}
		
		if( args[1].equalsIgnoreCase( "leave" ) ) 
		{
			if( args.length != 2 ) 
			{
				sendUsage( player );
				return true;
			}
			
			if( !jobs.isWorking( player ) ) 
			{
				player.sendMessage(lang.getColoredMessage("de", "job_isnotworking"));
				return true;
			}
			
			final AbstractJob job = jobs.getJob( user );
			
			if(job == null)
			{
				player.sendMessage(lang.getColoredMessage("de", "job_nojob"));
				return true;
			}
			
			job.leave( player );
			
			player.sendMessage(lang.getColoredMessage("de", "job_leavejob"));
			return true;
		}
		
		if( args[1].equalsIgnoreCase( "setjob" ) ) 
		{
				if( args.length != 4 ) 
				{
					sendUsage( player );
					return true;
				}
			
				if( !Level.canUse( user, Level.MODERATOR ) ) 
				{
					player.sendMessage( EdgeCore.errorColor + lang.getColoredMessage( userLang, "nopermission") );
					return false;
				}
					
				final Player target = Bukkit.getPlayer( args[2] );
				
				if( !target.isOnline() )
				{
					player.sendMessage(EdgeCore.errorColor + lang.getColoredMessage( userLang, "notfound" ) );
					return true;
				}		

				final AbstractJob job = jobs.getJob( args[3] );
					
				if( job == null ) 
				{
					player.sendMessage( EdgeCore.errorColor + lang.getColoredMessage( userLang, "jobnotfound" ) );
					return false;
				}
					
				ConfigHandler.setJob( user, job );
				return true;
		}
		
		sendUsage( player );
		return true;
	}
	
	@Override
	public boolean sysAccess( CommandSender sender, String[] args ) 
	{
		sender.sendMessage(lang.getColoredMessage("de", "noconsole"));
		return true;
	}
}
