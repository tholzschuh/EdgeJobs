package net.edgecraft.edgejobs;

import java.util.logging.Logger;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.command.CommandContainer;
import net.edgecraft.edgecore.command.CommandHandler;
import net.edgecraft.edgejobs.api.tasks.JobPayTask;
import net.edgecraft.edgejobs.api.tasks.SidejobPayTask;
import net.edgecraft.edgejobs.events.HandleItemEvents;
import net.edgecraft.edgejobs.events.HandlePlayerEvents;
import net.edgecraft.edgejobs.job.Job;
import net.edgecraft.edgejobs.job.JobCommand;
import net.edgecraft.edgejobs.job.JobCommands;
import net.edgecraft.edgejobs.job.jobs.Firefighter;
import net.edgecraft.edgejobs.job.jobs.Firefighter.FireCommand;
import net.edgecraft.edgejobs.partitions.PartitionCommand;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class EdgeJobs extends JavaPlugin 
{

	public static final ChatColor helpColor = ChatColor.GOLD;
	
	public static final String banner = "[EdgeJobs] ";
	private static final CommandHandler commands = EdgeCoreAPI.commandsAPI();
	
	private static EdgeJobs instance;
	public static final Logger log = EdgeCore.log;
	
	@Override
	public void onLoad() 
	{
			instance = this;
		}
	
	@Override
	public void onEnable() 
	{
		ConfigHandler.prepare();
		
		final PluginManager manager = getServer().getPluginManager();
		
		Job.registerJobs();
		
		manager.registerEvents( new HandlePlayerEvents(), this );
		manager.registerEvents( new HandleItemEvents(), this );
		
		commands.registerCommand( JobCommand.getInstance() );
		commands.registerCommand( PartitionCommand.getInstance() );
		commands.registerCommand( Firefighter.FireCommand.getInstance() );
		FireCommand.getInstance().fire();
		
		commands.registerCommand( new CommandContainer( JobCommands.getInstance() ) );
		
		startSchedulers();
		
		log.info(EdgeJobs.banner + "EdgeJobs aktiviert");
	}

	@Override
	public void onDisable() 
	{
		log.info(EdgeJobs.banner + "EdgeJobs deaktiviert");
	}
	
	public static EdgeJobs getInstance()
	{
		return instance;
	}
	
	private void startSchedulers() 
	{
		final BukkitRunnable jpt = (BukkitRunnable) new JobPayTask();
		final BukkitRunnable sjpt = (BukkitRunnable) new SidejobPayTask();
		
		final BukkitScheduler scheduler = this.getServer().getScheduler();
		
		scheduler.runTaskTimerAsynchronously( this, jpt, 40L, 200L);
		scheduler.runTaskTimerAsynchronously( this, sjpt, 40L, 250L);
		
	}
	
}
