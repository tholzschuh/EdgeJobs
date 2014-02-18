package net.edgecraft.edgejobs.job;

import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgejobs.api.AbstractJob;

public class JobFiremen extends AbstractJob {

	public JobFiremen() {
		super("Feuerwehr", ConfigHandler.get);
	}

	@Override
	public AbstractCommand[] jobCommands() {
		// TODO Auto-generated method stub
		return null;
	}

}
