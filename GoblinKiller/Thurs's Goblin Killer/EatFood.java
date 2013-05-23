import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.methods.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class EatFood {
	// Eating
	public class eating extends Node {

		@Override
		public boolean activate() {
			return Players.getLocal().getHealthPercent() < 60
					&& Inventory.getItem(Variables.foodID) != null && Variables.eatMe == true;
		}

		@Override
		public void execute() {
			Inventory.getItem(Variables.foodID).getWidgetChild()
					.interact("Eat");

		}

	}
}