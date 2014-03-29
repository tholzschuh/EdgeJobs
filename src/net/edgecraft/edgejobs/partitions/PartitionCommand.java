package net.edgecraft.edgejobs.partitions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.EdgeCuboidAPI;
import net.edgecraft.edgecuboid.cuboid.Cuboid;
import net.edgecraft.edgecuboid.cuboid.CuboidHandler;
import net.edgecraft.edgecuboid.shop.Shop;
import net.edgecraft.edgecuboid.shop.ShopHandler;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;

public class PartitionCommand extends AbstractCommand
{
	private final static PartitionCommand instance = new PartitionCommand();
	
	private final static CuboidHandler cuboids = EdgeCuboidAPI.cuboidAPI();
	private final static ShopHandler shops = EdgeCuboidAPI.shopAPI();
	
	private PartitionCommand() { /* ... */ }
	
	public final static PartitionCommand getInstance()
	{
		return instance;
	}
	
	@Override
	public Level getLevel() 
	{
		return Level.MODERATOR;
	}

	@Override
	public String[] getNames() 
	{
		return new String[]{ "partition" };
	}

	@Override
	public boolean runImpl( Player p, User u, String[] args ) 
	{
		final String userLang = u.getLang();
		
		if( args[1].equalsIgnoreCase( "register" ) && args.length >= 5 )
		{
			final AbstractJob job = JobManager.getJob( args[2] );
			
			if( job == null )
			{
				p.sendMessage( lang.getColoredMessage( userLang, "job_notfound" ) );
				return false;
			}
			
			final User owner = users.getUser( args[3] );
			
			if( owner == null )
			{
				p.sendMessage( lang.getColoredMessage( userLang, "notfound" ) );
				return false;
			}
			
			final Cuboid cuboid = cuboids.getCuboid( args[4] );
			
			if( cuboid == null )
			{
				p.sendMessage( lang.getColoredMessage( userLang, "unknowncuboid" ).replace( "[0]", args[4]) );
				return false;
			}
			
			final Shop store = shops.getShop( cuboid );
			
			return PartitionManager.registerPartition( job, new Partition( job, owner, cuboid, store ) );
		}
		
		if( args[1].equalsIgnoreCase( "delete" ) && args.length == 3 )
		{
			
			final User owner = users.getUser( args[2] );
			
			if( owner == null )
			{
				p.sendMessage( lang.getColoredMessage( userLang, "notfound" ) );
				return false;
			}
			
			PartitionManager.deletePartition( owner );
			return true;
		}
		
		final User owner = users.getUser( args[2] );
		final User modify = users.getUser( args[3] );
		
		if( owner == null || modify == null )
		{
			p.sendMessage( lang.getColoredMessage( userLang, "notfound" ) );
			return false;
		}
		
		if( args[2].equalsIgnoreCase( "register-user" ) && args.length == 4 )
		{	
			PartitionManager.getPartitionByOwner( owner ).addParticipant( modify );
			return true;
		}
		
		if( args[2].equalsIgnoreCase( "delete-user" ) && args.length == 4 )
		{
			PartitionManager.getPartitionByOwner( owner ).removeParticipant( modify );
			return true;
		}

		sendUsage( p );
		return false;
	}

	@Override
	public void sendUsageImpl( CommandSender sender ) 
	{
		sender.sendMessage( EdgeCore.usageColor + "/partition register <job> <owner> <cuboid>");
		sender.sendMessage( EdgeCore.usageColor + "/partition delete <owner>" );
		sender.sendMessage( EdgeCore.usageColor + "/partition register-user <owner> <user>");
		sender.sendMessage( EdgeCore.usageColor + "/partition delete-user <owner> <user>" );
		return;
	}

	@Override
	public boolean validArgsRange( String[] args ) 
	{
		return( args.length >= 3 && args.length <= 6 );
	}

}
