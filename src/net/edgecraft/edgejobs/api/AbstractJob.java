package net.edgecraft.edgejobs.api;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.util.ConfigHandler;


public abstract class AbstractJob 
{
	
	private static final JobManager jobs = EdgeJobs.getJobs();

	private static final HashMap<Player, PlayerInventory> inventories = new HashMap<>();
	
	private String _name;
	private double _pay;
	private JobType _type;
	
	public AbstractJob( String name, JobType type ) 
	{
		setName( name );
		setPay( ConfigHandler.getJobPay( name ) );
		setType( type );
	}
	
	public final String getName() 
	{
		return _name;
	}
	
	public final double getPay()
	{
		return _pay;
	}
	
	public JobType getType()
	{
		return _type;
	}
	
	private final void setName( String name ) 
	{
		if( name == null || name.trim().length() == 0 ) return;
		
		_name = name;
	}
	
	private final void setPay( double pay ) 
	{
		_pay = Math.abs( pay );
	}
	
	private void setType( JobType type )
	{
		_type = type;
	}
	
	// Optional Override-Possibility
	public AbstractCommand[] jobCommands()
	{
		return new AbstractCommand[]{};
	}
	
	// Optional Override-Possibility
	public void equipPlayerImpl( Player p )
	{
		return;
	}
	
	// Optional Override-Possibility
	public CuboidType whereToStart()
	{
		return null;
	}
	
	// Optional Override-Possibility
	public void onJobQuit( Player p ) 
	{
		return;
	}
	
	
	
	public void equipPlayer( Player p ) 
	{
		if( p == null || !jobs.isWorking(p) || !jobs.getJob(p).equals(jobs.getJob( _name ))) return;
		
		inventories.put( p, p.getInventory() );
		p.getInventory().clear();
		
		equipPlayerImpl( p );
	}
	
	public void unequipPlayer( Player p ) 
	{
		
		if( p == null ) return;
		p.getInventory().setContents( inventories.get(p).getContents() );
	}
	
	@Override
	public int hashCode() 
	{
		return _name.hashCode();
	}

	@Override
	public boolean equals( Object another ) 
	{
		if( this == another ) return true;
		if( another == null ) return false;
		if( getClass() != another.getClass() ) return false;
		
		if( getName().equalsIgnoreCase( ((AbstractJob) another).getName() ) ) return true;
		
		return false;
	}
	
	public boolean equals( String another ) 
	{
		if( another != null && another.trim().length() > 0 && getName().equalsIgnoreCase( another ) )
			return true;
		
		return false;
	}
	
	public static PlayerInventory getOldPlayerInventory( Player p ) 
	{
		return inventories.get( p );
	}
	
	public static void clearOldPlayerInventory( Player p ) 
	{
		inventories.get(p).clear();
	}
	
	public void join( Player p )
	{
		this.equipPlayer( p );
		jobs.setWorking( p, true );
	}
	
	public void leave( Player p )
	{
		this.unequipPlayer( p );
		this.onJobQuit( p );
		jobs.setWorking( p, false );
	}
}
