package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;

public class Reporter extends AbstractJob 
{

	private static final Reporter instance = new Reporter();
	
	private Reporter() 
	{
		super( "Reporter" );
	}
	
	public static final Reporter getInstance()
	{
		return instance;
	}
}
