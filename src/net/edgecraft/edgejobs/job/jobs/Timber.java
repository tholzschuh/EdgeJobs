package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Timber extends DressedJob {

	public static final Timber instance = new Timber();
	
	private final ItemStack axe = new ItemStack( Material.IRON_AXE );
	
	private Timber() {
		super("Timber", ConfigHandler.getJobPay("Timer"));
		super.prepareKit( "Timber boots", "Timber pants", "Timber chestplate", "Timber helmet" );
		super.addItem( axe );
	}
	
	public static final Timber getInstance(){
		return instance;
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
	public CuboidType whereToStart() {
			return null;
	}
	
	@Override
	public void onJobQuit( Player p ) {
		final PlayerInventory inv = p.getInventory();
		
		ArrayList<ItemStack> wood = new ArrayList<>();
		
		for( ItemStack stack : inv.getContents() ) {
			
			if( isWood(stack) ) {
				wood.add(stack);
				inv.remove(stack);
			}
		}
		
		// TODO
	}
	
	
	private boolean isWood( ItemStack stack ) {
		return isWood( stack.getType() );
	}
	private boolean isWood( Material m ) {
		
		if( m == null ) return false;
		
		if( m.equals(Material.WOOD ) ) return true;
		
		return false;
	}
}
