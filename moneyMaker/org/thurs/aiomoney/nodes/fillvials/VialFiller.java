package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.thurs.aiomoney.resources.Variables;

public class VialFiller extends Node {
	

	@Override
	public boolean activate() {

		return Inventory.getCount(Variables.VIALS) == 28;
	}

	@Override
	public void execute() {
		Variables.status = "Filling Vials";
		Variables.fount = SceneEntities.getNearest(Variables.FOUNTAIN_ID);
		Item vial = Inventory.getItem(Variables.VIALS);
		WidgetChild fill = Widgets.get(1371, 5);
		WidgetChild camera = Widgets.get(548, 3);
		boolean selected = false;
		if (!vial.getWidgetChild().contains(Mouse.getLocation()) && !selected) {
			Mouse.click(camera.getCentralPoint(), true);
			Camera.turnTo(Variables.fount);
			vial.getWidgetChild().interact("Use");
			selected = true;

		} else {
			Variables.fount.click(true);
			final Timer timer = new Timer(5000);
			while (!fill.validate()) {
				Task.sleep(100);
				if (!timer.isRunning())
					break;
			}
			if (fill.validate()) {
				Widgets.get(1371, 51).getChild(0).click(true);
				Timer t = new Timer(740);
				while (t.isRunning() && Widgets.get(1371, 51).getChild(0).visible()) {
					Task.sleep(10);
				}
				Widgets.get(1371, 62).getChild(0).interact("Select");
				while (t.isRunning() && Widgets.get(1371, 62).getChild(0).visible()) {
					Task.sleep(10);
				}
				Widgets.get(1371, 44).getChild(2).interact("Select");
				Timer t1 = new Timer(2350);
				while (t1.isRunning() && Widgets.get(1371, 44).getChild(2).visible()) {
					Task.sleep(10);
				}
				Mouse.click(fill.getCentralPoint(), true);

			}
		}
	}
}
