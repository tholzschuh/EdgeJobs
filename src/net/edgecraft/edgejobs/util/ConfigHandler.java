package net.edgecraft.edgejobs.util;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.JobManager;

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
		
		if(!config().contains("pay")) config().addDefault("payhour", "17");
		if(!config().contains("pay")) prepareJobs();
		if(!config().contains("user")) config().createSection("user");
		
		
		save();
	}
	
	private static void prepareJobs(){
		
		for(AbstractJob job : JobManager.getJobs()){
			
			config().addDefault("pay." + job.getName(), "2000");
			
		}
		
		for(AbstractSidejob job : JobManager.getSidejobs()){
			
			config().addDefault("pay." + job.getName(), "10");
			
		}
		
	}
	
	public static String getPayHour(){
		return config().getString("payhour");
	}
	
	public static boolean containsUser(User u){
		return config().contains("user." + u.getID());
	}
	
	public static void createUser(User u){
		
		if(containsUser(u)) return;
		
		config().addDefault("user." + u.getID() + ".job", "none");
		config().addDefault("user." + u.getID() + ".job", "none");
		config().addDefault("user." + u.getID() + ".job", "none");
		
	}
	
	public static void removeUser(User u){
		
		if(!containsUser(u)) return;
		
		config().set("user." + u.getID(), null);
		
	}
	
	// TODO: Add here stuff for companies
	
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
	public static void setSidejob(User u, AbstractSidejob job){
		
		if(job == null){
			
			config().set("user." + u.getID() + ".sidejob", "none");
			
		}
		else{
			
			config().set("user." + u.getID() + ".sidejob", job.getName());
			
		}
		
		save();
	}
	
	public static void setJobPay(AbstractJob job, double pay){
		
		config().set("pay." + job.getName()	, pay);
		
		save();
	}
	
	public static void setSidejobPay(AbstractSidejob job, double pay){
		
		config().set("pay." + job.getName()	, pay);
		
		save();
	}
	
	public static String getJob(User u){
		return config().getString("user." + u.getID() + ".job");
	}
	
	public static String getSidejob(User u){
		return config().getString("user." + u.getID() + ".sidejob");
	}
	
}
