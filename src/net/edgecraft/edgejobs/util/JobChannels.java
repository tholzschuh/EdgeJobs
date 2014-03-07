package net.edgecraft.edgejobs.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import net.edgecraft.edgejobs.job.Job;

public enum JobChannels {

	DEFAULT_JOB_CHANNEL( 0, new JobChannel( "Default Job", false, null, Job.DEFAULT_JOB ) ),
	DEFAULT_SIDEJOB_CHANNEL( 1, new JobChannel( "Default Sidejob", false, null, Job.DEFAULT_SIDEJOB ) ),
	FIREFIGHTER_CHANNEL( 2, new JobChannel( "Firefighter", false, Material.FLINT, Job.FIREFIGHTER )),
	POLICEMAN_CHANNEL( 3, new JobChannel( "Policemen", false, Material.FLINT, Job.POLICEMAN ) ),
	KILLER_CHANNEL( 4, new JobChannel( "Killer", false, Material.FLINT, Job.KILLER ) );
	
	private final int _id;
	private final JobChannel _channel;
	
	private JobChannels( int id, JobChannel channel ) {
		
		_id = id;
		
		if( channel == null ) _channel = new JobChannel( "Default Job", false, null, Job.DEFAULT_JOB ); //TODO: impr?
		else
			_channel = channel;
	}	
	
	public final int getID() {
		return _id;
	}
	
	public final JobChannel getChannel() {
		return _channel;
	}
	
	private final JobChannels[] getJobChannels() {
		return new JobChannels[]{ JobChannels.DEFAULT_JOB_CHANNEL, JobChannels.DEFAULT_SIDEJOB_CHANNEL, JobChannels.FIREFIGHTER_CHANNEL, JobChannels.POLICEMAN_CHANNEL };
	}
	
	public final List<JobChannel> getChannelsList() {
		
		ArrayList< JobChannel > jobChannels = new ArrayList<JobChannel>();
		
		for( JobChannels tmp : getJobChannels() ) {
			jobChannels.add( tmp.getChannel() );
		}
		
		return jobChannels;
	}
	
	public final JobChannel[] getChannels() {
		
		return (JobChannel[])getChannelsList().toArray();
	}
	
}
