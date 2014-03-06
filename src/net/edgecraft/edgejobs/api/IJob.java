package net.edgecraft.edgejobs.api;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * 
 * Interface for any kind of jobs
 * Every IJob is automatically an Listener
 *
 */
public interface IJob extends Listener { 
	public String getName();
	public double getPay();
	public AbstractCommand[] jobCommands();
	public void printHelp(User u);
	public void equipPlayer(Player p);
	public CuboidType whereToStart();
}
