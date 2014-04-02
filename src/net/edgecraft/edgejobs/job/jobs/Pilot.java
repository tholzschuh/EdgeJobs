package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobType;

public class Pilot extends AbstractJob 
{

	private static final Pilot instance = new Pilot();
	
	private Pilot() 
	{
		super( "Pilot", JobType.AIRMAN );
	}
	
	public static final Pilot getInstance()
	{
		return instance;
	}
}
