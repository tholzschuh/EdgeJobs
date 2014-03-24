package net.edgecraft.edgejobs.job;

import net.edgecraft.edgejobs.api.AbstractJob;

public class DefaultJob extends AbstractJob 
{
	public static final DefaultJob instance = new DefaultJob();
	
	private DefaultJob() 
	{
		super( "defaultJob" );
	}
	
	public static final DefaultJob getInstance() 
	{
		return instance;
	}
}
