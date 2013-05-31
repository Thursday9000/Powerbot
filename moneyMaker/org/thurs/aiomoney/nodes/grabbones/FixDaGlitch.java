package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class FixDaGlitch extends Node {
	
	@Override
	public boolean activate() {
		return Variables.BONE_COW_AREA.contains(Players
				.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.BONE_COW_FIX).traverse();
		if (SceneEntities.getLoaded(45212) != null
				&& Variables.COW_AREA.contains(Players
						.getLocal())) {
			SceneEntities.getNearest(45212).interact("Open", "Gate");
			Timer t = new Timer(1350);
			while (t.isRunning() && SceneEntities.getNearest(45212) == null) {
				Task.sleep(10);
			}
		}
	}

}