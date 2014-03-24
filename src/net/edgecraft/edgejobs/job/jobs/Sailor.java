package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;

public class Sailor extends AbstractJob 
{
	
	private static final Sailor instance = new Sailor();

	private Sailor() 
	{
		super( "Sailor" );
	}

	public static final Sailor getInstance()
	{
		return instance;
	}
}
