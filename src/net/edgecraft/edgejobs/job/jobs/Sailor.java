package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Sailor extends AbstractJob 
{
	
	private static final Sailor instance = new Sailor();

	private Sailor() {
		super( "Sailor", ConfigHandler.getJobPay( "Sailor" ) );
	}

	public static final Sailor getInstance()
	{
		return instance;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void printHelp(User u) {
		return;
	}

	@Override
	public void equipPlayerImpl(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}
	
}
