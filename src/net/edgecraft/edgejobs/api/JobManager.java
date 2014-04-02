package net.edgecraft.edgejobs.api;

import java.util.ArrayList;
import java.util.HashMap;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.job.Job;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.entity.Player;

public class JobManager 
{
	
	private static final JobManager instance = new JobManager();
	
	private static final UserManager users = EdgeCoreAPI.userAPI();
	
	private final ArrayList<AbstractJob> jobs = new ArrayList<>();
	private final ArrayList<AbstractSidejob> sidejobs = new ArrayList<>();
	private final HashMap<Player, Boolean> isWorking = new HashMap<>();
	
	private JobManager() { /* ... */ }
	
	public static final JobManager getInstance()
	{
		return instance;
	}
	
	public void setWorking( Player p, boolean b ) 
	{
		if( p == null ) return;
		
		isWorking.put( p, b );
	}
	
	public boolean isWorking( Player p ) 
	{
		if( p == null ) return false;
		
		return isWorking.get( p );
	}
	
	public void registerJob( AbstractJob job ) 
	{
		if( job instanceof AbstractSidejob )
			sidejobs.add( (AbstractSidejob)job );
		
		else if( job instanceof AbstractJob ) 
			jobs.add( (AbstractJob)job );
		
		else return;
	}
	
	public ArrayList<AbstractJob> getJobs() 
	{
		return jobs;
	}
	
	public ArrayList<AbstractSidejob> getSidejobs() 
	{
		return sidejobs;
	}
	
	public AbstractJob getJob( AbstractJob another ) 
	{
		AbstractJob job;
		
		if( another instanceof AbstractSidejob )
			job = getSidejobByInstance( (AbstractSidejob)another );
		else
			job = getJobByInstance( another );
		
		return job;
	}
	
	public AbstractJob getJob( String name ) 
	{
		AbstractJob job = getJobByName( name );
		
		if( job == null ) job = getSidejobByName( name );
		
		return job;
	}
	
	public AbstractJob getJob( User u ) 
	{
		AbstractJob job = getJobByUser( u );
		
		if( job == null ) job = getSidejobByUser( u );
		
		return job;
	}
	
	public AbstractJob getJob( Player p ) 
	{
		if( p == null ) return null;
		
		return getJob( users.getUser( p.getName() ) );
	}
	
	public AbstractJob getJobByName( String name ) 
	{
		
		if( name == null || name.trim().length() == 0 ) return null;
		
		for( AbstractJob job : jobs ) 
			if( job.getName().equals( name ) ) return job;
		
		return null;
	}
	
	public AbstractJob getJobByInstance( AbstractJob another ) 
	{
		if( another == null ) return null;
		
		for( AbstractJob job : jobs ) 
			if( job.equals( another ) ) return job;
		
		return null;
	}
	
	public AbstractSidejob getSidejobByName( String name ) 
	{
		if( name == null || name.trim().length() == 0 ) return null;
		
		for( AbstractSidejob job : sidejobs ) 
			if( job.getName().equals( name ) ) return job;
			
		return null;
	}
	
	public final AbstractSidejob getSidejobByInstance( AbstractSidejob another ) 
	{
		if( another == null ) return null;
		
		for( AbstractSidejob job : sidejobs ) 
			if( job.equals( another ) ) return job;
		
		return null;
	}
	
	public AbstractJob getJobByUser( User u ) 
	{
		return getJobByName( ConfigHandler.getJob( u ) );
	}
	
	public AbstractSidejob getSidejobByUser( User u ) 
	{
		return getSidejobByName( ConfigHandler.getSidejob( u ) );
	}
	
	public final boolean canUse( User u, Job j ) 
	{
		if( u == null || j == null ) return false;
		if( !getJob( u ).equals( j ) ) return false;
		
		return true;
	}
}
