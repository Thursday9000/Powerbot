package org.thurs.dagonhai.tasks.fighting;

import java.util.LinkedList;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;
import org.thurs.dagonhai.tasks.Task;

public class Attack extends Task<ClientContext> {

	public Attack(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return !ctx.npcs.select().id(7138, 7139, 7140, 6363).isEmpty()
				&& ctx.players.local().inCombat();
	}

	public void execute() {
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