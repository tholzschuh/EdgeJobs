package net.edgecraft.edgejobs.job;

import java.util.Arrays;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.api.AbstractJob;
import net.edgecraft.edgejobs.api.AbstractSidejob;
import net.edgecraft.edgejobs.api.IJob;
import net.edgecraft.edgejobs.api.JobManager;
import net.edgecraft.edgejobs.util.ConfigHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJob extends AbstractCommand {

	private static final String[] jobnames = { "job", "beruf" };
	private static final String[] sidejobnames = { "nebenjob", "nebenberuf", "sidejob" };
	
	@Override
	public Level getLevel() {
		return Level.USER;
	}

	@Override
	public String[] getNames() {
		return new String[]{ "job", "beruf", "nebenjob", "nebenberuf", "sidejob" };
	}

	@Override
	public boolean runImpl(Player player, User user, String[] args) throws Exception {
		
		if(args[1].equalsIgnoreCase("join")){
			
			IJob job = null;
			
			if(Arrays.asList(jobnames).contains(args[0]))
				job = JobManager.getUserJob(user);
			else if(Arrays.asList(sidejobnames).contains(args[0]))
				job = JobManager.getUserSidejob(user);
			
			if(job == null){
				
				player.sendMessage(ChatColor.GRAY + "Du hast doch garkeinen Job!");
				
				return true;
			}
			
			job.equipPlayer(player);
			
			JobManager.setWorking(player, true);
			
			player.sendMessage(ChatColor.GREEN + "Du arbeitest jetzt!");
			return true;
		}
		else if(args[1].equalsIgnoreCase("leave")){

			IJob job = null;
			
			if(Arrays.asList(jobnames).contains(args[0]))
				job = JobManager.getUserJob(user);
			else if(Arrays.asList(sidejobnames).contains(args[0]))
				job = JobManager.getUserSidejob(user);
			
			if(job == null){
				
				player.sendMessage(ChatColor.GRAY + "Du hast doch garkeinen Job!");
				
				return true;
			}
			
			player.getInventory().clear();
			
			JobManager.setWorking(player, false);
			
			player.sendMessage(ChatColor.GREEN + "Du arbeitest jetzt!");
			return true;
		}
		
		if(args.length > 4){
			
			if(user.getLevel().value() >= Level.TEAM.value()){
				
				Player target = Bukkit.getPlayer(args[3]);
				
				if(!target.isOnline()){
					player.sendMessage(EdgeCore.errorColor + EdgeCore.getLang().getColoredMessage("de", "notfound"));
					return true;
				}
				
				if(args[2].equalsIgnoreCase("setjob")){
					
					AbstractJob job = JobManager.getJobByName(args[4]);
					
					if(job == null){
						player.sendMessage(EdgeCore.errorColor + EdgeCore.getLang().getColoredMessage("de", "jobnotfound"));
						return true;
					}
					
					ConfigHandler.setJob(UserManager.getInstance().getUser(target.getName()), job);
					
					return true;
				}
				else if(args[2].equalsIgnoreCase("setsidejob")){
					
					AbstractSidejob job = JobManager.getSidejobByName(args[4]);
					
					if(job == null){
						player.sendMessage(EdgeCore.errorColor + EdgeCore.getLang().getColoredMessage("de", "jobnotfound"));
						return true;
					}
					
					ConfigHandler.setSidejob(UserManager.getInstance().getUser(target.getName()), job);
					
					return true;
				}
				
			}
			else{
				player.sendMessage(EdgeCore.errorColor + EdgeCore.getLang().getColoredMessage("de", "nopermission"));
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public void sendUsage(CommandSender sender) {
		sender.sendMessage("/job <join/leave/(Team: setsidejob/setjob)");
		sender.sendMessage("/beruf <join/leave/(Team: setsidejob/setjob)");
		sender.sendMessage("/work <join/leave/(Team: setsidejob/setjob)");
		sender.sendMessage("/nebenjob <join/leave/(Team: setsidejob/setjob)");
		sender.sendMessage("/sidejob <join/leave/(Team: setsidejob/setjob)");
	}

	@Override
	public boolean sysAccess(CommandSender arg0, String[] arg1) {
		arg0.sendMessage("No support for console!");
		return true;
	}

	@Override
	public boolean validArgsRange(String[] args) {
		
		if(args.length > 1 && args[1].equalsIgnoreCase("join") || args[1].equalsIgnoreCase("leave")) return true;
		
		return args.length > 4;
	}

}
