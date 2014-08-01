package org.thurs.dagonhai.tasks;

import java.util.LinkedList;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;
import org.thurs.dagonhai.resources.Task;
import org.thurs.dagonhai.resources.Variables;

public class Attack extends Task<ClientContext> {

	public Attack(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return ctx.npcs.select().id(Variables.ENEMY_IDS).nearest().peek()
				.valid();
	}

	public void execute() {
		if (ctx.players.local().inCombat()) {
			return;
		}
		final LinkedList<Npc> npcs = new LinkedList<Npc>();
		ctx.npcs.addTo(npcs);
		for (final Npc npc : ctx.npcs) {
			if (npc.inCombat()) {
				npcs.remove(npc);
			}
		}
		final Npc monk = ctx.npcs.select(npcs).nearest().poll();
		if (monk.valid()) {
			if (!monk.inViewport()) {
				ctx.camera.turnTo(monk);
				ctx.camera.pitch(false);
			} else if (monk.interact("Attack")) {
				Condition.sleep(800);
			}
		}
	}
}