package net.edgecraft.edgejobs.util;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UtilListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		
		User u = UserManager.getInstance().getUser(e.getPlayer().getName());
		
		if(!ConfigHandler.containsUser(u)){
			
			ConfigHandler.createUser(u);
			
		}
		
	}
	
}
