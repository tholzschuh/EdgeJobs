package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.EdgeJobs;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractJobCommand;

import org.bukkit.Bukkit;
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

public class Criminal extends AbstractJob implements Listener {

	private static final Criminal instance = new Criminal();
	
	private Criminal() {
		super( "Criminal" );
		Bukkit.getServer().getPluginManager().registerEvents(this, EdgeJobs.getInstance());
	}
	
	public static final Criminal getInstance(){
		return instance;
	}
	
	@EventHandler
	public void onDrug(PlayerInteractEvent e){
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			ItemStack item = e.getItem();
			
			if(item != null){
				
				if(item.getType() == Material.SUGAR){
					
					if(item.getItemMeta().getDisplayName() == "Kokain"){
						
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 20));
						
						//TODO: Add happiness
						
					}
					
				}
				
			}
			
		}
		
	}
	
	private static class CommandCocaine extends AbstractJobCommand {

		public CommandCocaine() {
			super(getInstance());
		}

		@Override
		public String[] getNames() {
			return new String[]{ "cocaine", "kokain" };
		}

		@Override
		public boolean runImpl(Player player, User user, String[] args) throws Exception {
			
			ItemStack sucre = player.getItemInHand();
			
			if(!(sucre.getType() == Material.SUGAR)){
				
				player.sendMessage(lang.getColoredMessage("de", "job_criminal_onlysugar"));
				
				return true;
			}
			
			sucre.getItemMeta().setDisplayName("Kokain");
			
			return true;
		}

		@Override
		public boolean validArgsRange(String[] arg0) {
			return true;
		}

		@Override
		public void sendUsageImpl(CommandSender arg0) {
			arg0.sendMessage("/<command> <- Du musst Zucker in der Hand halten");
		}
		
	}
	
	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{
				new CommandCocaine(),
		};
	}

	@Override
	public void equipPlayerImpl(Player p) {
		//TODO: Fill function
	}

	@Override
	public CuboidType whereToStart() {
		return null;
	}

}
