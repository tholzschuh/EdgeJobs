package net.edgecraft.edgejobs.api;

import java.util.ArrayList;
import java.util.HashMap;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.entity.Player;

public abstract class JobManager {
	
	private static ArrayList<AbstractJob> jobs = new ArrayList<>();
	private static ArrayList<AbstractSidejob> sidejobs = new ArrayList<>();
	private static HashMap<Player, Boolean> isWorking = new HashMap<>();
	
	public static void setWorking( Player p, boolean b ) {
		if( p == null ) return;
		
		isWorking.put(p, b);
	}
	
	public static boolean isWorking( Player p ) {
		
		if( p == null ) return false;
		
		return isWorking.get( p );
	}
	
	public static void registerJob( AbstractJob job ) {
		
		if( job instanceof AbstractSidejob )
			sidejobs.add( (AbstractSidejob)job );
		
		else if( job instanceof AbstractJob ) 
			jobs.add( (AbstractJob)job );
		
	}
	
	public static ArrayList<AbstractJob> getJobs() {
		return jobs;
	}
	
	public static ArrayList<AbstractSidejob> getSidejobs() {
		return sidejobs;
	}
	
	public static AbstractJob getJob( AbstractJob another ) {
		
		AbstractJob job;
		
		if( another instanceof AbstractSidejob )
			job = getSidejobByInstance( (AbstractSidejob)another );
		else
			job = getJobByInstance( another );
		
		return job;
	}
	
	public static AbstractJob getJob( String name ) {
		
		AbstractJob job = getJobByName( name );
		
		if( job == null ) job = getSidejobByName( name );
		
		return job;
	}
	
	public static AbstractJob getJob( User u ) {
		
		AbstractJob job = getJobByUser( u );
		
		if( job == null ) job = getSidejobByUser( u );
		
		return job;
		
	}
	
	/**
	 * Get a AbstractJob by String
	 * 
	 * @param name - name ob the job
	 * @return null if not found
	 */
	public static AbstractJob getJobByName( String name ) {
		
		if( name == null || name.trim().length() == 0 ) return null;
		
		for( AbstractJob job : jobs ) {
			if( job.equals( name ) ) return job;
		}
		
		return null;
	}
	
	public static AbstractJob getJobByInstance( AbstractJob another ) {
		if( another == null ) return null;
		
		for( AbstractJob job : jobs ) {
			if( job.equals( another ) ) return job;
		}
		
		return null;
	}
	
	public static final AbstractSidejob getSidejobByInstance( AbstractSidejob another ) {
		if( another == null ) return null;
		
		for( AbstractSidejob job : sidejobs ) {
			if( job.equals( another ) ) return job;
		}
		
		return null;
	}
	
	/**
	 * Get a AbstractSidejob by String
	 * 
	 * @param name - name ob the job
	 * @return null if not found
	 */
	public static AbstractSidejob getSidejobByName( String name ) {
		
		if( name == null || name.trim().length() == 0 ) return null;
		
		for( AbstractSidejob job : sidejobs ) {
			
			if( job.equals( name ) ) return job;
			
		}
		
		return null;
	}
	
	public static AbstractJob getJobByUser( User u ) {
		return getJobByName( ConfigHandler.getJob( u ) );
	}
	
	public static AbstractSidejob getSidejobByUser( User u ) {
		return getSidejobByName( ConfigHandler.getSidejob( u ) );
	}
	
}
