package net.edgecraft.edgejobs.api;

import net.edgecraft.edgecore.user.User;

public abstract class AbstractJob implements IJob {

	private String name;
	private double pay;
	
	public AbstractJob(String name, double pay) {
		this.name = name;
		this.pay = pay;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public final double getPay(){
		return this.pay;
	}
	
	public abstract void doWork(User u);
	
}
