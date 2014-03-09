package net.edgecraft.edgejobs.api;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.EdgeJobs;


public abstract class AbstractJob {

	private static final HashMap<Player, PlayerInventory> inventories = new HashMap<>();
	
	private String name;
	private double pay;
	
	public AbstractJob( String name, double pay ) {
		setName( name );
		setPay( pay );
	}
	
	public String getName() {
		return this.name;
	}
	
	public final double getPay(){
		return this.pay;
	}
	
	public void printHelpSentence(User u, String sentence){
		u.getPlayer().sendMessage(EdgeJobs.helpColor + sentence);
	}
	
	private final void setName( String name ) {
		if( name == null || name.trim().length() == 0 ) return;
		
		this.name = name;
	}
	
	private final void setPay( double pay ) {
		this.pay = Math.abs( pay );
	}
	
	public abstract AbstractCommand[] jobCommands();
	public abstract void printHelp( User u );
	public abstract void equipPlayerImpl( Player p );
	
	public abstract CuboidType whereToStart();
	
	public void equipPlayer( Player p ) {
		
		if( p == null || !JobManager.isWorking(p) || !JobManager.getJob(p).equals(JobManager.getJob(name))) return;
		
		inventories.put( p, p.getInventory() );
		p.getInventory().clear();
		
		equipPlayerImpl( p );
	}
	
	public void unequipPlayer( Player p ) {
		
		if( p == null ) return;
		p.getInventory().clear();
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals( Object another ) {
		
		if( this == another ) return true;
		if( another == null ) return false;
		if( getClass() != another.getClass() ) return false;
		
		if( getName().equalsIgnoreCase( ((AbstractJob) another).getName() ) ) return true;
		
		return false;
	}
	
	public boolean equals( String another ) {
		if( another != null && another.trim().length() > 0 && getName().equalsIgnoreCase( another ) )
			return true;
		
		return false;
	}
	
	public static PlayerInventory getOldPlayerInventory( Player p ) {
		return inventories.get( p );
	}
	
	public static void clearOldPlayerInventory( Player p ) {
		inventories.get(p).clear();
	}
}
