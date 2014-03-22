package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;

public class Airman extends DressedJob 
{

	private static final Airman instance = new Airman();
	
	private Airman() 
	{
		super( "Airman" );
	}
	
	public static final Airman getInstance()
	{
		return instance;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}

}
