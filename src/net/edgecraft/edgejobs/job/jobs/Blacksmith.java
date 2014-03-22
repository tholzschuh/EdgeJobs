package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;

public class Blacksmith extends DressedJob 
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
	public CuboidType whereToStart() {
		return null; //TODO:?
	}

}
