package net.edgecraft.edgejobs.job;

import net.edgecraft.edgejobs.api.AbstractJob;

public enum Job {

	DEFAULT_JOB( 0, DefaultJob.getInstance() ),
	DEFAULT_SIDEJOB( 1, DefaultSidejob.getInstance() ),
	FIREFIGHTER( 2, Firefighter.getInstance() ),
	POLICEMAN( 3, Policeman.getInstance() );
	
	private final int _id;
	private final AbstractJob _job;
	
	private Job( int id, AbstractJob job ) {
		_id = id;
		
		if( job != null ) {
			_job = job;
		} else {
			_job = DefaultJob.getInstance();
		}
		
	}
	
	public final int getID() {
		return _id;
	}
	
	public final AbstractJob getJob() {
		return _job;
	}
	
	public final Job getJob( int id ) {
		
		for( Job job : getJobs() ) {
			if( job.getID() == id ) return job;
		}
		
		return Job.DEFAULT_JOB;
	}
	
	public final Job[] getJobs() {
		return new Job[]{ Job.DEFAULT_JOB, Job.DEFAULT_SIDEJOB, Job.FIREFIGHTER, Job.POLICEMAN };
	}
	
	
		
}
