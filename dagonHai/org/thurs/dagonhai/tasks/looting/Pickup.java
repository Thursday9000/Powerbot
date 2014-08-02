package org.thurs.dagonhai.tasks.looting;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GroundItem;
import org.thurs.dagonhai.tasks.Task;

public class Pickup extends Task<ClientContext> {
	final GroundItem loot = ctx.groundItems.id(557, 556, 559, 554, 555, 558)
			.nearest().poll();
	public static boolean profit = false;

	public Pickup(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.backpack.select().count() <= 27
				&& !ctx.groundItems.select().id(557, 556, 559, 554, 555, 558)
						.isEmpty() && profit;
	}

	public void execute() {
		if (loot.valid()) {
			if (loot.inViewport()) {
				loot.interact("Take", loot.name());
				Condition.sleep(2000);
			} else {
				ctx.camera.turnTo(loot);
			}
		}
	}
}
