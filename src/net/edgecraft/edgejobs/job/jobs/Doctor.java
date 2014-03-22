package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;

public class Doctor extends DressedJob {

	private static final Doctor instance = new Doctor();
	
	private Doctor() 
	{
		super( "Doctor", Color.WHITE );
	}
	
	public static final Doctor getInstance()
	{
		return instance;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void equipPlayerImpl(Player p) {
		
	}

	@Override
	public CuboidType whereToStart() {
		return CuboidType.Hospital;
	}

}
