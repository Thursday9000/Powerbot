package org.thurs.dagonhai.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GroundItem;
import org.thurs.dagonhai.resources.Task;
import org.thurs.dagonhai.resources.Variables;

public class Pickup extends Task<ClientContext> {

	
	public Pickup(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.backpack.select().count() <= 27
				&& ctx.groundItems
						.select()
						.id(Variables.LOOT_ID)
						.nearest().peek().valid() && Variables.profit == true;
	}

	public void execute() {
		final GroundItem loot = ctx.groundItems.select().id(Variables.LOOT_ID).nearest().poll();
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
