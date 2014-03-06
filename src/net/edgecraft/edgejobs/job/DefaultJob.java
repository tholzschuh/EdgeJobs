package net.edgecraft.edgejobs.job;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;

public class DefaultJob extends AbstractJob {

	public static final DefaultJob instance = new DefaultJob();
	
	private DefaultJob( ) {
		super( "defaultJob", 0 );
	}
	
	public static final DefaultJob getInstance() {
		return instance;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return null;
	}

	@Override
	public void printHelp(User u) {
		return;
	}

	@Override
	public void equipPlayer(Player p) {
		return;
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}

}
