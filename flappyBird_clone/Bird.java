package flappyBird_clone;
import java.awt.Color;
import java.awt.Graphics;
public class Bird extends GameObject {
	
    private int velocity;

    public Bird(int x, int y) {
    	
        super(x, y, 40, 40);
        this.velocity = 0;
    }

    
    
    public void draw(Graphics g) {
    	
    	//bird fall sprite
    	if(velocity>0){
    		
    	g.setColor(Color.red);
        g.fillArc(x+12, y-15, width, height,100,25);
        g.fillArc(x+35, y-10, width-10, height,200,-25);
        g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
        g.setColor(Color.orange);
        g.fillArc(x, y-5, width + 40, height,200,-45);
        g.drawOval(x, y, width, height);
        
        //bird flap sprite
    	}else{
    		
    	g.setColor(Color.red);
    	g.fillArc(x+12, y-15, width, height,110,25);
    	g.fillArc(x+35, y-10, width-10, height,210,-25);	
    	g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
        g.setColor(Color.orange);
        g.fillArc(x, y-5, width+40, height,200,45);
        g.drawOval(x, y, width, height);
        
        }
    }

    public void fall() {
    	
        velocity += 2;
        y += velocity;
    }

    public void flap() {
    	
        velocity = -15; 
    }
}

