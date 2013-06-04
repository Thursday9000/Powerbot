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
		return Inventory.getCount(Variables.INV_FLAX) <= 27
				&& SceneEntities.getLoaded(25824) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Spinning...";
		SceneObject spinner = SceneEntities.getNearest(25824);
		if (SceneEntities.getLoaded(Variables.SPINNER) != null
				&& Inventory.getCount(Variables.INV_FLAX) >= 1) {
			spinner.interact("Spin", "Spinning wheel");
			Timer t = new Timer(1500);
			while (t.isRunning() && !Widgets.get(1370).getChild(38).visible()) {
				Task.sleep(20);
			}
			if (Widgets.get(1370).getChild(38).visible()) {
				Widgets.get(1370).getChild(38).interact("Make");
				Timer ti = new Timer(2500);
				while (ti.isRunning()
						&& Widgets.get(1370).getChild(38).visible()) {
					Task.sleep(40);
				}

			} else {
				Camera.turnTo(spinner);
			}
			Timer tim = new Timer(2500);
			while (tim.isRunning()
					&& !Widgets.get(1370).getChild(38).visible()) {
				Task.sleep(40);
			}
		}
		if (Widgets.get(1370).getChild(32).visible() && Inventory.getCount(Variables.BOW_STRING) == 27) {
			Widgets.get(1370).getChild(32).interact("Close");
		}
	}
}