package net.edgecraft.edgejobs;

import java.util.logging.Logger;

import net.edgecraft.edgecore.EdgeCore;

import org.bukkit.plugin.java.JavaPlugin;

public class EdgeJobs extends JavaPlugin {

	public static final String banner = "[EdgeJobs] ";
	private static EdgeJobs instance;
	private static final Logger log = EdgeCore.log;
	
	@Override
	public void onLoad() {
		
		instance = this;
		
	}
	
	@Override
	public void onEnable() {
		
		log.info(EdgeJobs.banner + "EdgeJobs aktiviert");
	}
	
	@Override
	public void onDisable() {

		log.info(EdgeJobs.banner + "EdgeJobs deaktiviert");
	}
	
	public static EdgeJobs getInstance(){
		return instance;
	}
	
}
