package net.edgecraft.edgejobs.job;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.edgecraft.edgejobs.api.AbstractJob;

public abstract class DressedJob extends AbstractJob {

	protected ItemStack boots = new ItemStack( Material.LEATHER_BOOTS );
	protected ItemStack pants = new ItemStack( Material.LEATHER_LEGGINGS );
	protected ItemStack chestplate = new ItemStack( Material.LEATHER_CHESTPLATE );
	protected ItemStack helmet = new ItemStack( Material.LEATHER_HELMET );
	protected Color color;
	
	protected ArrayList<ItemStack> other;
	
	public DressedJob( String name, Color color ) {
		super( name );
		prepareKit();
		other = new ArrayList<>();
		this.color = color;
	}
	
	@Override
	public void equipPlayerImpl( Player p ) {

		if( p == null ) return;
		
		PlayerInventory inv = p.getInventory();
		
		inv.setBoots( boots );
		inv.setLeggings( pants );
		inv.setChestplate( chestplate );
		inv.setHelmet( helmet );
		
		for( ItemStack stack : other ) {
			inv.addItem( stack );
		}
		

		
	}
	
	private void prepareKit()
	{
		prepareKit( getName() + " boots", getName() + " pants", getName() + " chestplate", getName() + " helmet");
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
		
		final LeatherArmorMeta bootsmeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsmeta.setDisplayName( display );
		bootsmeta.setColor( color );
	}
	
	protected void preparePants( String display ) {
		
		if( !validString( display ) ) return;
		
		final LeatherArmorMeta pantsmeta = (LeatherArmorMeta) pants.getItemMeta();
		pantsmeta.setDisplayName( display );
		pantsmeta.setColor( color );
		
	}
	
	protected void prepareChestplate( String display ) {
		
		if( !validString( display ) ) return;
		
		final LeatherArmorMeta chestmeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestmeta.setDisplayName( display );
		chestmeta.setColor( color );
	}
	
	protected void prepareHelmet( String display ) {
		
		if( !validString( display ) ) return;
		
		final LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();
		helmetmeta.setDisplayName( display );
		helmetmeta.setColor( color );
	}
	
	private boolean validString( String s ) {
		
		if( s == null || s.trim().length() == 0 ) return false;
		return true;
	}

}
