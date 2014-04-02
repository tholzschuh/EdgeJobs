package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractJobCommand;
import net.edgecraft.edgejobs.api.JobType;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Criminal extends AbstractJob implements Listener 
{
	private static final Criminal instance = new Criminal();
	
	private Criminal() 
	{
		super( "Criminal", JobType.CRIMINAL );
	}
	
	public static final Criminal getInstance()
	{
		return instance;
	}
	
	@EventHandler
	public void onDrug( PlayerInteractEvent e )
	{
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			final ItemStack item = e.getItem();
			
			if( item == null ) return;
			
				
			if( item.getType().equals( Material.SUGAR ) && item.getItemMeta().getDisplayName().equalsIgnoreCase( "Cocaine" )){
					e.getPlayer().addPotionEffect( new PotionEffect( PotionEffectType.SPEED, 300, 20 ) );
			}
		}
	}
	
	private static class CocaineCommand extends AbstractJobCommand 
	{
		public CocaineCommand() 
		{
			super( JobType.CRIMINAL );
		}

		@Override
		public String[] getNames() 
		{
			return new String[]{ "cocaine" };
		}

		@Override
		public boolean runImpl(Player player, User user, String[] args) throws Exception 
		{
			final ItemStack sucre = player.getItemInHand();
			
			if( !( sucre.getType().equals( Material.SUGAR ) ) )
			{
				player.sendMessage(lang.getColoredMessage("de", "job_criminal_onlysugar"));
				return true;
			}
			
			sucre.getItemMeta().setDisplayName("Cocaine");
			return true;
		}

		@Override
		public boolean validArgsRange( String[] args ) 
		{
			return ( args.length == 1 );
		}

		@Override
		public void sendUsageImpl( CommandSender sender ) 
		{
			sender.sendMessage("/cocaine");
		}
		
	}
	
	@Override
	public AbstractCommand[] jobCommands() 
	{
		return new AbstractCommand[]{ new CocaineCommand() };
	}
}
