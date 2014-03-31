package net.edgecraft.edgejobs.job;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.job.jobs.NoJob;
import net.edgecraft.edgejobs.job.jobs.Pilot;
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

public enum Job 
{
	NO_JOB( 1, NoJob.getInstance() ),
	FIREFIGHTER( 2, Firefighter.getInstance() ),
	POLICEMAN( 3, Policeman.getInstance() ),
	DOCTOR( 4, Doctor.getInstance() ),
	MINER( 5, Miner.getInstance() ),
	FARMER( 6, Farmer.getInstance() ),
	TIMBER( 7, Timber.getInstance() ),
	BLACKSMITH( 8, Blacksmith.getInstance() ),
	DRIVING_INSTRUCTOR( 9, DrivingInstructor.getInstance() ),
	AIRMAN( 10, Pilot.getInstance() ),
	SAILOR( 11, Sailor.getInstance() ),
	BROKER( 12, Broker.getInstance() ),
	REPORTER( 13, Reporter.getInstance() ),
	CRIMINAL( 14, Criminal.getInstance() ),
	KILLER( 15, Killer.getInstance() );
	
	private final int _id;
	private final AbstractJob _job;
	
	private Job( int id, AbstractJob job ) 
	{
		_id = id;
		
		if( job != null ) 
			_job = job;
		else 
			_job = NoJob.getInstance();
	}
	
	public final int getID() 
	{
		return _id;
	}
	
	public final AbstractJob getJob() 
	{
		return _job;
	}
	
	public final Job getJob( int id ) 
	{
		
		for( Job job : getJobs() ) 
			if( job.getID() == id ) return job;
		
		return Job.NO_JOB;
	}
	
	public final static Job[] getJobs() 
	{
		return new Job[]{ Job.NO_JOB, Job.FIREFIGHTER, Job.POLICEMAN, Job.DOCTOR, Job.MINER, Job.FARMER, Job.TIMBER, Job.BLACKSMITH, Job.DRIVING_INSTRUCTOR, Job.AIRMAN, Job.SAILOR, Job.BROKER, Job.REPORTER, Job.CRIMINAL, Job.KILLER };
	}
	
	public final static void registerJobs()
	{
		for( Job j : getJobs() )
		{
			JobManager.registerJob( j.getJob() );
		}
		return;
	}
		
}
