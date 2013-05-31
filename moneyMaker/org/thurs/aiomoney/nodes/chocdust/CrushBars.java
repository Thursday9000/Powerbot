package org.thurs.aiomoney.nodes.chocdust;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;
import org.thurs.aiomoney.resources.Variables;

public class CrushBars extends Node {
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.CHOCOLATE_BAR) <= 28
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		Item bars = Inventory.getItem(Variables.CHOCOLATE_BAR);
		Variables.status = "Crushing bars...";
		if (Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Widgets.get(548).getChild(172).visible()) {
			bars.getWidgetChild().interact("Powder", "Chocolate bar");
			Timer t = new Timer(1550);
			while (t.isRunning()) {
				Task.sleep(10);
			}
			Widgets.get(1371, 44).getChild(2).interact("Select");
			Timer ti = new Timer(535);
			while (ti.isRunning()) {
				Task.sleep(500, 550);
			}
			Widgets.get(1370, 35).getChild(0)
					.interact("Make 28 Chocolate dust");
			Timer tim = new Timer(2340);
			while (tim.isRunning()) {
				Task.sleep(10);
			}
		}
	}
}