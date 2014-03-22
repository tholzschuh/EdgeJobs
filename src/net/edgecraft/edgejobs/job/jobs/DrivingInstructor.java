package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class DrivingInstructor extends AbstractJob 
{

	private static final DrivingInstructor instance = new DrivingInstructor();
	
	private DrivingInstructor() 
	{
		super( "DrivingInstructor", ConfigHandler.getJobPay("DrivingInstructor") );
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
	public void printHelp(User u) {
		return;
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
