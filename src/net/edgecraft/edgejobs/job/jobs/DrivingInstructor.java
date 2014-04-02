package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobType;

public class DrivingInstructor extends AbstractJob 
{

	private static final DrivingInstructor instance = new DrivingInstructor();
	
	private DrivingInstructor() 
	{
		super( "DrivingInstructor", JobType.DRIVING_INSTRUCTOR );
	}
	
	public static final DrivingInstructor getInstance()
	{
		return instance;
	}

	@Override
	public void equipPlayerImpl( Player p ) 
	{
		p.getInventory().addItem( new ItemStack( Material.MINECART ) );
	}
}
