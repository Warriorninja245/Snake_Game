import java.awt.Graphics2D;
import java.io.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JMenuItem;


import java.awt.event.*;
import javax.swing.*;


class Snake extends FlexiblePictureExplorer implements ImageObserver ,ActionListener, MouseListener, KeyListener{
	private Picture disp;
	private Picture playScreen;
	private Picture gamePiece;
	private Graphics2D graphics;
	private boolean playing;
	private ArrayList<Picture> snake = new ArrayList<Picture>();
	private String direction = "L";
	private int speed = 2;
	//private JButton keyBoard;
	JTextField typingArea;

	
	public Snake() {
		super(new Picture (1000, 1000));
		disp = new Picture(1000, 1000);
		graphics = disp.createGraphics();
		playScreen =  new Picture ("Pictures/MainPlayingScreen.jpg");
		graphics.drawImage(playScreen.getBufferedImage(), 0, 0, this);
		setImage(disp);
		playing = true;
		runTimer();
		snake.add(new Picture("Pictures/SnakePiece.jpg"));
		gamePiece = new Picture("Pictures/SnakePiece.jpg");
		JButton button = new JButton("Clear");
        button.addActionListener(this);
         
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
		//graphics = displ.createGraphics();
		//runTimer();
		//graphics.drawImage(gamePiece.getBufferedImage(), 100, 100, this);
		//setImage(displ);
		
		
		long milliseconds = System.currentTimeMillis();
		System.out.println(milliseconds);
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
		System.out.println("X: " + row);
		System.out.println("Y: " + column);
		if ((((row <= 110) && (row >= 54))) && ((column <= 352) && (column >= 298))) {
			direction = "U";
			//System.out.println(System.currentTimeMillis());
		} else if ((((row <= 164) && (row >= 110))) && ((column <= 406) && (column >= 353))) {
			direction = "L";
		} else if ((((row <= 110) && (row >= 56))) && ((column <= 461) && (column >= 408))) {
			direction = "D";
		} else if ((((row <= 54) && (row >= 1))) && ((column <= 406) && (column >= 351))) {
			direction ="R";
		}
		
		
		
	}
	
	  private void setUpNextAndPreviousButtons() {
		  
	  }
	  
	  public void runTimer() {

	        try {
	            Picture snakePiece = new Picture("Pictures/SnakePiece.jpg");
	            int xCoordinateOfSnakePiece = 250;
	            int yCoordinateOfSnakePiece = 200;
	            while (playing == true) {
	               // System.out.println(xCoordinateOfSnakePiece);
	                // System.out.println("A");
	                // updateImage();
	            	
	                Thread.sleep(10);
	                graphics.drawImage(playScreen.getBufferedImage(), 0, 0, this);
	                graphics.drawImage(snakePiece.getBufferedImage(), xCoordinateOfSnakePiece , yCoordinateOfSnakePiece, this);
		                
	                appleCreater();
	                // move the snake forward however many pixels, according to direction, speed
	                if(direction.equals("L")) {
		                xCoordinateOfSnakePiece = xCoordinateOfSnakePiece + speed;

	                }else if(direction.equals("R")) {
		                xCoordinateOfSnakePiece = xCoordinateOfSnakePiece - speed;
	                } else if(direction.equals("U")) {
	                	yCoordinateOfSnakePiece = yCoordinateOfSnakePiece - speed;
	                } else if(direction.equals("D")) {
	                	yCoordinateOfSnakePiece = yCoordinateOfSnakePiece + speed;
	                }
	                //xCoordinateOfSnakePiece = xCoordinateOfSnakePiece + 10;
	                // yCoordinateOfSnakePiece = yCoordinateOfSnakePiece + 10;'
	                if (checkCollisions(xCoordinateOfSnakePiece, yCoordinateOfSnakePiece, 30)) {
	                	playing = false;
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
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public void appleCreater() {
            Picture snakePiece = new Picture("Pictures/SnakePiece.jpg");
	    	Random rand = new Random();	    	
	    	boolean loopEnder = true;
	    	int randomX = rand.nextInt(659) + 181;
	    	int randomY = rand.nextInt(361) + 34;
	
	    	if(checkCollisions(randomX,randomY,30)) {
	
	    	}else {
	    		loopEnder = false;
	    		while(loopEnder == false) {
	    	    	randomX = rand.nextInt(689) + 181;
	    	    	randomY = rand.nextInt(391) + 34;
		    		if(!checkCollisions(randomX,randomY,30)) {
		    			loopEnder = true;
		    		}
		    	}
	    	}
	    	
	    	graphics.drawImage(snakePiece.getBufferedImage(), randomX, randomY, this);
	    }
	 
	    /*
	    public void keyPressed() {
	    	keyBoard.addKeyListener(new KeyListener);
	    	
	    }
	    */
	   /*
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	            dx = -1;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 1;
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = -1;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 1;
	        }
	    }
	    */
	public static void main(String[] args) {
		//new Snake();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyLocation());
		System.out.println(e.getKeyCode());
		switch (e.getKeyLocation()) {
			case KeyEvent.VK_LEFT:
				if(!direction.equals("R")) {
					direction = "L";
					System.out.println("H");
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(!direction.equals("L")) {
					direction = "R";
				}
				break;
			case KeyEvent.VK_UP:
				if(!direction.equals("D")) {
					direction = "U";
				}
				break;
			case KeyEvent.VK_DOWN:
				if(!direction.equals("U")) {
					direction = "D";
				}
				break; 
		}
	}
		

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}