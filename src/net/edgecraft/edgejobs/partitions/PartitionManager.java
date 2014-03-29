package net.edgecraft.edgejobs.partitions;

import java.util.ArrayList;
import java.util.HashMap;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.api.AbstractJob;

public final class PartitionManager 
{
	private static final HashMap<AbstractJob, ArrayList<Partition>> _partitions = new HashMap<>();
	private PartitionManager() { /* ... */ }
	
	public static boolean registerPartition( AbstractJob job, Partition partition )
	{
		if( !_partitions.containsKey( job ) )
			_partitions.put( job, new ArrayList<Partition>() );
		
		for( ArrayList<Partition> partitions : _partitions.values() )
		{
			for( Partition p : partitions )
			{
				if( p.getOwner().equals( partition.getOwner() ) ) return false;
				for( User pcp : partition.getParticipants() )
				{
					if( p.getParticipants().contains( pcp ) ) return false;
				}
			}
		}
		
		return _partitions.get( job ).add( partition );
	}
	
	public static boolean deletePartition( AbstractJob job, Partition partition )
	{
		if(!_partitions.containsKey( job ) ) return false;
		
		return _partitions.get( job ).remove( partition );
	}
	
	public static boolean deletePartition( User owner )
	{
		for( ArrayList<Partition> partitions : _partitions.values() )
			for( Partition p : partitions )
				if( p.getOwner().equals( owner ) )
				{
					partitions.remove( p );
					return true;
				}

		return true;
	}
	
	public static ArrayList<Partition> getPartitions( AbstractJob job )
	{
		return _partitions.get( job );
	}
	
	public static Partition getPartition( AbstractJob job, int index )
	{
		return _partitions.get( job ).get( index );
	}
	
	public static Partition getPartitionByOwner( User owner )
	{
		for( ArrayList<Partition> partitions : _partitions.values() )
			for( Partition p : partitions )
				if( p.getOwner().equals( owner ) ) return p;
		
		return null;
	}
	
	public static Partition getPartitionByParticipant( User participant )
	{
		for( ArrayList<Partition> partitions : _partitions.values() )
			for( Partition p : partitions )
				if( p.getParticipants().contains( participant ) ) return p;
		
		return null;
	}
}
