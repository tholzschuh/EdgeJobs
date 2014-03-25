package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;

public class Broker extends AbstractJob 
{
	private static final Broker instance = new Broker();
	
	private Broker() 
	{
		super( "Broker" );
	}
	
	public static final Broker getInstance()
	{
		return instance;
	}
}
