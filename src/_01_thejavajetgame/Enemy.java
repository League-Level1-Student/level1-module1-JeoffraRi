package _01_thejavajetgame;

import java.awt.*;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy {
	Random random = new Random();
	int RADIUS = random.nextInt(10) + 25;
	int DIAMETER = 2 * RADIUS;
	int x, y, speed;
	double xSpeed, ySpeed, angle;
	Rectangle collisionBox;
	boolean isAlive = true;
	static Image su57;
	static Image mig15;
	boolean isShot = false;

static{
	loadImage("su-57.png");
	loadMig("mig-15.png");
}		
		Enemy(int x, int y) {
	
		this.x = x;
		this.y = y;

		speed = random.nextInt(10) + 5;

		collisionBox = new Rectangle(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);

		angle = random.nextInt(360);
		xSpeed = (int) (Math.cos(Math.toRadians(angle)) * speed);
		ySpeed = (int) (Math.sin(Math.toRadians(angle)) * speed);
	}
	
	static void loadImage(String imageFile) {
		 
        try {
            su57 = ImageIO.read(Enemy.class.getResourceAsStream(imageFile));
	  
        } catch (Exception e) {
         
    }
}
	
	static void loadMig(String imageFile) {
		 
        try {
            mig15 = ImageIO.read(Enemy.class.getResourceAsStream(imageFile));
	  
        } catch (Exception e) {
         
    }
}

	public Enemy(int x, int y, int radius, boolean isShot) {
		this.x = x;
		this.y = y;
		this.isShot = isShot;
		RADIUS = radius;
		DIAMETER = radius * 2;
		speed = random.nextInt(10) + 5;

		collisionBox = new Rectangle(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);

		angle = random.nextInt(360);
		xSpeed = (int) (Math.cos(Math.toRadians(angle)) * speed);
		ySpeed = (int) (Math.sin(Math.toRadians(angle)) * speed);
	}

	void update() {
		x += xSpeed;
		y += ySpeed;

		if (x < 0)
			x = AsteroidsGame.WIDTH;
		if (x > AsteroidsGame.WIDTH)
			x = 0;
		if (y < 0)
			y = AsteroidsGame.HEIGHT;
		if (y > AsteroidsGame.HEIGHT)
			y = 0;

		collisionBox.setLocation(x - RADIUS, y - RADIUS);
	}

	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		if (isShot==false) {
		g.drawImage(su57,x - RADIUS, y - RADIUS, 80, 120, null);
		}else {
		g.drawImage(mig15,x - RADIUS, y - RADIUS, 90, 50, null);	
		}
		
	}

}
