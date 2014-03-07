package net.edgecraft.edgejobs.job;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractJobCommand;
import net.edgecraft.edgejobs.util.ConfigHandler;

public class Policeman extends AbstractJob {

	private static final Policeman instance = new Policeman();
	
	private Policeman() {
		super( "Policeman", ConfigHandler.getJobPay( "Policemen" ) );
	}
	
	public static final Policeman getInstance() {
		return instance;
	}

	@Override
	public AbstractJobCommand[] jobCommands() {
		return new AbstractJobCommand[]{ ArrestCommand.getInstance(), ReleaseCommand.getInstance() };
	}

	@Override
	public void printHelp( User u ) {
		return;
	}

	@Override
	public void equipPlayer( Player p ) {
		return;
	}

	@Override
	public CuboidType whereToStart() {
		return CuboidType.POLICE;
	}
	
	public static class ArrestCommand extends AbstractJobCommand {

		private static final ArrestCommand instance = new ArrestCommand( Policeman.getInstance() );
		
		private ArrestCommand( Policeman job ) {
			super( job );
		}

		public static final ArrestCommand getInstance() {
			return instance;
		}

		@Override
		public boolean runImpl( Player player, User user, String[] args) {
			
			
			return true;
		}

		@Override
		public String[] getNames() {
			return new String[]{ "arrest" };
		}

		@Override
		public void sendUsage( CommandSender sender ) {
			
				sender.sendMessage( EdgeCore.usageColor + "/arrest <target>" );
				return;
		}

		@Override
		public boolean validArgsRange( String[] args ) {
			return ( args.length == 1 );
		}
	}
	
	public static class ReleaseCommand extends AbstractJobCommand {

		private static final ReleaseCommand instance = new ReleaseCommand( Policeman.getInstance() );
		
		private ReleaseCommand( Policeman job ) {
			super(job);
		}
		
		public static final ReleaseCommand getInstance() {
			return instance;
		}

		@Override
		public String[] getNames() {
			return new String[]{ "release" };
		}

		@Override
		public boolean runImpl( Player player, User user, String[] args ) {
			return true;
		}

		@Override
		public void sendUsage( CommandSender sender ) {

			sender.sendMessage( EdgeCore.usageColor + "/release <target>" );
		}

		@Override
		public boolean validArgsRange(String[] args) {
			return ( args.length == 1 );
		}
	}
	
	public static class WantedCommand extends AbstractJobCommand {

		private static final WantedCommand instance = new WantedCommand( Policeman.getInstance() );
		
		private final Map<User, SearchLevel> wanted;
		private final UserManager users = EdgeCoreAPI.userAPI();
		
		private WantedCommand( Policeman job ) {
			super(job);
			wanted = new HashMap<User, SearchLevel>();
		}

		public static final WantedCommand getInstance() {
			return instance;
		}
		
		@Override
		public String[] getNames() {
			return new String[]{ "wanted" };
		}

		@Override
		public boolean runImpl( Player player, User user, String[] args ) {
			
				if( args.length >= 2 && args.length <= 3 && args[1].equalsIgnoreCase("list") ) {
					
					SearchLevel to = SearchLevel.FIVE;
					String prefix = "Top Wanted: ";
					
					if( args.length == 3 ) {
						try {
							to = SearchLevel.getInstance( Integer.valueOf( args[2] ) );
							prefix = "Wanted[" + args[2] + "]";
						} catch( NumberFormatException e ) {
							sendUsage( player );
							return true;
						}
					}
						
					StringBuilder sb = new StringBuilder();
						
					for( User u : wanted.keySet() ) {
						if( wanted.get( u ).equals( to ) ) {
							if( sb.length() == 0 ) sb.append( "[ " + u.getName() );
							else sb.append( ", " + u.getName() );
						}
					}
					sb.append( " ]" );
						
					player.sendMessage( prefix + sb.toString() );	
				}
				
				if( args.length == 4 && args[1].equalsIgnoreCase( "set" )) {
					
					User targetUser = null;
					SearchLevel targetLevel = null;
					
					try {
						targetUser = users.getUser( args[2] );
						targetLevel = SearchLevel.getInstance( Integer.valueOf( args[3] ) );
					} catch( NumberFormatException e ) {
						sendUsage( player );
						return true;
					}
					
					wanted.put( targetUser, targetLevel );
					return true;
				}
			
				sendUsage( player );
				return true;
		}

		
		@Override
		public void sendUsage( CommandSender sender ) {
			
			sender.sendMessage( EdgeCore.usageColor + "/wanted set <target> <lvl>" );
			sender.sendMessage( EdgeCore.usageColor + "/wanted list [<num>]" );
			return;
		}

		@Override
		public boolean validArgsRange( String[] args ) {
			return( args.length >= 2 && args.length <= 4 );
		}
		
	}
	
	public enum SearchLevel {
		
		ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
		
		private final int lvl;
		
		private SearchLevel( int lvl ) {
			if( lvl >= 0 ) this.lvl = lvl;
			else this.lvl = 0;
		}
		
		public final int getLevel() {
			return lvl;
		}
		
		public static final SearchLevel[] getSearchLevels() {
			return new SearchLevel[]{ SearchLevel.ZERO, SearchLevel.ONE, SearchLevel.TWO, SearchLevel.THREE, SearchLevel.FOUR, SearchLevel.FIVE };
		}
		
		public static final SearchLevel getInstance( int lvl ) {
			
			SearchLevel[] levels = getSearchLevels();
			
			for( int i = 0; i < levels.length; i++ ) {
				if( lvl == levels[i].getLevel() ) {
					return levels[i];
				}
			}
			
			return SearchLevel.ZERO;
		}
		
	}

}
