package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.EdgeCuboidAPI;
import net.edgecraft.edgecuboid.cuboid.Cuboid;
import net.edgecraft.edgecuboid.cuboid.CuboidHandler;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.job.LeatherJob;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Firefighter extends LeatherJob 
{
	
	private static final Firefighter instance = new Firefighter();
	
	private Firefighter() 
	{
		super( "Firefighter", Color.RED );
	}

	public static final Firefighter getInstance() 
	{
		return instance;
	}
	
	
	public static class FireCommand extends AbstractCommand 
	{ 
		private static final FireCommand instance = new FireCommand();

		private boolean enabled = true;
		private static final CuboidHandler cuboids = EdgeCuboidAPI.cuboidAPI();
		
		private FireCommand() { /* ... */ }
		
		public static final FireCommand getInstance()
		{
			return instance;
		}
		
		@Override
		public Level getLevel() 
		{
			return Level.DEVELOPER;
		}

		@Override
		public String[] getNames() 
		{
			return new String[]{ "fire" };
		}

		private void setRandomFire()
		{
			final Cuboid random = cuboids.getCuboid( CuboidType.Survival, false );
			
			random.getMinLocation().add(0, 1, 0).getBlock().setType(Material.FIRE);
			random.getMaxLocation().add(0, 1, 0).getBlock().setType(Material.FIRE);
			random.getCenter().add(0, 1, 0).getBlock().setType(Material.FIRE);
			random.getSpawn().add(0, 1, 0).getBlock().setType(Material.FIRE);
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage("§7[DEBUG] Random Fire has been set!");
			}
		}
		
		public void fire()
		{
			Bukkit.getScheduler().runTaskTimer( EdgeJobs.getInstance(), new Runnable() {

				@Override
				public void run() {
					if( enabled )
						setRandomFire();
				}
				
			}, 20L, 20L * 60 );
		}
		
		@Override
		public boolean runImpl( Player p, User u, String[] args ) 
		{
			
			if( args[1].equalsIgnoreCase( "enable" ) )
			{
				enabled = true;				
				return true;
			}
			
			if( args[1].equalsIgnoreCase( "disable" ) )
			{
				enabled = false;
				return true;
			}
			
			sendUsage( p );
			return true;
		}

		@Override
		public void sendUsageImpl( CommandSender sender ) 
		{
			sender.sendMessage("/fire enable");
			sender.sendMessage( "/fire disable" );
		}


		@Override
		public boolean validArgsRange( String[] args ) 
		{
			return ( args.length == 2 );
		}
		
	}
	
	@Override
	public CuboidType whereToStart() {
		return CuboidType.FireStation;
	}
}
