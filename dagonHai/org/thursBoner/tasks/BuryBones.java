package org.thursBoner.tasks;

import org.powerbot.script.Filter;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.thursBoner.resources.*;

public class BuryBones extends Task<ClientContext> {

	public BuryBones(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.inventory.select().id(GUI.boneID).count() == 28;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}


}
