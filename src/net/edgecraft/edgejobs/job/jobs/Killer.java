package net.edgecraft.edgejobs.job.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgeconomy.EdgeConomyAPI;
import net.edgecraft.edgeconomy.economy.BankAccount;
import net.edgecraft.edgeconomy.economy.Economy;
import net.edgecraft.edgeconomy.transactions.TransactionManager;
import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJobCommand;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Killer extends AbstractSidejob {

	private static final Killer instance = new Killer();
	
	private static final HashMap<KillContractPayload, ArrayList<User>> contracts = new HashMap<KillContractPayload, ArrayList<User>>();
	
	private Killer() {
		super( "Killer", ConfigHandler.getJobPay("Killer"));
	}
	
	public static final Killer getInstance() {
		return instance;
	}
	
	
	public void addContract( User killer, KillContractPayload details ) {
		
		if( killer == null || details == null || !JobManager.getJob( killer ).equals(Killer.getInstance()))
			return;
		
		ArrayList<User> killers = contracts.get( details );
		
		if( killers == null ) {
			
			killers = new ArrayList<User>();	
		}
		
		for( KillContractPayload tmp : contracts.keySet() ) {
			if( tmp.equals( details ) ) {
				details.addEmployers( tmp.getEmployers() );
			}
		}
		
		killers.add( killer );
		
		contracts.put( details, killers );
	}
	
	public void addContract( User killer, User employer, User target, int bounty ) {
		
		KillContractPayload details = new KillContractPayload( null, target );
		details.addEmployer( employer, bounty );
		
		addContract( killer, details );
		
	}
	
	public void setContract( ArrayList<User> killer, KillContractPayload details ) {
		
		if( killer == null || details == null ) return;
		
		for( User cur : killer ) {
			if( !JobManager.getJob( cur ).equals( Killer.getInstance() ) )
				return;
		}
		
		contracts.put( details, killer );
	}
	
	public HashMap<KillContractPayload, ArrayList<User>> getContracts() {
		return Killer.contracts;
	}
	

	@Override
	public boolean hasDoneWork( User u ) {
		
		return false;
	}

	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{ KillerCommand.getInstance() };
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
		return null;
	}

	/*
	 * 
	 */
	public static class KillContractPayload {
		
		private final int _id;
		private HashMap<User, Integer> _employers;
		private User _target;
		
		private static int greatestID;
		
		public KillContractPayload( HashMap<User, Integer> employers, User target ) {
			
			_id = greatestID;
			++Killer.KillContractPayload.greatestID;
			
			setEmployers( employers );
			setTarget( target );
		}
		
		public KillContractPayload( User target ) {
			this( null, target );
		}
		
		private void setEmployers( HashMap<User, Integer> employers ) {
			
			if( employers == null ) 
					employers = new HashMap<User, Integer>();
			
			for( User empl : employers.keySet() ) {
				if( !Level.canUse( empl, Level.USER ) ) return;
			}
			
			_employers = employers;
		}
		
		private void setTarget( User target ) {
			if( target == null )
				return;
						
				_target = target;		
		}
		
		
		public int getID() {
			return _id;
		}
		
		public HashMap<User, Integer> getEmployers() {
			return _employers;
		}
		
		public int getBounty( User empl ) {
			
			if( empl == null ) return 0;
			
			return _employers.get( empl );
		}
		
		
		public User getTarget() {
			return _target;
		}
		
		public int getBounty() {
			int bounty = 0;
			
			for( Entry<User, Integer> tmp : _employers.entrySet() ) {
				bounty += tmp.getValue();
			}
			
			return bounty;
		}
		
		public void addEmployer( User employer, int bounty ) {
			if( employer == null || bounty < 0 || !Level.canUse(employer, Level.USER) )
				return;
			
			_employers.put( employer, bounty );
		}
		
		public void addEmployers( HashMap<User, Integer> empls ) {
			if( empls == null ) return;
			
			for( User tmp : empls.keySet() ) {
				if( !Level.canUse( tmp, Level.USER) ) return;
			}
			
			_employers.putAll( empls );
		}
		
		public String getEmployersAsString() {
			
			StringBuilder empls = new StringBuilder();
			
			for( User empl : getEmployers().keySet() ) {
				if( empls.length() == 0 ) empls.append( "[ " + empl.getName() );
				else empls.append( ", " + empl.getName() );
			}
			
			empls.append( " ]" );
			
			return empls.toString();
		}
		
		@Override
		public int hashCode() {
			return getTarget().hashCode();
		}
		
		@Override
		public boolean equals( Object another ) {
			
			if( another == this ) return true;
			if( another == null ) return false;
			if( getClass() != another.getClass() ) return false;
			
			KillContractPayload load = (KillContractPayload) another;
			
			if( getTarget().equals( load.getTarget() ))
				return true;
			
			return false;
		}
		
	}
	
	/* 
	 *
	 */
	public static class KillerCommand extends AbstractJobCommand {

		private static KillerCommand instance = new KillerCommand();
		
		private final UserManager users = EdgeCoreAPI.userAPI();
		
		private KillerCommand() {
			super( Killer.getInstance() );
		}
		
		public static final KillerCommand getInstance() {
			return instance;
		}

		@Override
		public String[] getNames() {
			return new String[]{ "killer" };
		}

		@Override
		public boolean runImpl( Player player, User user, String[] args ) {
			
			final Killer killer = Killer.getInstance();
			final HashMap<KillContractPayload, ArrayList<User>> contracts = killer.getContracts();
			
			if( args.length == 4 && args[1].equalsIgnoreCase( "create-contract" ) ) {
				
				User target = null;
				int bounty;
				
				try {
					target = users.getUser( args[2] );
					bounty = Integer.valueOf( args[3] );
					
				} catch( NumberFormatException  e ) {
					sendUsage( player );
					return true;
				}
				
				if( target == null ) {
					player.sendMessage( EdgeCore.errorColor + "Target not registered!" );
					return false;
				}
				
				
				KillContractPayload payload = new KillContractPayload( target );
				payload.addEmployer( user , bounty );
				
				killer.addContract( null,  payload );
				return true;
			}
			
			if( args.length == 2 && args[1].equalsIgnoreCase( "list-contracts" ) ) {
				
				for( KillContractPayload details : contracts.keySet() ) {
					
					player.sendMessage( "Employers: " + details.getEmployersAsString() + ", Target: " + details.getTarget() + ", bounty: " + details.getBounty() );
				}
				
				return true;
			}
			
			if( args.length == 3 && args[1].equalsIgnoreCase( "accept-contract" ) ) {
				
				if( !JobManager.getJob( user ).equals( killer ) ) {
					sendUsage( player );
					return false;
				}
				
				int id = 0;
				
				try {
					id = Integer.valueOf( args[2] );
				} catch( NumberFormatException e ) {
					sendUsage( player );
					return true;
				}
				
				for( KillContractPayload payload : contracts.keySet() ) {
					if( id == payload.getID() ) {
						killer.addContract( user, payload );
						return true;
					}
				}
				
			}
			
			return true;
		}

		@Override
		public void sendUsage( CommandSender sender ) {
			User u = users.getUser( ((Player)sender).getName() );
			
			if( !Level.canUse( u, Level.USER) )
				return;
			
			sender.sendMessage( EdgeCore.usageColor + "/killer create-contract <target> <bounty>" );
			
			if( !JobManager.getJob( u ).getName().equals( super.getJobName() ))
				return;
			
			sender.sendMessage( EdgeCore.usageColor + "/killer accept-contract <id>" );
			sender.sendMessage( EdgeCore.usageColor + "/killer list-contracts" );
			return;
			
		}

		@Override
		public boolean validArgsRange( String[] args ) {
			return ( args.length >= 2 && args.length <= 4 );
		}
	}
	
	/*
	 *
	 */
	public static class PlayDeathEvent implements Listener {
		
		private final HashMap<KillContractPayload, ArrayList<User>> contracts = Killer.getInstance().getContracts();
		private final UserManager users = EdgeCoreAPI.userAPI();
		private final Economy economy = EdgeConomyAPI.economyAPI();
		private final TransactionManager transactions = EdgeConomyAPI.transactionAPI();
		
		@EventHandler
		public void onPlayerDeath( PlayerDeathEvent pde ) {
			
			for( KillContractPayload tmpPayload : contracts.keySet() ) {
				
				if( tmpPayload.getTarget().equals( users.getUser( pde.getEntity().getName() ) ) ) {
					for( User u : contracts.get( tmpPayload ) ) {
						if( pde.getEntity().getKiller().equals( u.getPlayer() ) && JobManager.getJob( u ).equals( Killer.getInstance() ) && JobManager.isWorking( u.getPlayer() ) ) {
							
							BankAccount killerAcc = economy.getAccount( u.getID() );
							
							for( User tmp : tmpPayload.getEmployers().keySet() ) {
								BankAccount tmpAcc = economy.getAccount( tmp.getID() );
								transactions.addTransaction( tmpAcc, killerAcc, tmpPayload.getBounty( tmp ), pde.getEntity().getKiller().getName() + " murdered " + pde.getEntity().getName());
							}
							
						}
					}
				}
				
			}
			
		}
		
	}

}
