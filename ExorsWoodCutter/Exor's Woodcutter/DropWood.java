import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class DropWood {
	public static class Drop extends Node {
		@Override
		public boolean activate() {
			return Inventory.isFull() && Players.getLocal().isIdle() && !Players.getLocal().isMoving();
		}
		
		@Override
		public void execute() {
			while(Inventory.getItem(Variables.logID) != null) {
				Inventory.getItem(Variables.logID).getWidgetChild().interact("Drop");
				sleep(750, 1000);
			}
		}
	}
}
