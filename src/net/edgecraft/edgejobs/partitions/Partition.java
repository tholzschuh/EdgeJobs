package net.edgecraft.edgejobs.partitions;

import java.util.ArrayList;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.Cuboid;
import net.edgecraft.edgecuboid.shop.Shop;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.JobManager;

public class Partition {

	private AbstractJob _job;
	private User _owner;
	private Cuboid _cuboid;
	private Shop _store;
	private ArrayList<User> _participants;
	
	public Partition( AbstractJob job, User owner, Cuboid cuboid, Shop store, ArrayList<User> participants )
	{
		setJob( job );
		setOwner( owner );
		setCuboid( cuboid );
		setStore( store );
		setParticipants( participants );
	}
	
	public Partition( AbstractJob job, User owner, Cuboid cuboid )
	{
		this( job, owner, cuboid, null, new ArrayList<User>() );
	}
	public Partition( AbstractJob job, User owner, Cuboid cuboid, Shop store )
	{
		this( job, owner, cuboid, store, new ArrayList<User>() );
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
		_owner = u;
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
	
	private void setParticipants( ArrayList<User> part )
	{
		if( part == null ) return;
		_participants = part;
	}
	
	public AbstractJob getJob()
	{
		return _job;
	}
	
	public User getOwner()
	{
		return _owner;
	}
	
	public Cuboid getCuboid()
	{
		return _cuboid;
	}
	
	public Shop getStore()
	{
		return _store;
	}
	
	public ArrayList<User> getParticipants()
	{
		return _participants;
	}
	
	public boolean addParticipant( User u )
	{
		if( !JobManager.getJob( u ).equals( getJob() ) )
			return false;
		
		return _participants.add( u );
	}
	
	public boolean removeParticipant( User u )
	{
		return _participants.remove( u );
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
