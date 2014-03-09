package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.CommandHandler;

public class JobCommands extends CommandHandler {

	private static final JobCommands instance = new JobCommands();
	
	private JobCommands() {}
	
	public static final JobCommands getInstance() {
		return instance;
	}
	
	public void registerCommand( AbstractCommand cmd ) {
		if( cmd == null ) return;
		registerCommand( cmd );
	}
	
}
