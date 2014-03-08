package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobCommand extends AbstractCommand {
	
	private static final UserManager users = EdgeCoreAPI.userAPI();

	private static final String[] jobnames = { "job", "beruf" };
	private static final String[] sidejobnames = { "nebenjob", "nebenberuf", "sidejob" };
	
	@Override
	public Level getLevel() {
		return Level.USER;
	}

	@Override
	public String[] getNames() {
		return (String[]) ArrayUtils.addAll( jobnames, sidejobnames );
	}
	
	@Override
	public boolean validArgsRange(String[] args) {
		
		return ( args.length == 1 || args.length == 2 || args.length == 4 );
	}
	
	@Override
	public void sendUsageImpl( CommandSender sender ) {		
		if( !(sender instanceof Player) ) return;
						
		sender.sendMessage( EdgeCore.usageColor + "/job join [job]" );
		sender.sendMessage( EdgeCore.usageColor + "/job leave");
		
		User u = users.getUser( ((Player)sender).getName() );
		
		if( !Level.canUse( u, Level.MODERATOR ) ) return;
	
		sender.sendMessage( EdgeCore.usageColor + "/job setjob <user> <job>" );
		return;
	}



	@Override
	public boolean runImpl( Player player, User user, String[] args ) {
		
		String userLang = user.getLanguage();
		
		if( args[1].equalsIgnoreCase( "join" ) ) {
			
			if( args.length < 1 || args.length > 2 ) {
				sendUsage( player );
				return false;
			}
			
			if( JobManager.isWorking( player ) ) {
				player.sendMessage( "Du arbeitest bereits!" );
				return true;
			}
			
			AbstractJob job = JobManager.getJob( args[1] );
			
			if( args.length == 2 ) {

				job = null;
				job = JobManager.getJobByName( args[1] );
		
				if( job == null ) job = JobManager.getSidejobByName( args[1] );
			} 	
			
			if( job == null ) {
				
				player.sendMessage(ChatColor.GRAY + "Du hast doch garkeinen Job!");
				return true;
			}
			
			job.equipPlayer( player );
			
			JobManager.setWorking( player, true );
			
			player.sendMessage(ChatColor.GREEN + "Viel Spaß beim Arbeiten!");
			return true;
		}
		
		else if( args[1].equalsIgnoreCase( "leave" ) ) {
			
			if( args.length != 2 ) {
				sendUsage( player );
				return true;
			}
			
			if( !JobManager.isWorking( player ) ) {
				player.sendMessage( "Du arbeitest momentan doch gar nicht!" );
				return true;
			}
			
			AbstractJob job = JobManager.getJob( user );
			
			if(job == null){
				
				player.sendMessage(ChatColor.GRAY + "Du hast doch garkeinen Job!");
				return true;
			}
			
			player.getInventory().clear();
			
			JobManager.setWorking( player, false );
			
			player.sendMessage(ChatColor.GREEN + "Genieß deine Freizeit!");
			return true;
		}
		
		if( args[1].equalsIgnoreCase( "setjob" ) ) {
			
				if( args.length != 4 ) {
					sendUsage( player );
					return true;
				}
			
				if( !Level.canUse( user, Level.MODERATOR ) ) {
					player.sendMessage( EdgeCore.errorColor + EdgeCoreAPI.languageAPI().getColoredMessage( userLang, "nopermission") );
					return false;
				}
					
				Player target = Bukkit.getPlayer( args[2] );
				
				if( !target.isOnline() ){
					player.sendMessage(EdgeCore.errorColor + EdgeCore.getLang().getColoredMessage( userLang, "notfound" ) );
					return true;
				}		

				AbstractJob job = JobManager.getJob( args[3] );
					
				if( job == null ) {
					player.sendMessage( EdgeCore.errorColor + EdgeCore.getLang().getColoredMessage( userLang, "jobnotfound" ) );
					return false;
				}
					
				ConfigHandler.setJob( user, job );
				return true;
		}
		
		sendUsage( player );
		return true;
	}
	
	@Override
	public boolean sysAccess(CommandSender arg0, String[] arg1) {
		arg0.sendMessage("No support for console!");
		return true;
	}
}