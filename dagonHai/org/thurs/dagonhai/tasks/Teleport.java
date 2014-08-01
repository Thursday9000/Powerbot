package org.thurs.dagonhai.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.resources.Task;
import org.thurs.dagonhai.resources.Variables;

public class Teleport extends Task<ClientContext> {

	public Teleport(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.players.local().healthPercent() < 15
				&& ctx.backpack.select().id(Variables.IDS_FOOD).count() == 0
				&& !(ctx.backpack.select().id(Variables.IDS_TAB).count() == 0);
	}

	public void execute() {
		ctx.backpack.select().id(Variables.IDS_TAB).poll().interact("Break");
	}

}
