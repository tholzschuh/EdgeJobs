package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Broker extends AbstractJob {

	private static final Broker instance = new Broker();
	
	public static final Broker getInstance(){
		return instance;
	}
	
	private Broker() {
		super("Makler", ConfigHandler.getJobPay("Makler"));
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void printHelp(User u) {
	}

	@Override
	public void equipPlayerImpl(Player p) {
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}
	
}
