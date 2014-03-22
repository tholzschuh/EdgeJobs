package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Not finished
 */
public class Firefighter extends DressedJob {
	
	private static final Firefighter instance = new Firefighter();
	
	private Firefighter() {
		
		super( "Firefighter", ConfigHandler.getJobPay( "Firefighter" ) );
	}

	public static final Firefighter getInstance() {
		return instance;
	}
	
	
	private static class CreateFireEventCommand extends AbstractCommand { 

		@Override
		public Level getLevel() {
			return Level.SUPPORTER;
		}

		@Override
		public String[] getNames() {
			return new String[]{ "createfireevent", "makefireevent" };
		}

		@Override
		public boolean runImpl( Player p, User u, String[] args ) {
			
			return true;
		}

		@Override
		public void sendUsageImpl( CommandSender sender ) {
			sender.sendMessage("/createfireevent");
		}

		@Override
		public boolean sysAccess(CommandSender sender, String[] args) {
			sender.sendMessage(lang.getColoredMessage("de", "noconsole"));
			return true;
		}

		@Override
		public boolean validArgsRange(String[] args) {
			return ( args.length == 1 );
		}
		
	}
	
	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{ new CreateFireEventCommand() };
	}

	@Override
	public void printHelp( User u ) {
		
		//TODO: WTF TÖTET ESSSSSSSS :)
		if( Level.canUse( u, Level.SUPPORTER ) ) {
			
			printHelpSentence(u, "=== Feuerwehr - Hilfe ===");
			printHelpSentence(u, "== Befehle ==");
			printHelpSentence(u, "/job join <-> Lege deine Arbeitskleidung <- Funktioniert nur in der Wache");
			printHelpSentence(u, "== Utensilien ==");
			printHelpSentence(u, "Du bekommst eine Feuerschutzrüstung, Wasser und dazu noch einen Transmitter für den Feuerwehrfunk.");
			printHelpSentence(u, "== Team ==");
			printHelpSentence(u, "/createfireevent oder /makefireevent - Wenn du irgendwo was Angezündet hast,");
			printHelpSentence(u, "kannst du hier einen Brand den Feuerwehrleiten übermitteln.");
			printHelpSentence(u, "Das kann sehr gut für Events benutzt werden. Have fun ;)");
			
		}
		else {
			
			printHelpSentence(u, "=== Feuerwehr - Hilfe ===");
			printHelpSentence(u, "== Befehle ==");
			printHelpSentence(u, "/job join <-> Lege deine Arbeitskleidung <- Funktioniert nur in der Wache");
			printHelpSentence(u, "== Utensilien ==");
			printHelpSentence(u, "Du bekommst eine Feuerschutzrüstung, Wasser und dazu noch einen Transmitter für den Feuerwehrfunk.");
			
		}
	}
	
	@Override
	public CuboidType whereToStart() {
		return CuboidType.FireDepartment;
	}
	
}
