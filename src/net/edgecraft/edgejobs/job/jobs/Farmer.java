package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgecuboid.other.EdgeItemStack;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.partitions.Partition;
import net.edgecraft.edgejobs.partitions.PartitionManager;

public class Farmer extends AbstractJob 
{
	private final static Farmer instance = new Farmer();
	
	private static final UserManager users = EdgeCoreAPI.userAPI();
	
	private final ItemStack _hoe = new ItemStack( Material.IRON_HOE ); 
	
	private Farmer() 
	{
		super( "Farmer" );
	}
	
	public static final Farmer getInstance()
	{
		return instance;
	}

	@Override
	public void equipPlayerImpl( Player p ) 
	{
		p.getInventory().addItem( _hoe );
	}
	
	@Override 
	public void onJobQuit( Player p ) 
	{
		if( p == null ) return;
		
		final PlayerInventory inv = p.getInventory();
		
		final ArrayList<ItemStack> stuff = new ArrayList<>();
		
		for( ItemStack stack : inv.getContents() ) 
		{
			if( isWheat( stack ) ) 
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isMelon( stack ) ) 
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isPumpkin( stack ) ) 
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isMushroom( stack ) ) 
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isCocoa( stack ) ) 
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
			if( isSugar( stack ) ) 
			{
				stuff.add( stack );
				inv.remove( stack );
				continue;
			}
			
		}
		
		final Partition partition = PartitionManager.getPartitionByParticipant( users.getUser( p.getName() ) );
		
		for( ItemStack stack : stuff )
		{
			partition.getStore().addItem( new EdgeItemStack( stack ), 0 );
		}
		return;	
		
	}
	
	private boolean isWheat( ItemStack stack ) 
	{
		if( stack == null ) return false;

		if( stack.getType().equals( Material.WHEAT ) ) return true;
		return false;
	}
	
	private boolean isMelon( ItemStack stack ) 
	{
		if( stack == null ) return false;
		
		final Material m = stack.getType();
		
		if( m.equals( Material.MELON ) ) return true;
		if( m.equals( Material.MELON_BLOCK ) ) return true;
		if( m.equals( Material.MELON_STEM ) ) return true;
		
		return false;
	}
	
	private boolean isPumpkin( ItemStack stack ) 
	{
		if( stack == null ) return false;
		
		final Material m = stack.getType();
		
		if( m.equals( Material.PUMPKIN ) ) return true;
		
		return false;
	}
	
	private boolean isMushroom( ItemStack stack ) 
	{
		if( stack == null ) return false;
		
		final Material m = stack.getType();
		
		if( m.equals( Material.BROWN_MUSHROOM ) ) return true;
		if( m.equals( Material.RED_MUSHROOM ) ) return true;
		if( m.equals( Material.HUGE_MUSHROOM_1 ) ) return true;
		if( m.equals( Material.HUGE_MUSHROOM_2 ) ) return true;
		
		return false;
	}
	
	private boolean isCocoa( ItemStack stack ) 
	{
		if( stack == null ) return false;
		
		final Material m = stack.getType();
		
		if(m.equals( Material.COCOA ) ) return true;
		
		return false;
	}
	
	private boolean isSugar( ItemStack stack ) 
	{
		if( stack == null ) return false;
		
		final Material m = stack.getType();
		
		if( m.equals( Material.SUGAR ) ) return true;
		if( m.equals( Material.SUGAR_CANE ) ) return true;
		if( m.equals( Material.SUGAR_CANE_BLOCK ) ) return true;
		
		return false;
	}

}
