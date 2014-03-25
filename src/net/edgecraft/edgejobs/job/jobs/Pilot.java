package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;

public class Pilot extends AbstractJob 
{

	private static final Pilot instance = new Pilot();
	
	private Pilot() 
	{
		super( "Pilot" );
	}
	
	public static final Pilot getInstance()
	{
		return instance;
	}
}
