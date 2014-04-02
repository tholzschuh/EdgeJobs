package net.edgecraft.edgejobs.partitions;

import java.util.ArrayList;
import java.util.List;

import net.edgecraft.edgecore.user.User;

public final class PartitionManager
{	
	private final static ArrayList<Partition> _partitions = new ArrayList<>();
	
	private PartitionManager() { /* ... */ }
	
	public static boolean registerPartition( Partition partition )
	{
		for( Partition tmp : _partitions )
		{
			if( tmp.getOwnerID() == partition.getOwnerID() ) return false;
			for( String user : partition.getParticipants() )
			{
				if( tmp.getParticipants().contains( user ) ) return false;
			}
		}
		
		return _partitions.add( partition );
	}
	
	public static boolean deletePartition( Partition partition )
	{
		return _partitions.remove( partition );
	}
	
	public static boolean deletePartition( User owner )
	{
		for( Partition p : _partitions )
			if( p.getOwnerID() == owner.getID() ) 
				return _partitions.remove( p );
		
		return false;
	}
	
	public static Partition getPartition( Partition p )
	{
		return _partitions.get( _partitions.indexOf( p ) );
	}
	
	public static Partition getPartition( String user )
	{
		for( Partition p : _partitions )
		{
			if( p.getOwner().getName() == user ) return p;
			if( p.getParticipants().contains( user ) ) return p;
		}
		
		return null;
	}
	
	public static Partition getPartition( User u )
	{
		return getPartition( u.getName() );
	}
	
	public static List<Partition> getPartitions()
	{
		return _partitions;
	}
	
}
