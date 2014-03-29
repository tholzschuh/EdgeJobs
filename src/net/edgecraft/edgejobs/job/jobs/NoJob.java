package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;

public class NoJob extends AbstractJob
{
	private static final NoJob instance = new NoJob();
	
	private NoJob() 
	{
		super( "nojob" );
	}
	
	public static final NoJob getInstance()
	{
		return instance;
	}

}
