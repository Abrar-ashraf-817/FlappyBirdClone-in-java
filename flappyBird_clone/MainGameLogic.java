package flappyBird_clone;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MainGameLogic extends JPanel implements ActionListener, KeyListener {
	
    private Bird bird;
    private Background background;
    private ArrayList<Pipe> pipes;
    private Timer timer; 
    private boolean gameOver;
    private int score;
    private int highScore;
    private String highScoreFile;
    private int gapdiff;
    private boolean gameStarted;
    private JButton play; 
     
    
    public MainGameLogic(int screenWidth ,int screenHeight) {
    	
        bird = new Bird(100, 250);
        background = new Background(0,(screenHeight*2/3),screenWidth,200);
        pipes = new ArrayList<>();
        timer = new Timer(20 , this);
        
        score=0;
        gapdiff=0;
        gameOver = false;
        gameStarted=false;
        highScoreFile = "high_score.txt";
        
        play = new JButton("Play");
        play.setFont(new Font("Forte",Font.ITALIC,24));
        play.setBackground(new Color(0,0,139));
        play.setForeground(Color.white);
        play.setBounds(300, 10, 200, 80); 
        add(play);
        play.setActionCommand("Start");
        play.addActionListener(this);
        
        addPipes(gapdiff);
        readHighScore();
        
        setFocusable(true);
        addKeyListener(this);
        timer.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        setBackground(Color.CYAN);
        
        background.draw(g);
        bird.draw(g);
        
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        
        g.setColor(Color.blue);
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        g.drawString("Score: " + Integer.toString(score), 675, 40);
        
        if(!gameStarted) {
        	
        	 g.setColor(Color.red);
             g.setFont(new Font("Forte", Font.PLAIN, 25));
             g.drawString("Press space to fly", 50, 330);
        	
        }
        
        if (gameOver) {
        	
            g.setColor(Color.red);
            g.setFont(new Font("Impact", Font.ITALIC, 50));
            g.drawString("Game Over", 250, 300);
            
            if(score>highScore) {
            	g.drawString("NEW! High Score: "+ Integer.toString(score), 250, 100);	
            }else {
            	g.setFont(new Font("Forte", Font.ITALIC, 25));
            	g.drawString("High Score: "+ Integer.toString(highScore), 250, 100);
            }
        }
    }

 
    public void actionPerformed(ActionEvent e) {
    	
    	if(gameStarted) {
    		
    		if (!gameOver) {
    			
                bird.fall();
                
                for (Pipe pipe : pipes) {
                	
                    pipe.move();
                    
                }

                // Check for collisions
                checkCollisions();
                
                
                if (pipes.get(0).getX() == 360) {
                    addPipes(gapdiff);
                }
                
                if (pipes.get(0).getX() == 100 || pipes.get(2).getX() == 100) {
                	
                    score += 1;
                    
                    if(score%5==0 && gapdiff<80) {
                    	
                    	gapdiff+=20;
                    	
                    }
                    
                }

                // Add new pipes
                if (pipes.get(0).getX() < -60) {
                	
                    pipes.remove(0);
                    pipes.remove(0);
                    
                }
                
                repaint();
                
            }else{
            	
            	saveHighScore();
            	readHighScore();
            	
            }
    		
    	}else{
    		
    			if ("Start".equals(e.getActionCommand())) {
    				
    				gameStarted = true;
    				remove(play);
    				revalidate();
    				repaint();
    				
    		 }
    			
    	}
    	
    	   
    	
        
    }

    public void addPipes(int gapdiff) {
    	
        int gapHeight = 200 - gapdiff;
        int pipeHeight = new Random().nextInt(200) + 50;
        
        pipes.add(new Pipe(1200, 0, true, pipeHeight));
        pipes.add(new Pipe(1200, pipeHeight + gapHeight, false, 600 - pipeHeight - gapHeight));
        
        pipeHeight = new Random().nextInt(200) + 100;
        pipes.add(new Pipe(800, 0, true, pipeHeight));
        pipes.add(new Pipe(800, pipeHeight + gapHeight, false, 600 - pipeHeight - gapHeight));
        
        
    }

    public void checkCollisions() {
    	
        for (Pipe pipe : pipes) {
        	
            if (bird.getX() + bird.getWidth() >= pipe.getX() && bird.getX() <= pipe.getX() + pipe.getWidth()) {
            	
                if (pipe.isUpperPipe() && bird.getY() <= pipe.getHeight()) {
                	
                    gameOver = true;
                    
                } else if (!pipe.isUpperPipe() && bird.getY() + bird.getHeight() >= pipe.getY()) {
                	
                    gameOver = true;
                    
                }
            }
        }

        // Check for out of bounds
        if (bird.getY() > 600 || bird.getY() < -40) {
            gameOver = true;
        }
    }
    
    
    //reads a .txt file with high score 
    private void readHighScore() {
    	
        try (BufferedReader reader = new BufferedReader(new FileReader(highScoreFile))) {
        	
            String line = reader.readLine();
            
            if (line != null) {
            	
                highScore = Integer.parseInt(line);
                
            }
        } catch (IOException i) {
        	
            highScore = 0;
            
        }
    }
    
    // writes high score to a .txt  file
    private void saveHighScore() {
    	
        if (score > highScore) {
        	
            highScore = score;
            
            try (FileWriter writer = new FileWriter(highScoreFile)) {
            	
                writer.write(Integer.toString(highScore));
                
            } catch (IOException i) {
            	
                System.out.println(i);;
            }
        }
    }
    
  
    public void keyPressed(KeyEvent e) {
    	
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            bird.flap();
            
        }
    }

  
    public void keyReleased(KeyEvent e) {}

   
    public void keyTyped(KeyEvent e) {}

   
}

