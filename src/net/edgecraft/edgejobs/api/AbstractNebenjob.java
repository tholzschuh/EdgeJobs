package net.edgecraft.edgejobs.api;

import net.edgecraft.edgecore.user.User;

public abstract class AbstractNebenjob implements IJob {

	private String name;
	private double lohn;
	
	public AbstractNebenjob(String name, double lohn) {
		this.name = name;
		this.lohn = lohn;
	}
	
	public String getName(){
		return this.name;
	}
	
	public final double getLohn(){
		return this.lohn;
	}
	
	/**
	 * Führe hier Aktionen innerhalb des Jobs aus
	 */
	public abstract void doWork(User u);
	
}
