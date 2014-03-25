package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;

public class Blacksmith extends AbstractJob 
{

	private static final Blacksmith instance = new Blacksmith();

	public static final Blacksmith getInstance()
	{
		return instance;
	}
	
	private Blacksmith() 
	{
		super( "Blacksmith" );
	}
}
