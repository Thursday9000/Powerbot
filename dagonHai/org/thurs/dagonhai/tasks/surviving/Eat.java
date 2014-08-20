package org.thurs.dagonhai.tasks.surviving;

import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.resources.GUI;
import org.thurs.dagonhai.tasks.Task;

public class Eat extends Task<ClientContext> {
	public Eat(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.players.local().healthPercent() < 50
				&& !ctx.backpack.select().id(GUI.food.value).isEmpty();
	}

	public void execute() {		
		Item food = ctx.backpack.poll();
		food.interact("Eat");
	}

}