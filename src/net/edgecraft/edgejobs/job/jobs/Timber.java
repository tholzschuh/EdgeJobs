package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Timber extends AbstractJob {

	public static final Timber instance = new Timber();
	
	private final ItemStack boots = new ItemStack( Material.LEATHER_BOOTS );
	private final ItemStack pants = new ItemStack( Material.LEATHER_LEGGINGS );
	private final ItemStack chestplate = new ItemStack( Material.LEATHER_CHESTPLATE );
	private final ItemStack helmet = new ItemStack( Material.LEATHER_HELMET );
	private final ItemStack axe = new ItemStack( Material.IRON_AXE );
	
	private Timber() {
		super("Timber", ConfigHandler.getJobPay("Timer"));
		prepareKit();
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

	private void prepareKit() {

		final LeatherArmorMeta bootsmeta = (LeatherArmorMeta)boots.getItemMeta();
		bootsmeta.setDisplayName( "Timber boots" );
		
		final LeatherArmorMeta pantsmeta = (LeatherArmorMeta)pants.getItemMeta();
		pantsmeta.setDisplayName( "Timber pants" );
		
		final LeatherArmorMeta chestmeta = (LeatherArmorMeta)chestplate.getItemMeta();
		chestmeta.setDisplayName( "Timber chestplate" );
		
		final LeatherArmorMeta helmetmeta = (LeatherArmorMeta)helmet.getItemMeta();
		helmetmeta.setDisplayName( "Timber helmet" );
	}
	
	@Override
	public void equipPlayerImpl(Player p) {
		PlayerInventory inv = p.getInventory();
		
		inv.addItem( boots );
		inv.addItem( pants );
		inv.addItem( chestplate );
		inv.addItem( helmet );
		inv.addItem( axe );
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
