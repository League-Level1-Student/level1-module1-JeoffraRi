package _01_thejavajetgame;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import game_tools.Game;
import game_tools.GameScene;

public class AsteroidsGame implements GameScene, ActionListener {

	/*
	 * 1. In Java a constructor is a special method used to initialize objects If a
	 * class does have any constructors java will use a default constructor
	 */
	Jet rocket = new Jet(582, 891);
	/*
	 * However if you tried running this you'll notice that we get a message popping
	 * up saying you need to write a constructor. This is because if we let it run
	 * we would get a null pointer exception since the rocket's collision box wouldn't be
	 * initialized by the default constructor and would have no value.
	 */

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	int score = 0;
	Random ran = new Random();
	Game game = new Game("Asteroids");
	ArrayList<Enemy> asteroids = new ArrayList<Enemy>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	Timer spawner;
	Timer rof;
	int lives = 3;
	boolean firstWave = false;
	static Image sky;
	static{
		loadSky("sky.jpeg");
	}		
	public AsteroidsGame() {
		if (rocket.collisionBox == null) {
			JOptionPane.showMessageDialog(null, "Rocket.java needs a constructor");
			System.exit(0);
		}
		game.setScene(this);
		game.addController(rocket);
		game.start();
		game.setSize(WIDTH, HEIGHT);
		rof = new Timer(0, this);
		spawner = new Timer(5000, this);
		spawner.start();
		asteroids.add(new Enemy(50, 50));
		asteroids.add(new Enemy(50, 50));

	}

	public void update() {
		if (rocket.restart) {
			rocket = new Jet();
			game.addController(rocket);
			lives = 3;
			score = 0;
			asteroids.clear();
		}
		if (lives > -1)
			score++;
		rocket.update();
		if (rocket.fire && !rof.isRunning()) {
			bullets.add(new Bullet(rocket.x, rocket.y, rocket.angle));
			rof.start();
		}
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update();
			if (!asteroids.get(i).isAlive) {
				asteroids.remove(i);
				i--;
			}
			checkCollisions();
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			if (!bullets.get(i).isAlive) {
				bullets.remove(i);
				i--;
			}
		}
	}
	
	static void loadSky(String imageFile) {
		 
        try {
            sky = ImageIO.read(Enemy.class.getResourceAsStream(imageFile));
            
            
	  
        } catch (Exception e) {
         
    }
}

	@Override
	public void draw(Graphics g) {
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(sky,0, 0, WIDTH, HEIGHT,null);
		for (Enemy asteroid : asteroids) {
			asteroid.draw(g);
		}
		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}
		rocket.draw(g);
		g.setColor(Color.black);
		g.drawString("score: " + score, 50, 50);
		g.drawString("Lives: " + lives, WIDTH - 100, 50);
		g.setColor(Color.YELLOW);
		g.drawRect(rocket.x, rocket.y, rocket.collisionBox.width, rocket.collisionBox.height);
		update();
		if (lives < 0) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", WIDTH / 2, HEIGHT / 2);
			g.drawString("score: " + score, WIDTH / 2, HEIGHT / 2 + 30);
			g.drawString("'R' to Restart", WIDTH / 2, HEIGHT / 2 + 50);
		}
	}

	public void checkCollisions() {
		if (score>=300&&firstWave==false) {
			firstWave = true;
			Random gren = new Random();
			int num = gren.nextInt(1000);
			Random nerg = new Random();
			int mun = nerg.nextInt(1000);
			score=0;
			for(int i=0; i<5; i++) {
			asteroids.add(new Enemy(num, mun, 10 ,false));
			}
		}
		for (Enemy asteroid : asteroids) {
			if (asteroid.collisionBox.intersects(rocket.collisionBox)) {
				lives--;
				rocket = new Jet();
				game.addController(rocket);
			}
			for (Bullet bullet : bullets) {
				if (asteroid.isAlive && asteroid.collisionBox.intersects(bullet.coll)) {
					bullet.isAlive = false;
					asteroid.isAlive = false;
					score += 1000;
					if (asteroid.RADIUS > 20) {
						asteroids.add(new Enemy(asteroid.x, asteroid.y, asteroid.RADIUS / 2,true));
						asteroids.add(new Enemy(asteroid.x, asteroid.y, asteroid.RADIUS / 2,true));
					}
					return;
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == spawner) {
			int angle = ran.nextInt(360);
			int x = (int) (Math.cos(Math.toRadians(angle)) * WIDTH / 2) + WIDTH / 2;
			int y = (int) (Math.sin(Math.toRadians(angle)) * HEIGHT / 2) + HEIGHT / 2;

			asteroids.add(new Enemy(x, y));
		}
		if (e.getSource() == rof) {
			rocket.fire = false;
			rof.stop();
		}

	}

}
