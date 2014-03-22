package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;

public class Miner extends DressedJob {

	private static final Miner instance = new Miner();
	
	private Miner() {
		super( "Miner" );
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
	
}
