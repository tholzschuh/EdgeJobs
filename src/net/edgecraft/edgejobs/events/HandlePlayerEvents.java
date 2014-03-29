package net.edgecraft.edgejobs.events;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.lang.LanguageHandler;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgecuboid.EdgeCuboidAPI;
import net.edgecraft.edgecuboid.cuboid.CuboidHandler;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HandlePlayerEvents implements Listener
{

	private static final UserManager users = EdgeCoreAPI.userAPI();
	private static final LanguageHandler lang = EdgeCoreAPI.languageAPI();
	private static final CuboidHandler cuboids = EdgeCuboidAPI.cuboidAPI();

	
	@EventHandler
	public void handlePlayerJoin( PlayerJoinEvent e )
	{
		final Player p = e.getPlayer();
		
		JobManager.setWorking( p, false );
		
		User u = users.getUser( p.getName() );
		
		if( ConfigHandler.containsUser( u ) ) return;
		ConfigHandler.createUser( u );
	}
	
	@EventHandler 
	public void handlePlayerQuit( PlayerQuitEvent e )
	{
		final Player p = e.getPlayer();
		
		if( !JobManager.isWorking( p ) ) return;
		
		JobManager.setWorking( p, false );
		JobManager.getJob( p ).unequipPlayer( p );
	}
	
	@EventHandler
	public void handlePlayerRespawn( PlayerRespawnEvent e )
	{
		final Player p = e.getPlayer();
		
		if( !JobManager.isWorking( p ) ) return;
		
		final AbstractJob job = JobManager.getJob( p );
		if( job == null ) return;
		
		p.addPotionEffect( new PotionEffect( PotionEffectType.WEAKNESS, 100, 2 ) );
		p.setHealth( p.getMaxHealth() / 10 );
		p.teleport( cuboids.getNearestCuboid( job.whereToStart(), p.getLocation() ).getSpawn() );
		job.unequipPlayer( p );
	}
	
	@EventHandler
	public void handlePlayerDamage( EntityDamageEvent e )
	{
		if( !( e.getEntity() instanceof Player ) ) return;
		
		final Player p = (Player) e.getEntity();
		
		if( JobManager.isWorking( p ) && e.getDamage() >= p.getHealth() )
		{
			e.setCancelled( true );
			
			p.teleport( cuboids.getNearestCuboid( CuboidType.Hospital, p.getLocation()).getSpawn() );
			p.getInventory().setContents( AbstractJob.getOldPlayerInventory(p).getContents() );
			JobManager.setWorking( p, false );
			AbstractJob.clearOldPlayerInventory( p );
			p.sendMessage( lang.getColoredMessage( users.getUser(p.getName()).getLanguage(), "job_died") );
			return;
		}
	}
	
}
