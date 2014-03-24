package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Color;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.LeatherJob;

public class Doctor extends LeatherJob 
{
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
	public CuboidType whereToStart() {
		return CuboidType.Hospital;
	}

}
