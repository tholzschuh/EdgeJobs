package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
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
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public void equipPlayerImpl(Player p) {
		
		p.getInventory().addItem( new ItemStack( Material.MINECART ) );
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}

}
