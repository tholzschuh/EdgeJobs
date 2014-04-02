package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobType;

public class NoJob extends AbstractJob
{
	private static final NoJob instance = new NoJob();
	
	private NoJob() 
	{
		super( "nojob", JobType.NO_JOB );
	}
	
	public static final NoJob getInstance()
	{
		return instance;
	}

}
