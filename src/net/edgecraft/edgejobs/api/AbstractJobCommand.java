package net.edgecraft.edgejobs.api;

import net.edgecraft.edgeconomy.EdgeConomyAPI;
import net.edgecraft.edgeconomy.economy.Economy;
import net.edgecraft.edgeconomy.transactions.TransactionManager;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.EdgeCuboidAPI;
import net.edgecraft.edgecuboid.cuboid.CuboidHandler;
import net.edgecraft.edgecuboid.shop.ShopHandler;
import net.edgecraft.edgecuboid.world.WorldManager;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.job.Job;
import net.edgecraft.edgejobs.job.JobCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractJobCommand extends AbstractCommand 
{
	private final AbstractJob _job;
	
	protected final CuboidHandler cuboids = EdgeCuboidAPI.cuboidAPI();
	protected final ShopHandler shops = EdgeCuboidAPI.shopAPI();
	protected final WorldManager worlds = EdgeCuboidAPI.worldAPI();
	protected final Economy economy = EdgeConomyAPI.economyAPI();
	protected final TransactionManager transactions = EdgeConomyAPI.transactionAPI();
	protected final JobManager jobs = EdgeJobs.getJobs();
	
	public AbstractJobCommand( AbstractJob  job ) 
	{
		if( job == null ) _job = Job.NO_JOB.getJob();
		else _job = job;
		
		JobCommands.getInstance().registerCommand( this );
	}
	
	public AbstractJob getJob() 
	{
		return _job;
	}
	
	public String getJobName()
	{
		return _job.getName();
	}
	
	@Override
	public Level getLevel() 
	{
		return Level.USER; // Every user can do Job-Commands
	}
	
	public final boolean run( Player player, User user, String[] args ) throws Exception 
	{
		
		final AbstractJob job = jobs.getJob( user );
		
		if( getJobName().equalsIgnoreCase( job.getName() ) )
			return super.run( player, args );
		
		player.sendMessage(lang.getColoredMessage("de", "job_nojob"));
		
		return true;
		
	}
	
	@Override
	public final boolean sysAccess(CommandSender sender, String[] args) 
	{
		sender.sendMessage(lang.getColoredMessage("de", "noconsole"));
		return true; // Console cant do Job-Commands
	}
}
