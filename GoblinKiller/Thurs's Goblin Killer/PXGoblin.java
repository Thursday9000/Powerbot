
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.tab.Skills;

@Manifest(name = "PXGoblin", description = "Kills lumbridge goblins; Walks back on death", 
version = 1.10, authors = { "Thurs" })
public class PXGoblin extends ActiveScript implements PaintListener{
	
      
	//Startup
	@Override
    public void onStart() {
    	Mouse.setSpeed(Speed.VERY_FAST);
    	
    	Variables.startXP = Skills.getExperience(Skills.ATTACK)
    			+ Skills.getExperience(Skills.DEFENSE)
    			+ Skills.getExperience(Skills.STRENGTH)
    			+ Skills.getExperience(Skills.MAGIC)
    			+ Skills.getExperience(Skills.RANGE) - Variables.xpGained;
    
            provide(new Killing());
            provide(new WalkBack());
	}
	  
  	
      //Paint
      private Image getImage(String url) {
          try {
              return ImageIO.read(new URL(url));
          } catch(IOException e) {
              return null;
          }
      }

      private final Color color1 = new Color(255, 255, 255);
      
      private final Font font1 = new Font("Arial", 0, 18);
      
      private final Image img1 = getImage("http://i45.tinypic.com/2qdpslk.png"); //Thank you CookieMonsta for the paint!
      

      public void onRepaint(Graphics g1) {
      	Variables.runTime.toElapsedString();
      	
      	Variables.levelsGained = (Skills.getLevel(Skills.ATTACK)
      			+ Skills.getLevel(Skills.DEFENSE)
      			+ Skills.getLevel(Skills.STRENGTH)
      			+ Skills.getLevel(Skills.MAGIC)
      			+ Skills.getLevel(Skills.RANGE)
      			+ Skills.getLevel(Skills.CONSTITUTION));
      			
      	Variables.xpGained = (Skills.getExperience(Skills.ATTACK)
      			+ Skills.getExperience(Skills.DEFENSE)
      			+ Skills.getExperience(Skills.STRENGTH)
      			+ Skills.getExperience(Skills.MAGIC)
      			+ Skills.getExperience(Skills.RANGE)) - Variables.startXP;
      	
   
      	
      		System.out.println("Levels gained is =" + Variables.levelsGained);
      	  int kills = Variables.xpGained/34;
      	  int killsPH = (int)((kills * 3600000D) / (System.currentTimeMillis() - Variables.startTime));
      	  int XPPh = (int)((Variables.xpGained * 3600000D) / (System.currentTimeMillis() - Variables.startTime));
      	
          Graphics2D g = (Graphics2D)g1;
          g.drawImage(img1, 524, 223, null);
          g.setFont(font1);
          g.setColor(color1);
          g.drawString(Variables.runTime.toElapsedString(), 626, 279); 
          g.drawString("" + kills, 586, 325);
          g.drawString("" + killsPH, 630, 369);
          g.drawString("" + Variables.xpGained, 636, 414);
          g.drawLine(0,Mouse.getY(),Game.getDimensions().width,Mouse.getY());
          g.drawLine(Mouse.getX(),0,Mouse.getX(),Game.getDimensions().height); 
          
      
  }
      
      //Ignore this
LinkedList<Node> NODES = new LinkedList<Node>();
	  
      @Override
      public int loop() {
              if (Game.isLoggedIn()) {
                      for (Node node : NODES) {
                              if (node != null && node.activate())
                                      node.execute();
                      }
              }
              return Random.nextInt(250, 500);
      }

      private void provide(Node node) {
              if (node != null)
                      NODES.add(node);
      }
      public int loop1() {
          if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
                  return 1000;
          }
			return 0;


}
      
      }
  
      



  
    