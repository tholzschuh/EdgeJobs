package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Blacksmith extends DressedJob 
{

	private static final Blacksmith instance = new Blacksmith();

	public static final Blacksmith getInstance()
	{
		return instance;
	}
	
	private Blacksmith() 
	{
		super( "Blacksmith", ConfigHandler.getJobPay( "Blacksmith" ) );
		super.prepareKit( "Smith boots", "Smith pants", "Smith chestplate", "Smith helmet" );
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void printHelp(User u) {
		return; //TODO
	}

	@Override
	public CuboidType whereToStart() {
		return null; //TODO:?
	}

}
