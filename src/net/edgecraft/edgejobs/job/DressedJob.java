package net.edgecraft.edgejobs.job;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgejobs.api.AbstractJob;

public abstract class DressedJob extends AbstractJob 
{
	protected ItemStack _boots = new ItemStack( Material.LEATHER_BOOTS );
	protected ItemStack _pants = new ItemStack( Material.LEATHER_LEGGINGS );
	protected ItemStack _chestplate = new ItemStack( Material.LEATHER_CHESTPLATE );
	protected ItemStack _helmet = new ItemStack( Material.LEATHER_HELMET );

	
	protected final ArrayList<ItemStack> other;
	
	public DressedJob( String name ) 
	{
		super( name );
		prepareKit();
		other = new ArrayList<>();
	}
	
	@Override
	public void equipPlayerImpl( Player p ) 
	{
		if( p == null ) return;
		
		PlayerInventory inv = p.getInventory();
		
		inv.setBoots( _boots );
		inv.setLeggings( _pants );
		inv.setChestplate( _chestplate );
		inv.setHelmet( _helmet );
		
		for( ItemStack stack : other ) 
			inv.addItem( stack );
	}
	
	protected void prepareKit()
	{
		prepareKit( getName() + " boots", getName() + " pants", getName() + " chestplate", getName() + " helmet");
	}
	
	protected void prepareKit( String boots, String pants, String chestplate, String helmet ) 
	{
		prepareBoots( boots );
		preparePants( pants );
		prepareChestplate( chestplate );
		prepareHelmet( helmet );
	}
	
	public void addItem( ItemStack stack ) 
	{
		if( stack != null )
			other.add( stack );
	}
	
	protected void prepareBoots( String display ) 
	{
		if( !validString( display ) ) return;
		
		_boots.getItemMeta().setDisplayName( display );
	}
	
	protected void preparePants( String display ) 
	{
		if( !validString( display ) ) return;
		
		_pants.getItemMeta().setDisplayName( display );
	}
	
	protected void prepareChestplate( String display ) 
	{
		if( !validString( display ) ) return;

		_chestplate.getItemMeta().setDisplayName( display );
	}
	
	protected void prepareHelmet( String display ) 
	{
		if( !validString( display ) ) return;
		
		_helmet.getItemMeta().setDisplayName( display );
	}
	
	public boolean validString( String s ) 
	{
		if( s == null || s.trim().length() == 0 ) return false;
		return true;
	}

}
