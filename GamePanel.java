import java.awt.*;
import java.awt.event.*;
import  javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{


	static final int SCREEN_WIDTH=800;
	static final int SCREEN_HEIGHT=800;
	static final int UNIT_SIZE=25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static int DELAY=8;
	final int x[]= new int[GAME_UNITS];
	final int y[]= new int[GAME_UNITS];
	int bodyParts;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	
	GamePanel(){
	random = new Random();
	this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
	//this.setBackground(Color.LIGHT_GRAY);
	this.setBackground(new Color(75,126,168));
	this.setFocusable(true);
	this.addKeyListener(new MyKeyAdapter());
	startGame();
}
public void startGame() {
	newApple();
	bodyParts=6;
	running= true;
	timer = new Timer(70,this);
	timer.start();
}

public void newstartGame() {
	newApple();
	running= true;
	timer = new Timer(DELAY,this); 
	timer.start();
}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	draw(g);
}
public void draw (Graphics g) {
if(running) {
	/*for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
		g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE,SCREEN_HEIGHT);
		g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
	}*/
	{
	g.setColor(Color.RED);
	//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
	g.fillOval(appleX, appleY,UNIT_SIZE,UNIT_SIZE);
	}
for(int i=0;i<bodyParts;i++) {
	if(i==0) {
		g.setColor(Color.white);
		g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
		
		{
		//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
		g.setColor(Color.black);
		g.setFont(new Font("Mv boli",Font.BOLD,30));
		FontMetrics metrics =getFontMetrics(g.getFont());
	g.drawString("Score: "+applesEaten+"      press R for incressing difficulti level", (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten+"      press R for incressing difficulti level\""))/2, g.getFont().getSize());
		}
	}
	else {
  	g.setColor(Color.black);
	//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
		g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
		
	}
}
}
else {
	gameOver(g);
}
}
public void move() {
	for(int i=bodyParts;i>0;i--) {
		x[i]=x[i-1];
		y[i]=y[i-1];
		
	}
	switch(direction){
	case 'U':
		y[0]=y[0]-UNIT_SIZE;
		break;
	case 'D':
		y[0]=y[0]+UNIT_SIZE;
		break;
	case 'L':
		x[0]=x[0]-UNIT_SIZE;
		break;
	case 'R':
		x[0]=x[0]+UNIT_SIZE;
		break;
	}
}
public void checkApple() {
	if((x[0] == appleX )&&(y[0]==appleY)) {
		bodyParts++;
		applesEaten++;
		newApple();
	}
}
public void newApple() {
	appleX=random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
appleY=random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
}
public void checkcollision() {
	for(int i=bodyParts;i>0;i--) {
		if(x[0]==x[i]&&(y[0]==y[i])) {
			running = false;
		}
		if(x[0]<0) {
			running=false;
		}
		if(x[0]>SCREEN_WIDTH) {
			running=false;
		}
		if(y[0]<0) {
			running=false;
		}
		if(x[0]>SCREEN_HEIGHT) {
			running=false;
		}
		if(!running) {
			timer.stop();
		
		}
	}
}
public void gameOver(Graphics g) {
	g.setColor(Color.red);
	g.setFont(new Font("Mv boli",Font.BOLD,45));
	FontMetrics metrics =getFontMetrics(g.getFont());
g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(running) {
		move();
		checkApple();
		checkcollision();
		
	}
	repaint();
	
}
public class MyKeyAdapter extends KeyAdapter{
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(direction !='R') {
				direction = 'L';
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(direction !='L') {
				direction = 'R';
			}
			break;
		case KeyEvent.VK_UP:
			if(direction !='D') {
				direction = 'U';
			}
			break;
		case KeyEvent.VK_DOWN:
			if(direction !='U') {
				direction = 'D';
			}
			break;
		case KeyEvent.VK_A:
			if(direction !='R') {
				direction = 'L';
			}
			break;
		case KeyEvent.VK_D:
			if(direction !='L') {
				direction = 'R';
			}
			break;
		case KeyEvent.VK_W:
			if(direction !='D') {
				direction = 'U';
			}
			break;
		case KeyEvent.VK_S:
			if(direction !='U') {
				direction = 'D';
			}
			break;
		case KeyEvent.VK_R:
			startGame();
           
			}
		}
	}
	}




