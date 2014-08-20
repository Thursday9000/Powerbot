package org.thurs.dagonhai.tasks.looting;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GroundItem;
import org.thurs.dagonhai.tasks.Task;

public class Pickup extends Task<ClientContext> {
	public Pickup(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.backpack.select().count() <= 27
				&& !ctx.groundItems.select().id(557, 556, 559, 554, 555, 558)
						.isEmpty();
	}

	public void execute() {
		final GroundItem loot = ctx.groundItems
				.id(557, 556, 559, 554, 555, 558).nearest().poll();
		ctx.camera.turnTo(loot);

		if (loot.interact("Take")) {
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (!loot.valid());
				}
			});
		}
	}
}
