package net.edgecraft.edgejobs.util;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgecuboid.EdgeCuboidAPI;
import net.edgecraft.edgecuboid.cuboid.CuboidHandler;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;

import org.bukkit.Bukkit;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.InventoryHolder;

public class UtilListener implements Listener {

	private static final UserManager users = EdgeCoreAPI.userAPI();
	private static final CuboidHandler cuboids = EdgeCuboidAPI.cuboidAPI();
	
	@EventHandler
	public void onPlayerJoin( PlayerJoinEvent e ){
		
		JobManager.setWorking( e.getPlayer(), false );
		
		User u = users.getUser( e.getPlayer().getName() );
		
		ConfigHandler.createUser( u );
		
	}
	
	@EventHandler
	public void onPlayerLeave( PlayerQuitEvent e ) {
		
		final Player quit = e.getPlayer();
		
		if( JobManager.isWorking( quit ) ) {
			
			JobManager.setWorking( e.getPlayer(), false);
			JobManager.getJob( quit ).unequipPlayer( quit );
		}	
	}
	
	@EventHandler
	public void onPlayerRespawnEvent( PlayerRespawnEvent e ) {
		
		Player player = e.getPlayer();
		
		if( JobManager.isWorking( player ) ) {
			
			AbstractJob job = JobManager.getJob( player );
			
			player.teleport( cuboids.getNearestCuboid( JobManager.getJob( player ).whereToStart(), player.getLocation() ).getSpawn() );
			job.equipPlayer( player );
		}
		
	}
	
	@EventHandler 
	public void onPlayerDropItemEvent( PlayerDropItemEvent e ) {
		
		Player p = e.getPlayer();
		
		if( JobManager.isWorking(p) ) {
			e.setCancelled( true );
			return;
		}
		
	}
	
	@EventHandler
	public void onInventoryOpenEvent( InventoryOpenEvent e ) {
		
		Player p = Bukkit.getPlayerExact( e.getPlayer().getName() );
		
		if( p == null ) {
			// wtf?
			e.setCancelled( true );
			return;
		}
		
		if( !JobManager.isWorking( p ) ) return;
		
		InventoryHolder holder = e.getInventory().getHolder();
		
		if( holder instanceof Chest || holder instanceof DoubleChest ) {
			
			e.setCancelled( true );
			return;
		}
	}
}
