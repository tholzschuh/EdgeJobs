package net.edgecraft.edgejobs.partitions;

import java.util.ArrayList;
import java.util.List;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.Cuboid;
import net.edgecraft.edgecuboid.shop.Shop;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;

public class Partition
{
	private static final JobManager jobs = EdgeJobs.getJobs();
	
	private AbstractJob _job;
	private int _ownerID;
	private Cuboid _cuboid;
	private Shop _store;
	private ArrayList<String> _participants;
	
	public Partition( AbstractJob job, User owner, Cuboid cuboid, Shop store )
	{
		setJob( job );
		setOwner( owner );
		setCuboid( cuboid );
		setStore( store );
		_participants = new ArrayList<String>();
		initialize();
	}
	
	public Partition( AbstractJob job, User owner, Cuboid cuboid )
	{
		this( job, owner, cuboid, null );
	}
	
	private void initialize()
	{
		final List<String> participants = _cuboid.getParticipants();
		participants.clear();
		
		for( String s : _participants )
		{
			participants.add( s );
		}
		
	}
	
	private void setJob( AbstractJob job )
	{
		if( job == null ) return;
		_job = job;
	}
	
	public void updateJob( AbstractJob job )
	{
		setJob( job );
	}
	
	private void setOwner( User u )
	{
		if( u == null ) return;
		_ownerID = u.getID();
	}
	
	public void updateOwner( User u )
	{
		setOwner( u );
	}
	
	private void setCuboid( Cuboid c )
	{
		if( c == null ) return;
		_cuboid = c;
	}
	
	public void updateCuboid( Cuboid c )
	{
		setCuboid( c );
	}
	
	private void setStore( Shop s )
	{
		if( s == null ) return;
		_store = s;
	}
	
	public void updateStore( Shop s )
	{
		setStore( s );
	}
	
	public boolean addParticipant( User u )
	{
		if( !jobs.getJob( u ).equals( getJob() ) )
			return false;
		
		boolean ret = _participants.add( u.getName() );
		
		if( ret )
		{
			_cuboid.getParticipants().add( u.getName() );
		}

		return ret;
	}
	
	public boolean removeParticipant( User u )
	{
		boolean ret = _participants.remove( u.getName() );
		
		if( ret )
		{
			_cuboid.getParticipants().remove( u.getName() );
		}
		
		return ret;
	}
	
	public AbstractJob getJob()
	{
		return _job;
	}
	
	public int getOwnerID()
	{
		return _ownerID;
	}
	
	public User getOwner()
	{
		return EdgeCoreAPI.userAPI().getUser( getOwnerID() );
	}
	
	public Cuboid getCuboid()
	{
		return _cuboid;
	}
	
	public Shop getStore()
	{
		return _store;
	}
	
	public ArrayList<String> getParticipants()
	{
		return _participants;
	}
	
	public boolean isParticipant( User u )
	{
		if( _participants.contains( u ) || _ownerID == u.getID() ) return true;

		return false;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( this == obj ) return true;
		if( obj == null ) return false;
		if( getClass() != obj.getClass() ) return false;
		
		final Partition another = (Partition) obj;
		
		if( !another.getJob().equals( this.getJob() ) ) return false;
		if( !another.getOwner().equals( this.getOwner() ) ) return false;
		if( !another.getCuboid().equals( this.getCuboid() ) ) return false;
		if( !another.getStore().equals( this.getStore() ) ) return false;
		if( !another.getParticipants().equals( this.getParticipants() ) ) return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		return( getJob().hashCode() * getOwner().hashCode() - getCuboid().hashCode() + getStore().hashCode() * getParticipants().hashCode() );
	}
	
}
