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
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.INV_FLAX) < 27
				&& SceneEntities.getLoaded(Variables.SPINNER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Spinning...";
		Camera.setPitch(3);
		SceneObject spinner = SceneEntities.getNearest(Variables.SPINNER);
		if (spinner.isOnScreen()
				&& SceneEntities.getLoaded(Variables.SPINNER) != null && !Widgets.get(1370).getChild(38).visible() && Widgets.get(1251).getChild(41).visible()) {
			spinner.interact("Spin", "Spinning wheel");
			Timer t = new Timer(1500);
			while (t.isRunning() && !Widgets.get(1370).getChild(38).visible()) {
				Task.sleep(20);
			}
			if (Widgets.get(1370).getChild(38).visible()) {
				Widgets.get(1370).getChild(38).interact("Make 27 Bow string");
				Timer ti = new Timer(2500);
				while (ti.isRunning() && !Widgets.get(1370).getChild(38).visible()) {
					Task.sleep(40);
				}
			}
		} else {
			Camera.turnTo(spinner);
		}
	}
}