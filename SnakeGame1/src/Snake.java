import java.awt.Font;

import java.awt.Graphics2D;
import java.io.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JMenuItem;


import java.awt.event.*;
import javax.swing.*;


class Snake extends FlexiblePictureExplorer implements ImageObserver ,ActionListener, MouseListener {
	private Picture disp;
	private Picture playScreen;
	private Picture gamePiece;
	private Graphics2D graphics;
	private int playing;
	private ArrayList<Picture> snake = new ArrayList<Picture>();
	private String direction = "R";
	private int speed = 2;
	//JTextField typingArea;
	private int xCoordinateOfSnakePiece = 0;
	private int yCoordinateOfSnakePiece = 0;
	private Random rand = new Random();
	private int randomX = rand.nextInt(659) + 181;
	private int randomY = rand.nextInt(361) + 34;
	private int score = 0;
	private Picture apple;

	
	public Snake() {
		super(new Picture (1000, 1000));
		disp = new Picture(1000, 1000);
		graphics = disp.createGraphics();
		playScreen =  new Picture ("Pictures/MainPlayingScreen.jpg");
		graphics.drawImage(playScreen.getBufferedImage(), 0, 0, this);
		setImage(disp);
		//playing = true;
		this.setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		runTimer();
		snake.add(new Picture("Pictures/SnakePiece.jpg"));
		gamePiece = new Picture("Pictures/SnakePiece.jpg");
	
		//graphics = displ.createGraphics();
		//runTimer();
		//graphics.drawImage(gamePiece.getBufferedImage(), 100, 100, this);
		//setImage(displ);
		
		
		//long milliseconds = System.currentTimeMillis();
		//System.out.println(milliseconds);
		//create a "wait" method to start, when making program.
	    //every so-and-so milliseconds, run the function that moves the snake forward
	    //make a global variable, "direction", that changes according to the direction the snake is going
	    //just change the variable direction whenever needed, the function called by the timer will automatically use the variable and update whenever it is updated
		//updateImage();
	}

	


	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mouseClickedAction(DigitalPicture pict, Pixel pix) {
		// TODO Auto-generated method stub
		
		
		int row = pix.getCol();
		int column = pix.getRow();
		System.out.println(pix.getRow());
		System.out.println(pix.getCol());
		
		if ((((row <= 110) && (row >= 54))) && ((column <= 352) && (column >= 298))) {
			if(!direction.equals("D")) {
				direction = "U";
			}
		} else if ((((row <= 164) && (row >= 110))) && ((column <= 406) && (column >= 353))) {
			if(!direction.equals("R")) {
				direction = "L";
			}
		} else if ((((row <= 110) && (row >= 56))) && ((column <= 461) && (column >= 408))) {
			if(!direction.equals("U")) {
				direction = "D";
			}
		} else if ((((row <= 54) && (row >= 1))) && ((column <= 406) && (column >= 351))) {
			if(!direction.equals("L")) {
				direction = "R";
			}
		}
		
		
		
	}
	  
	  public void runTimer() {

	        try {
	            Picture snakePiece = new Picture("Pictures/SnakePiece.jpg");
	            xCoordinateOfSnakePiece = 250;
	            yCoordinateOfSnakePiece = 200;
                appleCreater();
                playing = 1;
	            while (playing != 0) {
	               
	                Thread.sleep(10);
	                graphics.drawImage(playScreen.getBufferedImage(), 0, 0, this);
	                graphics.drawImage(snakePiece.getBufferedImage(), xCoordinateOfSnakePiece , yCoordinateOfSnakePiece, this);
		                
	                // move the snake forward however many pixels, according to direction, speed
	                if(direction.equals("L")) {
		                xCoordinateOfSnakePiece = xCoordinateOfSnakePiece - speed;

	                }else if(direction.equals("R")) {
		                xCoordinateOfSnakePiece = xCoordinateOfSnakePiece + speed;
	                } else if(direction.equals("U")) {
	                	yCoordinateOfSnakePiece = yCoordinateOfSnakePiece - speed;
	                } else if(direction.equals("D")) {
	                	yCoordinateOfSnakePiece = yCoordinateOfSnakePiece + speed;
	                }
	                if(playing ==1) {
	                	appleCreater();
	                }else if(checkAppleCollisions(randomX, randomY,30)) {
	                	appleCreater();
	                	score++;
	                }else {
	            		graphics.drawImage(apple.getBufferedImage(), randomX, randomY, this);
	                }
	                String score1 = score + "";
	                graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	                graphics.drawString(score1, 300, 475); 
	                playing++;
	                if (checkCollisions(xCoordinateOfSnakePiece, yCoordinateOfSnakePiece, 30)) {
	                	playing = 0;
	                } 
	                
	                setImage(disp);
	            }
	        } catch (InterruptedException e) {
	        }

	    }


	    
	    private void updateImage() {
	    	Picture snakePiece = new Picture("Pictures/SnakePiece.jpg");
	    	graphics.drawImage(snakePiece.getBufferedImage(), 10, 100, this);
	    	graphics.drawImage(playScreen.getBufferedImage(), 0, 0, this);
			graphics.drawImage(snakePiece.getBufferedImage(), 600, 600, this);
	    	
	    }
	    
	    public boolean checkCollisions(int x, int y,int widthAndHeight) {
	    	int leftX = x;
	    	int rightX = x + widthAndHeight;
	    	int topY = y;
	    	int bottomY = y + widthAndHeight;
	    	if (leftX <181 || rightX >870 || topY <34 || bottomY>425) {
	    		//returns true if the object is not within the bounds of the box
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public boolean checkAppleCollisions(int x, int y, int widthAndHeight) {
	    	int leftXOfApple = x;
	    	int rightXOfApple = x + widthAndHeight;
	    	int topYOfApple = y;
	    	int bottomYOfApple = y + widthAndHeight;

	    	int leftXOfSnake = xCoordinateOfSnakePiece;
	    	int rightXOfSnake = xCoordinateOfSnakePiece + widthAndHeight;
	    	int topYOfSnake = yCoordinateOfSnakePiece;
	    	int bottomYOfSnake = yCoordinateOfSnakePiece + widthAndHeight;

	    	if ((rightXOfSnake > leftXOfApple) && ((leftXOfSnake < rightXOfApple)) && (bottomYOfSnake > topYOfApple) && (topYOfSnake < bottomYOfApple)) {
	    		//if they are colliding return true    		
	    		return true;
	    	} else {
	    		//System.out.println("false");
	    		return false;
	    	}

	    }

	    	public void appleCreater() {

	    	apple = new Picture("Pictures/Apple.jpg");
	    	//boolean loopEnder = true;
	    	randomX = rand.nextInt(659) + 181;
	    	randomY = rand.nextInt(361) + 34;
	    	while (checkAppleCollisions(randomX,randomY,30)||checkCollisions(randomX,randomY, 30)) {
	    		randomX = rand.nextInt(659) + 181;
	    		randomY = rand.nextInt(361) + 34;
	    		//graphics.drawImage(snakePiece.getBufferedImage(), randomX, randomY, this);
	    	  }
	    	//System.out.println(checkAppleCollisions(randomX,randomY,30));

    		graphics.drawImage(apple.getBufferedImage(), randomX, randomY, this);
	    	
	    	}
	    	    	

	    	public class MyKeyAdapter extends KeyAdapter{
	    		@Override
	    		public void keyPressed(KeyEvent e) {
	    			// TODO Auto-generated method stub
	    			//System.out.println(e.getKeyLocation());
	    			//System.out.println(e.getKeyCode());
	    			switch (e.getKeyCode()) {
	    				case 37:
	    					if(!direction.equals("R")) {
	    						direction = "L";
	    						//System.out.println("H");
	    					}
	    					break;
	    				case 39:
	    					if(!direction.equals("L")) {
	    						direction = "R";
	    					}
	    					break;
	    				case 38:
	    					if(!direction.equals("D")) {
	    						direction = "U";
	    					}
	    					break;
	    				case 40:
	    					if(!direction.equals("U")) {
	    						direction = "D";
	    					}
	    					break; 
	    			}
	    		}
	    	} 	
	public static void main(String[] args) {
		new Snake();
	}
}

	