package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.thurs.aiomoney.resources.Variables;

public class Spinning extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.INV_FLAX) > 24
				&& SceneEntities.getLoaded(var.SPINNER) != null
				&& var.spinFlax;
	}

	@Override
	public void execute() {
		var.status = "Spinning...";
		Camera.setPitch(3);
		SceneObject spinner = SceneEntities
				.getNearest(var.SPINNER);
		if (spinner.isOnScreen()
				&& SceneEntities.getLoaded(var.SPINNER) != null) {
			spinner.interact("Spin", "Spinning wheel");
			Timer t = new Timer(1500);
			while (t.isRunning()) {
				Task.sleep(20);
			}
			if (Widgets.get(1370).getChild(38).visible()) {
				Widgets.get(1370).getChild(38)
						.interact("Make 27 Bow string");
				Timer ti = new Timer(40000);
				while (ti.isRunning()) {
					Task.sleep(40);
				}
			}
		} else {
			Camera.turnTo(spinner);
		}
	}
}