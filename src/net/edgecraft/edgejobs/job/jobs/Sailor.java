package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobType;

public class Sailor extends AbstractJob 
{
	
	private static final Sailor instance = new Sailor();
	
	private final ItemStack boat = new ItemStack( Material.BOAT );
	
	private Sailor() 
	{
		super( "Sailor", JobType.SAILOR );
	}

	public static final Sailor getInstance()
	{
		return instance;
	}
	
	@Override
	public void equipPlayerImpl( Player p )
	{
		if( p == null ) return;
		p.getInventory().addItem( boat );
	}
}
