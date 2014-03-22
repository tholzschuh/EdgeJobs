package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;

public class Pilot extends AbstractJob 
{

	private static final Pilot instance = new Pilot();
	
	private Pilot() 
	{
		super( "Pilot" );
	}
	
	public static final Pilot getInstance()
	{
		return instance;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void equipPlayerImpl(Player p) {
		return;
	}
	
	@Override
	public CuboidType whereToStart() {
		return null;
	}
}
