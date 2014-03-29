package net.edgecraft.edgejobs.util;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.job.jobs.NoJob;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class ConfigHandler 
{
	public static final Integer JOB_PAYHOUR = 17;
	private static final EdgeJobs instance = EdgeJobs.getInstance();
	
	public static FileConfiguration config()
	{
		return instance.getConfig();
	}
	
	private static void save() 
	{
		instance.saveConfig();
	}
	
	public static void prepare() 
	{
		config().options().copyDefaults( true );
		
		if( !config().contains( "payhour" ) ) config().addDefault( "payhour", JOB_PAYHOUR.toString() );
		if( !config().contains( "pay" ) ) prepareJobs();
		if( !config().contains( "user" ) ) config().createSection("user");
		
		ConfigHandler.save();
	}
	
	private static void prepareJobs() 
	{
		for( AbstractJob job : JobManager.getJobs() )
			config().addDefault( "pay." + job.getName(), 2000.00 );
		
		for( AbstractSidejob job : JobManager.getSidejobs() ) 
			config().addDefault( "pay." + job.getName(), 10.00 );
	}
	
	public static double getJobPay( String name )
	{
		return config().getDouble( "pay." + name );
	}
	
	public static String getPayHour()
	{
		return config().getString( "payhour" );
	}
	
	public static boolean containsUser( User u )
	{
		return config().contains( "user." + u.getID() );
	}
	
	public static void createUser( User u ) 
	{
		if( containsUser(u) ) return;
		
		config().addDefault( "user." + u.getID() + ".job", NoJob.getInstance().getName() );
	}
	
	public static void removeUser( User u ) 
	{
		if( !containsUser(u) ) return;
		
		config().set( "user." + u.getID(), null );
	}
	
	// TODO: Add here stuff for companies
	
	
	public static void setJob( User u, AbstractJob job ) 
	{
		if( u == null || job == null ) return;
		
		final String jobVar = ( job instanceof AbstractSidejob ) ? ".sidejob" : ".job";
		
		config().set( "user." + u.getID() + jobVar, job.getName() );
		ConfigHandler.save();
	}
	
	public static void setJobPay( AbstractJob job, double pay ) 
	{
		config().set( "pay." + job.getName(), pay );
		save();
	}
	
	public static String getJob( User u )
	{
		return config().getString( "user." + u.getID() + ".job" );
	}
	
	public static String getSidejob( User u )
	{
		return config().getString( "user." + u.getID() + ".sidejob" );
	}
}
