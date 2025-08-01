//source code by Abrar Ashraf
package flappyBird_clone;

import javax.swing.JFrame;

public class Flappytest {
	
	
	 public static void main(String[] args) {
		 
		 	final int screenWidth = 800;
		 	final int screenHeight = 600;
		 
	        JFrame frame = new JFrame("Flappy Bird Clone");
	        MainGameLogic game = new MainGameLogic(screenWidth,screenHeight);
	        
	        frame.add(game);
	        frame.setSize(screenWidth, screenHeight);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }
}
