package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.api.AbstractSidejob;

public class DefaultSidejob extends AbstractSidejob 
{
	public static final DefaultSidejob instance = new DefaultSidejob();
	
	private DefaultSidejob() 
	{
		super( "defaultSidejob" );
	}

	public static final DefaultSidejob getInstance() 
	{
		return instance;
	}
	
	@Override
	public boolean hasDoneWork( User u ) 
	{
		return true;
	}
}
