package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.job.DressedJob;

import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Not finished
 */
public class Firefighter extends DressedJob 
{
	
	private static final Firefighter instance = new Firefighter();
	
	private Firefighter() 
	{
		super( "Firefighter", Color.RED );
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
	public CuboidType whereToStart() {
		return CuboidType.FireDepartment;
	}
	
}
