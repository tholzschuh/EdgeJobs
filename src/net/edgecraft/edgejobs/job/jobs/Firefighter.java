package net.edgecraft.edgejobs.job.jobs;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecuboid.cuboid.types.CuboidType;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Not finished
 */
public class Firefighter extends AbstractJob {

	private final ItemStack boots = new ItemStack( Material.LEATHER_BOOTS );
	private final ItemStack pants = new ItemStack( Material.LEATHER_LEGGINGS );
	private final ItemStack chestplate = new ItemStack( Material.LEATHER_CHESTPLATE );
	private final ItemStack helmet = new ItemStack( Material.LEATHER_HELMET );
	
	private static final Firefighter instance = new Firefighter();
	
	private Firefighter() {
		
		super( "Feuerwehr", ConfigHandler.getJobPay( "Feuerwehr" ) );
		prepareKit();
		
	}

	public static final Firefighter getInstance() {
		return instance;
	}
	
	private final void prepareKit(){
		
		final LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
		
		bootmeta.setDisplayName( "Feuerwehrschuhe" );
		bootmeta.addEnchant( Enchantment.PROTECTION_FIRE, 999999, true );
		boots.setItemMeta( bootmeta );
		
		
		
		final LeatherArmorMeta pantmeta = (LeatherArmorMeta) pants.getItemMeta();
		
		pantmeta.setDisplayName( "Feuerwehrhose" );
		pantmeta.addEnchant( Enchantment.PROTECTION_FIRE, 999999, true );
		
		pants.setItemMeta( pantmeta );
		
		final LeatherArmorMeta chestmeta = (LeatherArmorMeta) chestplate.getItemMeta();
		
		chestmeta.setDisplayName( "Feuerwehrschuhe" ); // TODO:
		chestmeta.addEnchant( Enchantment.PROTECTION_FIRE, 999999, true );
		
	    chestplate.setItemMeta( chestmeta );
		
		final LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();
		
		helmetmeta.setDisplayName( "Feuerwehrschuhe" ); // TODO:
		helmetmeta.addEnchant( Enchantment.PROTECTION_FIRE, 999999, true );
		
		helmet.setItemMeta( helmetmeta );
		
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
	public void equipPlayer( Player p ) {
		
		PlayerInventory inv = p.getInventory();
		
		ItemStack water = new ItemStack( Material.WATER_BUCKET );
		water.setDurability( (short) 15 );
		
		ItemStack mobile = new ItemStack( Material.FLINT );
		mobile.getItemMeta().setDisplayName( "Funkgerät" );
		
		//TODO: Save the inventory bevore clearing || Beta
		inv.clear();
		
		inv.setHelmet( helmet );
		inv.setChestplate( chestplate );
		inv.setBoots( boots );
		inv.setLeggings( pants );
		inv.addItem( mobile );
		inv.addItem( water );
		
	}
	
	@Override
	public CuboidType whereToStart() {
		return CuboidType.FireDepartment;
	}
	
}
