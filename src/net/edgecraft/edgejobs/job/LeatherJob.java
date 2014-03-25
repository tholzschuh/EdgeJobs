package net.edgecraft.edgejobs.job;

import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherJob extends DressedJob
{

	protected final Color color;
	
	public LeatherJob( String name, Color c ) 
	{
		super( name );
		color = c;
	}
	
	@Override 
	protected void prepareBoots( String display )
	{
		super.prepareBoots( display );
		
		((LeatherArmorMeta)_boots.getItemMeta()).setColor( color );
	}

	@Override
	protected void preparePants( String display )
	{
		super.preparePants( display );
		((LeatherArmorMeta)_pants.getItemMeta()).setColor( color );
	}
	
	@Override
	protected void prepareChestplate( String display )
	{
		super.prepareChestplate( display );
		((LeatherArmorMeta)_chestplate.getItemMeta()).setColor( color );
	}
	
	@Override
	protected void prepareHelmet( String display )
	{
		super.prepareHelmet( display );
		((LeatherArmorMeta)_helmet.getItemMeta()).setColor( color );
	}
}
