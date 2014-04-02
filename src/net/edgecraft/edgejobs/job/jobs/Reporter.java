package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobType;

public class Reporter extends AbstractJob 
{

	private static final Reporter instance = new Reporter();
	
	private Reporter() 
	{
		super( "Reporter", JobType.REPORTER );
	}
	
	public static final Reporter getInstance()
	{
		return instance;
	}
}
