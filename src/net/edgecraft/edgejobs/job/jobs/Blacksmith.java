package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;

public class Blacksmith extends AbstractJob 
{

	private static final Blacksmith instance = new Blacksmith();

	public static final Blacksmith getInstance()
	{
		return instance;
	}
	
	private Blacksmith() 
	{
		super( "Blacksmith" );
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
		return null; //TODO:?
	}

}
