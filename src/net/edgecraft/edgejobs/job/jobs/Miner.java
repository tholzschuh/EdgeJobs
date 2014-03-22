package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;

public class Miner extends AbstractJob {

	private static final Miner instance = new Miner();
	
	private final ItemStack pickaxe = new ItemStack( Material.IRON_PICKAXE );
	private final ItemStack boots = new ItemStack( Material.IRON_BOOTS );
	private final ItemStack pants = new ItemStack( Material.IRON_LEGGINGS );
	private final ItemStack chestplate = new ItemStack( Material.IRON_CHESTPLATE );
	private final ItemStack helmet = new ItemStack( Material.IRON_HELMET );
	
	private Miner() {
		super( "Miner" );
		prepareKit();
	}
	
	public static final Miner getInstance() {
		return instance;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{};
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}
	
	@Override
	public void onJobQuit( Player p ) {
		
		if( p == null ) return;
		
		final PlayerInventory inv = p.getInventory();
		final ArrayList<ItemStack> stuff = new ArrayList<>();
		
		for( ItemStack stack : inv.getContents() )
		{
			if( take( stack ) )
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
		}
		
	}

	private boolean take( ItemStack stack )
	{
		if( stack == null ) return false;
		
		Material m = stack.getType();
		
		if( m.equals( Material.COBBLESTONE ) ) return true;
		if( m.equals( Material.IRON_ORE ) ) return true;
		if( m.equals( Material.COAL_ORE ) ) return true;
		if( m.equals( Material.DIAMOND ) ) return true;
		if( m.equals( Material.DIRT ) ) return true;
		if( m.equals( Material.EMERALD_ORE ) ) return true;
		if( m.equals( Material.GOLD_ORE ) ) return true;
		if( m.equals( Material.LAPIS_ORE ) ) return true;
		if( m.equals( Material.QUARTZ_ORE ) ) return true;
		
		return false;
	}

	private void prepareKit()
	{
		boots.getItemMeta().setDisplayName( "Miner boots" );
		pants.getItemMeta().setDisplayName( "Miner Pants" );
		chestplate.getItemMeta().setDisplayName( "Miner chestplate" );
		helmet.getItemMeta().setDisplayName( "Miner helmet" );
	}
	
	@Override
	public void equipPlayerImpl(Player p) {
		
		PlayerInventory inv = p.getInventory();
		
		inv.setBoots( boots );
		inv.setLeggings( pants );
		inv.setChestplate( chestplate );
		inv.setHelmet( helmet );
		inv.addItem( pickaxe );
	}
	
}
