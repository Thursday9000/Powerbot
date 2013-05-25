import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ChopWood {

	public static class Cut extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Variables.treeID) <= 28 && SceneEntities.getNearest(Variables.treeID) != null;
		}

		@Override
		public void execute() {
			SceneObject tree = SceneEntities.getNearest(Variables.treeID);
			if (tree.isOnScreen()) {
				tree.interact("chop");
			} else {
				Camera.turnTo(tree);
			}
		}
	}
}