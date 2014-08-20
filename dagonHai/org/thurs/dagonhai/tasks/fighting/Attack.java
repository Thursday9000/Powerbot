package org.thurs.dagonhai.tasks.fighting;

import java.util.concurrent.Callable;

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
			return monk.name().contains("Goblin") && !monk.inCombat();
		}
	};

	public boolean activate() {
		return !ctx.npcs.select().select(monkFilter).isEmpty()
				&& !ctx.players.local().inCombat();
	}

	public void execute() {
		final Npc monk = ctx.npcs.nearest().poll();
		ctx.camera.turnTo(monk);
		if (monk.interact("Attack")) {
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return (!monk.valid());
				}
			});
		}
	}
}
