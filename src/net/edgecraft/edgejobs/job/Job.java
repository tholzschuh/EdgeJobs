package net.edgecraft.edgejobs.job;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.job.jobs.Airman;
import net.edgecraft.edgejobs.job.jobs.Blacksmith;
import net.edgecraft.edgejobs.job.jobs.Broker;
import net.edgecraft.edgejobs.job.jobs.Criminal;
import net.edgecraft.edgejobs.job.jobs.Doctor;
import net.edgecraft.edgejobs.job.jobs.DrivingInstructor;
import net.edgecraft.edgejobs.job.jobs.Farmer;
import net.edgecraft.edgejobs.job.jobs.Firefighter;
import net.edgecraft.edgejobs.job.jobs.Killer;
import net.edgecraft.edgejobs.job.jobs.Miner;
import net.edgecraft.edgejobs.job.jobs.Policeman;
import net.edgecraft.edgejobs.job.jobs.Reporter;
import net.edgecraft.edgejobs.job.jobs.Sailor;
import net.edgecraft.edgejobs.job.jobs.Timber;

public enum Job {

	DEFAULT_JOB( 0, DefaultJob.getInstance() ),
	DEFAULT_SIDEJOB( 1, DefaultSidejob.getInstance() ),
	FIREFIGHTER( 2, Firefighter.getInstance() ),
	POLICEMAN( 3, Policeman.getInstance() ),
	DOCTOR( 4, Doctor.getInstance() ),
	MINER( 5, Miner.getInstance() ),
	FARMER( 6, Farmer.getInstance() ),
	TIMBER( 7, Timber.getInstance() ),
	BLACKSMITH( 8, Blacksmith.getInstance() ),
	DRIVING_INSTRUCTOR( 9, DrivingInstructor.getInstance() ),
	AIRMAN( 10, Airman.getInstance() ),
	SAILOR( 11, Sailor.getInstance() ),
	BROKER( 12, Broker.getInstance() ),
	REPORTER( 13, Reporter.getInstance() ),
	CRIMINAL( 14, Criminal.getInstance() ),
	KILLER( 15, Killer.getInstance() );
	
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
		return new Job[]{ Job.DEFAULT_JOB, Job.DEFAULT_SIDEJOB, Job.FIREFIGHTER, Job.POLICEMAN, Job.DOCTOR, Job.MINER, Job.FARMER, Job.TIMBER, Job.BLACKSMITH, Job.DRIVING_INSTRUCTOR, Job.AIRMAN, Job.SAILOR, Job.BROKER, Job.REPORTER, Job.CRIMINAL, Job.KILLER };
	}
	
	
		
}
