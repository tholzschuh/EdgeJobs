package net.edgecraft.edgejobs.api;

import java.util.ArrayList;

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
	
	/**
	 * Get a AbstractJob by String
	 * 
	 * @param name - name ob the job
	 * @return null if not found
	 */
	public AbstractJob getJobByName(String name){
		
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
	public AbstractSidejob getSidejobByName(String name){
		
		for(int i = 0; i < jobs.size(); i++){
			
			AbstractSidejob job = sidejobs.get(i);
			
			if(job.getName().equalsIgnoreCase(name)) return job;
			
		}
		
		return null;
	}
	
}
