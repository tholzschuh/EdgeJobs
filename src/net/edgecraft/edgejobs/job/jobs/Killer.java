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
import net.edgecraft.edgecore.lang.LanguageHandler;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJobCommand;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.api.JobType;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Killer extends AbstractSidejob 
{
	private static final Killer instance = new Killer();
	
	private static final JobManager jobs = EdgeJobs.getJobs();
	
	private static final HashMap<KillContractPayload, ArrayList<User>> contracts = new HashMap<KillContractPayload, ArrayList<User>>();
	
	private Killer() 
	{
		super( "Killer", JobType.KILLER );
	}
	
	public static final Killer getInstance() 
	{
		return instance;
	}
	
	
	public void addContract( User killer, KillContractPayload details ) 
	{
		if( killer == null || details == null || !jobs.getJob( killer ).equals(Killer.getInstance()))
			return;
		
		ArrayList<User> killers = contracts.get( details );
		
		if( killers == null ) 
		{
			killers = new ArrayList<User>();	
		}
		
		for( KillContractPayload tmp : contracts.keySet() ) 
		{
			if( tmp.equals( details ) ) 
				details.addEmployers( tmp.getEmployers() );
		}
		
		killers.add( killer );
		
		contracts.put( details, killers );
	}
	
	public void addContract( User killer, User employer, User target, int bounty ) 
	{
		final KillContractPayload details = new KillContractPayload( null, target );
		details.addEmployer( employer, bounty );
		
		addContract( killer, details );
	}
	
	public void setContract( ArrayList<User> killer, KillContractPayload details ) 
	{
		if( killer == null || details == null ) return;
		
		for( User cur : killer ) 
			if( !jobs.getJob( cur ).equals( Killer.getInstance() ) )
				return;

		contracts.put( details, killer );
	}
	
	public HashMap<KillContractPayload, ArrayList<User>> getContracts() 
	{
		return Killer.contracts;
	}
	

	@Override
	public boolean hasDoneWork( User u ) 
	{
		// Checked through ManagePlayerDeathEvent
		return false;
	}

	@Override
	public AbstractCommand[] jobCommands() 
	{
		return new AbstractCommand[]{ KillerCommand.getInstance() };
	}

	public static class KillContractPayload 
	{
		private final int _id;
		private HashMap<User, Integer> _employers;
		private User _target;
		
		private static int greatestID;
		
		public KillContractPayload( HashMap<User, Integer> employers, User target ) 
		{
			_id = greatestID;
			++Killer.KillContractPayload.greatestID;
			
			setEmployers( employers );
			setTarget( target );
		}
		
		public KillContractPayload( User target ) 
		{
			this( null, target );
		}
		
		private void setEmployers( HashMap<User, Integer> employers ) 
		{
			if( employers == null ) 
					employers = new HashMap<User, Integer>();
			
			for( User empl : employers.keySet() ) 
				if( !Level.canUse( empl, Level.USER ) ) return;

			_employers = employers;
		}
		
		private void setTarget( User target ) 
		{
			if( target == null )
				return;
						
				_target = target;		
		}
		
		
		public int getID() 
		{
			return _id;
		}
		
		public HashMap<User, Integer> getEmployers() 
		{
			return _employers;
		}
		
		public int getBounty( User empl ) 
		{
			if( empl == null ) return 0;
			
			return _employers.get( empl );
		}
		
		
		public User getTarget() 
		{
			return _target;
		}
		
		public int getBounty() 
		{
			int bounty = 0;
			
			for( Entry<User, Integer> tmp : _employers.entrySet() ) 
				bounty += tmp.getValue();
			
			return bounty;
		}
		
		public void addEmployer( User employer, int bounty ) 
		{
			if( employer == null || bounty < 0 || !Level.canUse(employer, Level.USER) )
				return;
			
			_employers.put( employer, bounty );
		}
		
		public void addEmployers( HashMap<User, Integer> empls ) 
		{
			if( empls == null ) return;
			
			for( User tmp : empls.keySet() ) 
				if( !Level.canUse( tmp, Level.USER) ) return;
			
			_employers.putAll( empls );
		}
		
		public String getEmployersAsString() 
		{
			final StringBuilder empls = new StringBuilder();
			
			for( User empl : getEmployers().keySet() ) {
				if( empls.length() == 0 ) empls.append( "[ " + empl.getName() );
				else empls.append( ", " + empl.getName() );
			}
			
			empls.append( " ]" );
			
			return empls.toString();
		}
		
		@Override
		public int hashCode() 
		{
			return getTarget().hashCode();
		}
		
		@Override
		public boolean equals( Object another ) 
		{
			if( another == this ) return true;
			if( another == null ) return false;
			if( getClass() != another.getClass() ) return false;
			
			final KillContractPayload load = (KillContractPayload) another;
			
			if( getTarget().equals( load.getTarget() ) )
				return true;
			
			return false;
		}
		
	}
	
	public static class KillerCommand extends AbstractJobCommand 
	{

		private static final UserManager users = EdgeCoreAPI.userAPI();
		
		
		private static final KillerCommand instance = new KillerCommand();
		
		public KillerCommand() 
		{
			super( null );
		}
		
		public static final KillerCommand getInstance() 
		{
			return instance;
		}

		@Override
		public String[] getNames() 
		{
			return new String[]{ "killer" };
		}

		@Override
		public boolean runImpl( Player player, User user, String[] args ) 
		{
			final Killer killer = Killer.getInstance();
			final HashMap<KillContractPayload, ArrayList<User>> contracts = killer.getContracts();
			
			if( args.length == 4 && args[1].equalsIgnoreCase( "create-contract" ) ) 
			{
				User target = null;
				int bounty;
				
				try 
				{
					target = users.getUser( args[2] );
					bounty = Integer.valueOf( args[3] );
					
				} 
				catch( NumberFormatException  e ) 
				{
					sendUsage( player );
					return true;
				}
				
				if( target == null ) 
				{
					player.sendMessage( EdgeCore.errorColor + "Target not registered!" );
					return false;
				}
				
				
				final KillContractPayload payload = new KillContractPayload( target );
				payload.addEmployer( user , bounty );
				
				killer.addContract( null,  payload );
				player.sendMessage( lang.getColoredMessage( user.getLanguage(), "job_killer_contract_created") );
				return true;
			}
			
			if( !jobs.canUse( user , JobType.KILLER ) ) 
			{
				lang.getColoredMessage( user.getLanguage(), "job_wrongjob" );
				return false;
			}
			
			if( args.length == 2 && args[1].equalsIgnoreCase( "list-contracts" ) ) 
			{
				for( KillContractPayload details : contracts.keySet() ) 
					player.sendMessage( "Employers: " + details.getEmployersAsString() + ", Target: " + details.getTarget() + ", bounty: " + details.getBounty() );
				
				return true;
			}
			
			if( args.length == 3 && args[1].equalsIgnoreCase( "accept-contract" ) ) 
			{
				if( !jobs.getJob( user ).equals( killer ) ) 
				{
					player.sendMessage( lang.getColoredMessage( user.getLanguage(), "job_wrongjob" ) );
					return false;
				}
				
				int id = 0;
				
				try 
				{
					id = Integer.valueOf( args[2] );
				} 
				catch( NumberFormatException e ) 
				{
					sendUsage( player );
					return true;
				}
				
				for( KillContractPayload payload : contracts.keySet() ) 
				{
					if( id == payload.getID() ) 
					{
						killer.addContract( user, payload );
						player.sendMessage( lang.getColoredMessage( user.getLanguage(), "job_killer_acceptec").replace( "[1]" , payload.getTarget().getName()) );
						return true;
					}
				}
				
			}
			return true;
		}

		@Override
		public boolean validArgsRange( String[] args ) 
		{
			return ( args.length >= 2 && args.length <= 4 );
		}

		@Override
		public void sendUsageImpl( CommandSender sender ) 
		{
			sender.sendMessage( EdgeCore.usageColor + "/killer create-contract <target> <bounty>" );
			
			User u = users.getUser( ((Player)sender).getName() );
			
			if( u == null || !jobs.getJob( u ).equals( Killer.getInstance() ) )
				return;
			
			sender.sendMessage( EdgeCore.usageColor + "/killer accept-contract <id>" );
			sender.sendMessage( EdgeCore.usageColor + "/killer list-contracts" );
			return;
			
		}

		@Override
		public Level getLevel() 
		{
			return Level.USER;
		}
	}
	
	public static class ManagePlayerDeathEvent implements Listener 
	{
		private final static HashMap<KillContractPayload, ArrayList<User>> contracts = Killer.getInstance().getContracts();
		private final static UserManager users = EdgeCoreAPI.userAPI();
		private final static Economy economy = EdgeConomyAPI.economyAPI();
		private final static TransactionManager transactions = EdgeConomyAPI.transactionAPI();
		private final static LanguageHandler lang = EdgeCoreAPI.languageAPI();
		
		//TODO: Add hasDoneWork( true ) function? 
		@EventHandler
		public void onPlayerDeath( PlayerDeathEvent pde ) 
		{
			for( KillContractPayload tmpPayload : contracts.keySet() ) 
			{
				
				if( tmpPayload.getTarget().equals( users.getUser( pde.getEntity().getName() ) ) ) 
				{
					for( User u : contracts.get( tmpPayload ) ) 
					{
						if( pde.getEntity().getKiller().equals( u.getPlayer() ) && jobs.getJob( u ).equals( Killer.getInstance() ) && jobs.isWorking( u.getPlayer() ) ) 
						{					
							
							final BankAccount killerAcc = economy.getAccount( u.getID() );
							
							for( User tmp : tmpPayload.getEmployers().keySet() ) 
							{
								final BankAccount tmpAcc = economy.getAccount( tmp.getID() );
								transactions.addTransaction( tmpAcc, killerAcc, tmpPayload.getBounty( tmp ), pde.getEntity().getKiller().getName() + " murdered " + pde.getEntity().getName());
							}
							u.getPlayer().sendMessage( lang.getColoredMessage( u.getLanguage(), "job_transaction") );
						}
					}
				}
				
			}
			
		}
		
	}

}
