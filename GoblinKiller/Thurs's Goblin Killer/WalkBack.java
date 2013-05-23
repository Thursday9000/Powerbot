import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

   
        public class WalkBack extends Node {
        	
        	
        	Tile[] path = { new Tile(3220, 3218, 0), new Tile(3228, 3219, 0), new Tile(3234, 3224, 0),
                            new Tile(3240, 3226, 0), new Tile(3248, 3226, 0), new Tile(3247, 3232, 0),
                            new Tile(3249, 3238, 0) };
           
            Area goblins = new Area(new Tile[] { new Tile(3241, 3231, 0), new Tile(3241, 3248, 0), new Tile(3262, 3248, 0),
                            new Tile(3262, 3231, 0) });
     
            @Override
            public boolean activate() {
                    return !goblins.contains(Players.getLocal());
            }
     
            @Override
            public void execute() {
                    Walking.newTilePath(path).traverse();
                   
            }
     
    }