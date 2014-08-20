package org.thurs.dagonhai.tasks.surviving;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;
import org.thurs.dagonhai.tasks.Task;

public class Teleport extends Task<ClientContext> {

	public Teleport(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.players.local().healthPercent() <= 15
				&& ctx.backpack.select()
						.id(315, 329, 361, 373, 379, 385, 7946, 15272)
						.isEmpty()
				&& !(ctx.backpack.select().id(8007, 8008, 8009, 8010, 8011,
						8012, 8013).isEmpty());
	}

	public void execute() {
		Item tab = ctx.backpack.select().first().poll();
		tab.interact("Break");
	}

}
