package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Timber extends AbstractJob {

	public static final Timber instance = new Timber();
	
	public static final Timber getInstance(){
		return instance;
	}
	
	private Timber() {
		super("Holzfaeller", ConfigHandler.getJobPay("Holzfaeller"));
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void printHelp(User u) {
		//TODO: Fill function
	}

	@Override
	public void equipPlayer(Player p) {
		//TODO: Fill function
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}

	@Override
	public void equipPlayerImpl(Player p) {
	}

}
