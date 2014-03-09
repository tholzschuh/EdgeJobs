package net.edgecraft.edgejobs.job;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.job.jobs.Broker;
import net.edgecraft.edgejobs.job.jobs.Criminal;
import net.edgecraft.edgejobs.job.jobs.Firefighter;
import net.edgecraft.edgejobs.job.jobs.Killer;
import net.edgecraft.edgejobs.job.jobs.Policeman;
import net.edgecraft.edgejobs.job.jobs.Timber;

public enum Job {

	DEFAULT_JOB( 0, DefaultJob.getInstance() ),
	DEFAULT_SIDEJOB( 1, DefaultSidejob.getInstance() ),
	FIREFIGHTER( 2, Firefighter.getInstance() ),
	POLICEMAN( 3, Policeman.getInstance() ),
	CRIMINAL( 4, Criminal.getInstance() ),
	KILLER( 5, Killer.getInstance() ),
	TIMBER( 6, Timber.getInstance() ),
	BROKER( 7, Broker.getInstance() );
	
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
	
	public final static Job[] getJobs() {
		return new Job[]{ Job.DEFAULT_JOB, Job.DEFAULT_SIDEJOB, Job.FIREFIGHTER, Job.POLICEMAN, Job.CRIMINAL, Job.KILLER, Job.TIMBER, Job.BROKER };
	}
	
	
		
}
