package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.edgecraft.edgejobs.api.AbstractJob;

public class DrivingInstructor extends AbstractJob 
{

	private static final DrivingInstructor instance = new DrivingInstructor();
	
	private DrivingInstructor() 
	{
		super( "DrivingInstructor" );
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
