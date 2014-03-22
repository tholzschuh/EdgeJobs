package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Miner extends DressedJob {

	private static final Miner instance = new Miner();
	
	private Miner() {
		super( "Miner", ConfigHandler.getJobPay( "Miner" ));
		super.prepareKit( "Miner boots", "Miner pants", "Miner chestplate", "Miner helmet" );
		super.addItem( new ItemStack( Material.IRON_PICKAXE ) );
	}
	
	public static final Miner getInstance() {
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
	public CuboidType whereToStart() {
		return null;
	}
	
	@Override
	public void onJobQuit( Player p ) {
		//TODO:
	}

}
