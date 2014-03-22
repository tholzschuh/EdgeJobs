package net.edgecraft.edgejobs.job;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgejobs.api.AbstractJob;

public abstract class DressedJob extends AbstractJob {

	protected ItemStack boots = new ItemStack( Material.LEATHER_BOOTS );
	protected ItemStack pants = new ItemStack( Material.LEATHER_LEGGINGS );
	protected ItemStack chestplate = new ItemStack( Material.LEATHER_CHESTPLATE );
	protected ItemStack helmet = new ItemStack( Material.LEATHER_HELMET );
	
	protected ArrayList<ItemStack> other;
	
	public DressedJob(String name, double pay) {
		super(name, pay);
		other = new ArrayList<>();
	}
	
	@Override
	public void equipPlayerImpl( Player p ) {

		if( p == null ) return;
		
		PlayerInventory inv = p.getInventory();
		
		inv.addItem( boots );
		inv.addItem( pants );
		inv.addItem( chestplate );
		inv.addItem( helmet );
		
		for( ItemStack stack : other ) {
			inv.addItem( stack );
		}
		
	}
	
	protected void prepareKit( String boots, String pants, String chestplate, String helmet ) {
		prepareBoots( boots );
		preparePants( pants );
		prepareChestplate( chestplate );
		prepareHelmet( helmet );
	}
	
	public void addItem( ItemStack stack ) {
		if( stack != null )
			other.add( stack );
	}
	
	protected void prepareBoots( String display ) {
		
		if( !validString( display ) ) return;
		
		boots.getItemMeta().setDisplayName( display );
	}
	
	protected void preparePants( String display ) {
		
		if( !validString( display ) ) return;
		pants.getItemMeta().setDisplayName( display );
	}
	
	protected void prepareChestplate( String display ) {
		
		if( !validString( display ) ) return;
		
		chestplate.getItemMeta().setDisplayName( display );
	}
	
	protected void prepareHelmet( String display ) {
		
		if( !validString( display ) ) return;
		
		helmet.getItemMeta().setDisplayName( display );
	}
	
	private boolean validString( String s ) {
		
		if( s == null || s.trim().length() == 0 ) return false;
		return true;
	}

}
