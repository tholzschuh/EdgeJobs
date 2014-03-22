package net.edgecraft.edgejobs.job;

import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractSidejob;

public class DefaultSidejob extends AbstractSidejob {

	public static final DefaultSidejob instance = new DefaultSidejob();
	
	private DefaultSidejob() {
		super( "defaultSidejob" );
	}

	public static final DefaultSidejob getInstance() {
		return instance;
	}
	
	@Override
	public boolean hasDoneWork(User u) {
		return true;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return null;
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
