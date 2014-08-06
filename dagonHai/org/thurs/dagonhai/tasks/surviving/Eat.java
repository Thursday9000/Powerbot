package org.thurs.dagonhai.tasks.surviving;

import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.DagonHai;
import org.thurs.dagonhai.tasks.Task;

public class Eat extends Task<ClientContext> {
	

	public Eat(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.players.local().healthPercent() < 50
				&& !ctx.backpack.select().id(DagonHai.food.value).isEmpty();
	}

	public void execute() {		
		Item food = ctx.backpack.id(DagonHai.food.value).poll();
		food.interact("Eat");
	}

}