package net.edgecraft.edgejobs.util;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgecore.user.UserManager;
import net.edgecraft.edgejobs.api.JobManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UtilListener implements Listener {

	private static final UserManager users = EdgeCoreAPI.userAPI();
	
	@EventHandler
	public void onPlayerJoin( PlayerJoinEvent e ){
		
		JobManager.setWorking( e.getPlayer(), false );
		
		User u = users.getUser( e.getPlayer().getName() );
		
		ConfigHandler.createUser( u );
		
	}
	
}
