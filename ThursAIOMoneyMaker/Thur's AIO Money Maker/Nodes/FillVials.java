package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class FillVials {
	public static class VialFiller extends Node {

		@Override
		public boolean activate() {

			return Inventory.getCount(Resources.Variables.VIALS) == 28
					&& Resources.Variables.fillVials == true;
		}

		@Override
		public void execute() {
			Resources.Variables.fount = SceneEntities
					.getNearest(Resources.Variables.FOUNTAIN_ID);
			Item vial = Inventory.getItem(Resources.Variables.VIALS);
			WidgetChild fill = Widgets.get(1371, 5);
			WidgetChild camera = Widgets.get(548, 3);
			boolean selected = false;
			if (!vial.getWidgetChild().contains(Mouse.getLocation())
					&& !selected) {
				Mouse.click(camera.getCentralPoint(), true);
				Camera.turnTo(Resources.Variables.fount);
				vial.getWidgetChild().interact("Use");
				selected = true;

			} else {
				Resources.Variables.fount.click(true);
				final Timer timer = new Timer(5000);
				while (!fill.validate()) {
					Task.sleep(100);
					if (!timer.isRunning())
						break;
				}
				if (fill.validate()) {
					Widgets.get(1371, 51).getChild(0).click(true); // The widget
																	// wasn't
																	// working
																	// correctly,
																	// #noh8
					Timer t = new Timer(750);
					while (t.isRunning()) {
						Task.sleep(500, 1000);
					}
					Widgets.get(1371, 62).getChild(0).interact("Select");
					while (t.isRunning()) {
						Task.sleep(500, 1000);
					}
					Widgets.get(1371, 44).getChild(2).interact("Select");
					Timer t1 = new Timer(2350);
					while (t1.isRunning()) {
						Task.sleep(2000, 2500);
					}
					Mouse.click(fill.getCentralPoint(), true);

				}
			}
		}
	}

	public static class VialBanker extends Node {

		@Override
		public boolean activate() {

			return Inventory.getCount(Resources.Variables.WATER_VIALS) == 28
					&& Resources.Variables.fillVials == true;
		}

		@Override
		public void execute() {
			NPC banker = NPCs.getNearest(Resources.Variables.VIAL_BANKER);
			if (banker.isOnScreen()) {
				Resources.Variables.status = "Banking...";
				Bank.open();
				{
					while (Resources.Variables.Sleeping.isRunning()) {
						Task.sleep(3000, 6000);
					}
				}
				Bank.deposit(Resources.Variables.WATER_VIALS, 28);
				Bank.withdraw(Resources.Variables.VIALS, 28);
				Bank.close();
			} else {
				Camera.turnTo(banker);
				Camera.setPitch(3);

			}
		}
	}

	public static class InitialVialBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.VIALS) == 0
					&& Resources.Variables.fillVials == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(Resources.Variables.VIAL_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				Task.sleep(1000, 1500);
				if (!Inventory.contains(Resources.Variables.VIALS)
						&& Bank.isOpen()
						&& Bank.getItem(Resources.Variables.VIALS) != null) {
					Bank.withdraw(Resources.Variables.VIALS, 28);
				}
				Bank.close();
			}
		}
	}
}
