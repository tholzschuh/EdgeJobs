package net.edgecraft.edgejobs.api;

import org.bukkit.event.Listener;

/**
 * 
 * Interface for any kind of jobs
 * Every IJob is automatically an Listener
 *
 */
interface IJob extends Listener { 
	public String getName();
	public double getLohn();
}
