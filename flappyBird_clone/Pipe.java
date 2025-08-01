package flappyBird_clone;
import java.awt.Color;
import java.awt.Graphics;
public class Pipe extends GameObject {
	
    private boolean upperPipe;
    
    

    public Pipe(int x, int y, boolean upperPipe, int height) {
    	
        super(x, y, 60, height);
        this.upperPipe = upperPipe;
        
    }

 
    public void draw(Graphics g) {
    	
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        x -= 5;
    }

    public boolean isUpperPipe() {
        return upperPipe;
    }

	
}
