package flappyBird_clone;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends GameObject{
	
	public Background(int x,int y,int width,int height) {
		
		super(x,y,width,height);
	}
	
	public void draw(Graphics g) {
		
		//sea
        g.setColor(Color.blue);
        g.fill3DRect(0, y, width, height,false);
        
        g.setColor(Color.white);
        g.fillOval(x+100, 100, 100, 60);
        g.fillOval(x+140, 80, 120, 80);
        g.fillOval(x+200, 100, 100, 60);

        //second cloud
        g.fillOval(x+400, 200, 100, 60);
        g.fillOval(x+440, 180, 120, 80);
        g.fillOval(x+500, 200, 100, 60);

        //third cloud
        g.fillOval(x+300, 350, 100, 60);
        g.fillOval(x+340, 330, 120, 80);
        g.fillOval(x+400, 350, 100, 60);
        
        x--;//moving cloud
        
        if(x== -600) {x=width;}//resetting cloud
    }

}
