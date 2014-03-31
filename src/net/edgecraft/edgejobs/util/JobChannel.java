package net.edgecraft.edgejobs.util;

import org.bukkit.Material;

import net.edgecraft.edgecore.chat.Channel;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.job.Job;

public class JobChannel extends Channel 
{
	private AbstractJob _requiredJob;
	
	public JobChannel(String name, boolean listed, Material requiredItem, Job requiredJob) 
	{
		super( name, listed, requiredItem );

		setRequiredJob( requiredJob.getJob() );
	}
	
	@Override
	public boolean addMember( User u ) 
	{
		if( JobManager.getJob( u ).equals( _requiredJob ) )
			return super.addMember( u );
		
		return false;
	}
	
	private final void setRequiredJob( AbstractJob requiredJob ) 
	{
		if( requiredJob == null ) return;
		
		_requiredJob = requiredJob;
	}
	
	public final AbstractJob getRequiredJob() 
	{
		return _requiredJob;
	}

}
