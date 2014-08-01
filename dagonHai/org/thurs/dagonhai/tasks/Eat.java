package org.thurs.dagonhai.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.resources.Task;
import org.thurs.dagonhai.resources.Variables;

public class Eat extends Task<ClientContext> {

	public Eat(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.players.local().healthPercent() < 60
				&& ctx.backpack.select().id(Variables.IDS_FOOD).peek().valid();
	}

	public void execute() {
		ctx.backpack.poll().interact("Eat");
	}

}