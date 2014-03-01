package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.chat.Channel;
import net.edgecraft.edgecore.chat.ChatHandler;
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
public class JobFiremen extends AbstractJob {

	private ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	private ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
	private ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	private ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
	
	public JobFiremen() {
		super("Feuerwehr", ConfigHandler.getJobPay("Feuerwehr"));
		ChatHandler.getInstance().addChannel(new Channel("Feuerwehr", false, Material.FLINT)); //TODO: Change material
		prepareKit();
	}

	private void prepareKit(){
		
		LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
		
		bootmeta.setDisplayName("Feuerwehrschuhe");
		bootmeta.addEnchant(Enchantment.PROTECTION_FIRE, 999999, true);
		
		boots.setItemMeta(bootmeta);
		
		LeatherArmorMeta pantmeta = (LeatherArmorMeta) pants.getItemMeta();
		
		pantmeta.setDisplayName("Feuerwehrhose");
		pantmeta.addEnchant(Enchantment.PROTECTION_FIRE, 999999, true);
		
		pants.setItemMeta(pantmeta);
		
		LeatherArmorMeta chestmeta = (LeatherArmorMeta) chestplate.getItemMeta();
		
		chestmeta.setDisplayName("Feuerwehrschuhe");
		chestmeta.addEnchant(Enchantment.PROTECTION_FIRE, 999999, true);
		
		chestplate.setItemMeta(chestmeta);
		
		LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();
		
		helmetmeta.setDisplayName("Feuerwehrschuhe");
		helmetmeta.addEnchant(Enchantment.PROTECTION_FIRE, 999999, true);
		
		helmet.setItemMeta(helmetmeta);
		
	}
	
	private static class CommandCreateFireEvent extends AbstractCommand { 

		@Override
		public Level getLevel() {
			return Level.TEAM;
		}

		@Override
		public String[] getNames() {
			return new String[]{ "createfireevent", "makefireevent" };
		}

		@Override
		public boolean runImpl(Player p, User u, String[] args) throws Exception {
			
			//Location l = p.getLocation();
			
			// TODO: Fill function
			
			p.sendMessage(EdgeCore.sysColor + "Event erstellt!");
			
			return false;
		}

		@Override
		public void sendUsage(CommandSender arg0) {
			arg0.sendMessage("/<command>");
		}

		@Override
		public boolean sysAccess(CommandSender arg0, String[] arg1) {
			arg0.sendMessage("Not for Console!");
			return true;
		}

		@Override
		public boolean validArgsRange(String[] args) {
			return true;
		}
		
	}
	
	@Override
	public AbstractCommand[] jobCommands() {
		return new AbstractCommand[]{ new CommandCreateFireEvent() };
	}

	@Override
	public void printHelp(User u) {
		
		if(u.getLevel().value() >= Level.TEAM.value()){
			
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
		else{
			
			printHelpSentence(u, "=== Feuerwehr - Hilfe ===");
			printHelpSentence(u, "== Befehle ==");
			printHelpSentence(u, "/job join <-> Lege deine Arbeitskleidung <- Funktioniert nur in der Wache");
			printHelpSentence(u, "== Utensilien ==");
			printHelpSentence(u, "Du bekommst eine Feuerschutzrüstung, Wasser und dazu noch einen Transmitter für den Feuerwehrfunk.");
			
		}
		
	}

	@Override
	public void equipPlayer(Player p) {
		
		PlayerInventory inv = p.getInventory();
		//
		ItemStack water = new ItemStack(Material.WATER_BUCKET);
		water.setDurability((short) 15);
		//
		ItemStack flint = new ItemStack(Material.FLINT);
		flint.getItemMeta().setDisplayName("Funkgerät");
		
		//TODO: Save the inventory bevore clearing || Beta
		inv.clear();
		
		inv.setHelmet(helmet);
		inv.setChestplate(chestplate);
		inv.setBoots(boots);
		inv.setLeggings(pants);
		inv.addItem(new ItemStack(Material.FLINT));
		inv.addItem(water);
		
	}
	
	@Override
	public CuboidType whereToStart() {
		return CuboidType.FIREDEPARTMENT;
	}
	
}
