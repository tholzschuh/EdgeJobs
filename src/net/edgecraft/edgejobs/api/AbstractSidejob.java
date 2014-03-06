package net.edgecraft.edgejobs.api;

import net.edgecraft.edgecore.user.User;

public abstract class AbstractSidejob extends AbstractJob {
	
	public AbstractSidejob(String name, double pay) {
		super( name, pay );
	}
	
	public abstract boolean hasDoneWork(User u);
	
}
