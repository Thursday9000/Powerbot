package org.thurs.dagonhai.tasks.surviving;

import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.tasks.Task;

public class Eat extends Task<ClientContext> {

	public static int foodID;
	public Eat(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.players.local().healthPercent() < 60
				&& !ctx.backpack.select().id(315, 329, 361, 373, 379, 385, 7946, 15272).isEmpty();
	}

	public void execute() {		
		Item food = ctx.backpack.select().id(315, 329, 361, 373, 379, 385, 7946, 15272).first().poll();
		food.interact("Eat");
	}

}