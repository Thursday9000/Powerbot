package org.thurs.dagonhai.tasks.fighting;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;
import org.thurs.dagonhai.tasks.Task;

public class Attack extends Task<ClientContext> {

	public Attack(ClientContext ctx) {
		super(ctx);
	}

	public static final Filter<Npc> monkFilter = new Filter<Npc>() {
		public boolean accept(final Npc monk) {
			return monk.name().contains("Monk") && !monk.inCombat();
		}
	};

	public boolean activate() {
		return !ctx.npcs.select().select(monkFilter).isEmpty() && !ctx.players.local().inCombat();
	}

	public void execute() {
		Npc monk = ctx.npcs.nearest().poll();
		ctx.camera.turnTo(monk);
		if (monk.inViewport()) {
			monk.interact("Attack");
			{
				while (!ctx.players.local().inCombat()) {
					Condition.sleep(50);
				}
			}
		}
	}
}
