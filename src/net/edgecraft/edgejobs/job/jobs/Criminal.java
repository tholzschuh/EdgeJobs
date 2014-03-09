package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractJobCommand;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Criminal extends AbstractJob {

	private static Criminal instance;
	
	private Criminal() {
		super("Criminal", ConfigHandler.getJobPay("Criminal"));
	}
	
	public static Criminal getInstance(){
		if(instance == null) return new Criminal();
		else return instance;
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
		public void sendUsage(CommandSender arg0) {
			arg0.sendMessage("/<command> <- Du musst Zucker in der Hand halten");
		}

		@Override
		public boolean validArgsRange(String[] arg0) {
			return true;
		}

		@Override
		public void sendUsageImpl(CommandSender arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{
				new CommandCocaine(),
		};
	}

	@Override
	public void printHelp(User u) {
		printHelpSentence(u, "=== Der Kriminelle ===");
		printHelpSentence(u, "Du bist der Abschaum der Stadt! Alle denken so, jedoch respektieren sie dich auch; denn ohne dich kriegen sie");
		printHelpSentence(u, "ihren Stoff nicht. Den kannst nur du Herstellen und somit verticken.");
		printHelpSentence(u, "Aber pass auf, dass du den Boss nicht ver��rgerst...");
		printHelpSentence(u, "== Commands ==");
		printHelpSentence(u, "");
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
