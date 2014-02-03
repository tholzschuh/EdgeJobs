package net.edgecraft.edgejobs.util;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractNebenjob;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class ConfigHandler {

	public static FileConfiguration config(){
		return EdgeJobs.getInstance().getConfig();
	}
	
	private static void save(){
		EdgeJobs.getInstance().saveConfig();
	}
	
	public static void prepare(){
		
		config().options().copyDefaults(true);
		config().options().header("User values der Nebenjobs und Datenbank der Jobs der User");
		
		if(!config().contains("gehalt")) prepareJobs(); // Gehälter der Jobs festlegen
		if(!config().contains("user")) config().createSection("user"); // Job der User
		if(!config().contains("nebenjob")) config().createSection("nebenjob"); // Aktuelles Gehalt der Nebenjobs der User
		
		save();
	}
	
	@SuppressWarnings("TODO: Fill function")
	private static void prepareJobs(){
		
	}
	
	/**
	 * @param u - User
	 * @param job - Set null for 'none'
	 */
	public static void setJob(User u, AbstractJob job){
		
		if(job == null){
			
			config().set("user." + u.getID() + ".job", "none");
			
		}
		else{
			
			config().set("user." + u.getID() + ".job", job.getName());
			
		}
		
		save();
	}
	
	/**
	 * @param u - User
	 * @param job - Set null for 'none'
	 */
	public static void setNebenjob(User u, AbstractNebenjob job){
		
		if(job == null){
			
			config().set("user." + u.getID() + ".nebenjob", "none");
			
		}
		else{
			
			config().set("user." + u.getID() + ".nebenjob", job.getName());
			
		}
		
		save();
	}
	
	public static String getJob(User u){
		return config().getString("user." + u.getID() + ".job");
	}
	
	public static String getNebenjob(User u){
		return config().getString("user." + u.getID() + ".nebenjob");
	}
	
}
