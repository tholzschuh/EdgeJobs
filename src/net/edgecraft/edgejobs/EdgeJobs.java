package net.edgecraft.edgejobs;

import java.util.logging.Logger;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.CommandHandler;
import net.edgecraft.edgejobs.api.tasks.JobPayTask;
import net.edgecraft.edgejobs.api.tasks.SidejobPayTask;
import net.edgecraft.edgejobs.job.CommandJob;
import net.edgecraft.edgejobs.util.ConfigHandler;
import net.edgecraft.edgejobs.util.UtilListener;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EdgeJobs extends JavaPlugin {

	public static final ChatColor helpColor = ChatColor.GOLD;
	
	public static final String banner = "[EdgeJobs] ";
	private static EdgeJobs instance;
	private static final Logger log = EdgeCore.log;
	
	@Override
	public void onLoad() {
		
		instance = this;
		
	}
	
	@Override
	public void onEnable() {
		
		ConfigHandler.prepare();
		
		getServer().getPluginManager().registerEvents(new UtilListener(), this);
		
		CommandHandler.getInstance().registerCommand(new CommandJob());
		
		startSchedulers();
		
		log.info(EdgeJobs.banner + "EdgeJobs aktiviert");
	}

	@Override
	public void onDisable() {

		log.info(EdgeJobs.banner + "EdgeJobs deaktiviert");
	}
	
	public static EdgeJobs getInstance(){
		return instance;
	}
	
	private void startSchedulers() {
				
		BukkitRunnable jpt = (BukkitRunnable) new JobPayTask();
		BukkitRunnable sjpt = (BukkitRunnable) new SidejobPayTask();
		
		this.getServer().getScheduler().runTaskTimerAsynchronously(this, jpt, 40L, 200L);
		this.getServer().getScheduler().runTaskTimerAsynchronously(this, sjpt, 40L, 250L);
		
	}
	
}
