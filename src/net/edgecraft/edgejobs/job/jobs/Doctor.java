package net.edgecraft.edgejobs.job.jobs;

import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.Cuboid;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJobCommand;
import net.edgecraft.edgejobs.job.LeatherJob;

public class Doctor extends LeatherJob 
{
	private static final Doctor instance = new Doctor();
	
	private Doctor() 
	{
		super( "Doctor", Color.WHITE );
	}
	
	public static final Doctor getInstance()
	{
		return instance;
	}
	
	@Override
	public AbstractCommand[] jobCommands()
	{
		return new AbstractCommand[]{ HealCommand.getInstance() };
	}
	
	@Override
	public CuboidType whereToStart() {
		return CuboidType.Hospital;
	}
	
	private static class HealCommand extends AbstractJobCommand
	{

		private static final HealCommand instance = new HealCommand();
		
		private HealCommand() 
		{
			super( Doctor.getInstance() );
		}
		
		public static final HealCommand getInstance()
		{
			return instance;
		}

		@Override
		public String[] getNames() 
		{
			return new String[]{ "heal" };
		}

		@Override
		public boolean runImpl( Player p, User u, String[] args ) 
		{
			final User target = users.getUser( args[1] );
			
			if( target == null ) 
			{
				p.sendMessage( lang.getColoredMessage( u.getLang(),  "notfound" ) );
				return true;
			}
			
			final Player targetPlayer = target.getPlayer();
			
			if( Cuboid.getCuboid( p.getLocation() ).equals( targetPlayer.getLocation() ) )
			{
				targetPlayer.addPotionEffect( new PotionEffect( PotionEffectType.REGENERATION, 20 * 5, 2 ) );
				targetPlayer.setHealth( targetPlayer.getMaxHealth() );
				return true;
			}
			
			sendUsage( p );
			return false;
		}

		@Override
		public void sendUsageImpl( CommandSender sender ) 
		{
			sender.sendMessage( EdgeCore.usageColor + "/heal <target>" );
		}

		@Override
		public boolean validArgsRange( String[] args ) 
		{
			return ( args.length == 2 );
		}
		
	}

}
