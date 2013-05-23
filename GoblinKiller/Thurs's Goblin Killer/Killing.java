import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;


 public class Killing extends Node {
            
            private final int[] GOBLIN_IDS = {11232, 11234, 11236, 11240, 12353,
                    12355, 12357};
           
            @Override
            public boolean activate() {
                    NPC goblin = NPCs.getNearest(GOBLIN_IDS);
                    return goblin!=null;
            }
     
            @Override
            public void execute() {
                   
                    if(!Players.getLocal().isInCombat()){
                            NPC[] goblins = NPCs.getLoaded(GOBLIN_IDS);
                           for(int i=0;i<goblins.length-1;i++){
                                    if(goblins[i].isInCombat()){
                                            goblins[i]=null;
                                    }
                            }
                            NPC goblin = npcGetNearest(goblins);
                            if(goblin!=null)
                                    if(!goblin.isOnScreen()){
                                            Camera.turnTo(goblin);
                                            Camera.setPitch(false);
                                    } else
                                            if(goblin.interact("Attack", "Goblin"))
                                                    Task.sleep(3000,3200);
                    }
                   
            }
           
            private NPC npcGetNearest(NPC[] array){
                    int best = 0;
                    double bestDistance = 100000;
                    for(int i=0;i<array.length-1;i++){
                            if(array[i]!=null && Calculations.distanceTo(array[i])<bestDistance){
                                    best=i;
                                    bestDistance = Calculations.distanceTo(array[i]);
                            }
                    }
                    return array[best];
            }

           
     
            }