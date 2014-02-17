package net.edgecraft.edgejobs.api;

import java.util.ArrayList;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.util.ConfigHandler;

public abstract class JobManager {

	private static ArrayList<AbstractJob> jobs = new ArrayList<>();
	private static ArrayList<AbstractSidejob> sidejobs = new ArrayList<>();
	
	public static void registerJob(IJob job){
		
		if(job instanceof AbstractJob){
			jobs.add((AbstractJob) job);
		}
		else if(job instanceof AbstractSidejob){
			sidejobs.add((AbstractSidejob) job);
		}
		else{
			return;
		}
		
	}
	
	public static ArrayList<AbstractJob> getJobs(){
		return jobs;
	}
	
	public static ArrayList<AbstractSidejob> getSidejobs(){
		return sidejobs;
	}
	
	/**
	 * Get a AbstractJob by String
	 * 
	 * @param name - name ob the job
	 * @return null if not found
	 */
	public static AbstractJob getJobByName(String name){
		
		for(int i = 0; i < jobs.size(); i++){
			
			AbstractJob job = jobs.get(i);
			
			if(job.getName().equalsIgnoreCase(name)) return job;
			
		}
		
		return null;
	}
	
	/**
	 * Get a AbstractSidejob by String
	 * 
	 * @param name - name ob the job
	 * @return null if not found
	 */
	public static AbstractSidejob getSidejobByName(String name){
		
		for(int i = 0; i < jobs.size(); i++){
			
			AbstractSidejob job = sidejobs.get(i);
			
			if(job.getName().equalsIgnoreCase(name)) return job;
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param u - User
	 * @return null if not found
	 */
	public static AbstractJob getUserJob(User u){
		return getJobByName(ConfigHandler.getJob(u));
	}
	
	/**
	 * 
	 * @param u - User
	 * @return null if not found
	 */
	public static AbstractSidejob getUserSidejob(User u){
		return getSidejobByName(ConfigHandler.getSidejob(u));
	}
	
}
