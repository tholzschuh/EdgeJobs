package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Farmer extends AbstractJob {

	private final static Farmer instance = new Farmer();
	
	private final ItemStack hoe = new ItemStack( Material.IRON_HOE ); 
	
	private Farmer() {
		super( "Farmer", ConfigHandler.getJobPay("Farmer") );
	}
	
	public static final Farmer getInstance() {
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
		p.getInventory().addItem( hoe );
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
		
		for( ItemStack stack : inv.getContents() ) {
			
			if( isWheat( stack ) ) {
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isMelon( stack ) ) {
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isPumpkin( stack ) ) {
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isMushroom( stack ) ) {
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isCocoa( stack ) ) {
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isSugar( stack ) ) {
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
		}
		
		
		//TODO:
		
	}
	
	private boolean isWheat( ItemStack stack ) {
		
		if( stack == null ) return false;

		if( stack.getType().equals( Material.WHEAT ) ) return true;
		return false;
	}
	
	private boolean isMelon( ItemStack stack ) {
		
		if( stack == null ) return false;
		
		Material m = stack.getType();
		
		if( m.equals( Material.MELON ) ) return true;
		if( m.equals( Material.MELON_BLOCK ) ) return true;
		if( m.equals( Material.MELON_STEM ) ) return true;
		
		return false;
	}
	
	private boolean isPumpkin( ItemStack stack ) {
		
		if( stack == null ) return false;
		
		Material m = stack.getType();
		
		if( m.equals( Material.PUMPKIN ) ) return true;
		
		return false;
	}
	
	private boolean isMushroom( ItemStack stack ) {
		
		if( stack == null ) return false;
		
		Material m = stack.getType();
		
		if( m.equals( Material.BROWN_MUSHROOM ) ) return true;
		if( m.equals( Material.RED_MUSHROOM ) ) return true;
		if( m.equals( Material.HUGE_MUSHROOM_1 ) ) return true;
		if( m.equals( Material.HUGE_MUSHROOM_2 ) ) return true;
		
		return false;
	}
	
	private boolean isCocoa( ItemStack stack ) {
		
		if( stack == null ) return false;
		
		Material m = stack.getType();
		
		if(m.equals( Material.COCOA ) ) return true;
		
		return false;
	}
	
	private boolean isSugar( ItemStack stack ) {
		
		if( stack == null ) return false;
		
		Material m = stack.getType();
		
		if( m.equals( Material.SUGAR ) ) return true;
		if( m.equals( Material.SUGAR_CANE ) ) return true;
		if( m.equals( Material.SUGAR_CANE_BLOCK ) ) return true;
		
		return false;
	}

}
