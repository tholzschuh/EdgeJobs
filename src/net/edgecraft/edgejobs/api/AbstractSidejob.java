package net.edgecraft.edgejobs.api;

import net.edgecraft.edgecore.user.User;

public abstract class AbstractSidejob extends AbstractJob 
{
	public AbstractSidejob( String name, JobType type ) 
	{
		super( name, type );
	}
	
	public abstract boolean hasDoneWork( User u );
	
}
