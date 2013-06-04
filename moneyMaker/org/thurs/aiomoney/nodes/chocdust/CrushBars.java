package org.thurs.aiomoney.nodes.chocdust;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.thurs.aiomoney.resources.Variables;

public class CrushBars extends Node {
	final private WidgetChild CONFIRM_BUTTON = Widgets.get(1370, 35);

	@Override
	public boolean activate() {
		Player player = Players.getLocal();
		return Inventory.getCount(Variables.CHOCOLATE_BAR) == 28
				&& Variables.VARROCK_BANK.contains(player) && player.isIdle()
				&& !Bank.isOpen();
	}

	@Override
	public void execute() {
		Variables.status = "Crushing bars...";
		if (!CONFIRM_BUTTON.visible()) {
			Item chocolateBar = Inventory.getItem(Variables.CHOCOLATE_BAR);

			if (chocolateBar.getWidgetChild().interact("Powder",
					chocolateBar.getName())) {

				for (Timer t = new Timer(2000); t.isRunning()
						&& !CONFIRM_BUTTON.visible(); Task.sleep(50))
					;
			}

		} else {
			WidgetChild chocBarSelect = Widgets.get(1371, 44).getChild(1);
			if (chocBarSelect.getTextureId() == -1) {
				if (chocBarSelect.interact("Select")) {
					for (Timer t = new Timer(2000); t.isRunning()
							&& chocBarSelect.getTextureId() == -1; Task
							.sleep(50))
						;
				}
			} else if (CONFIRM_BUTTON.interact("Make")) {
				for (Timer t = new Timer(2000); t.isRunning()
						&& CONFIRM_BUTTON.visible(); Task.sleep(50))
					;
			}
		}
	}
}