import java.awt.Graphics;
import java.util.LinkedList;

import java.awt.*;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;


@Manifest
(name = "Exor's Woodcutter", description = "Chops regular trees", 
version = 1.10, authors = { "Exorcism" })

public class Main extends ActiveScript implements PaintListener, MessageListener{

	public void messageReceived(MessageEvent arg0) {
		if(arg0.getId() == 109 && arg0.getMessage().equalsIgnoreCase("You")){
			Variables.logsCut++;
			}
		}

	private final Color color1 = new Color(255, 255, 255);

    private final Font font1 = new Font("Arial", 1, 16);
    private final Font font2 = new Font("Arial", 0, 16);
    
    final Timer runTime = new Timer(0);
    long startTime = System.currentTimeMillis();
    
    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("Exor's Woodcutter", 25, 75);
        g.setFont(font2);
        g.drawString("RunTime:" + runTime.toElapsedString(), 25, 100);
        g.drawString("Logs Cut:" + Variables.logsCut, 25, 125);
    }


	// Node
		final LinkedList<Node> nodes = new LinkedList<Node>();

		@Override
		public int loop() {
			if (nodes == null || nodes.size() <= 0)
				return Random.nextInt(150, 250);
			for (final Node job : nodes.toArray(new Node[nodes.size()])) {
				if (job.activate()) {
					getContainer().submit(job);
					job.join();
			}
		}
			return Random.nextInt(150, 250);
	}
		//onStart
		@Override
		public void onStart() {
			
			//Drink brews
			nodes.add(new ChopWood.Cut());
			nodes.add(new DropWood.Drop());

			Mouse.setSpeed(Speed.VERY_FAST);
	}
}