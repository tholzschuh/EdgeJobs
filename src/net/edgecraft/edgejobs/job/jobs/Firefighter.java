package net.edgecraft.edgejobs.job.jobs;


import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.LeatherJob;

import org.bukkit.Color;
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

//		private boolean enabled = true;
//		private static final CuboidHandler cuboids = EdgeCuboidAPI.cuboidAPI();
		
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

//		private boolean turn()
//		{
//			if( new Random().nextInt( 1 ) == 1 ) return true;
//			return false;
//		}
		
		@Override
		public boolean runImpl( Player p, User u, String[] args ) 
		{
			
			if( args[1].equalsIgnoreCase( "enable" ) )
			{
				return true;
			}
			
			if( args[1].equalsIgnoreCase( "disable" ) )
			{
				return true;
			}
			
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
