package _08_SolarSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game_tools.Game;
import game_tools.GameControlScene;
/*
 * Background:
 * Our solar system has 8 planets that orbit around the sun:
 * Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, and Neptune
 * While each planet is spherical, they are different sizes, colors,
 * and distances from the sun.
 * 
 * Objective:
 * Create a planet object for each of the planets in our solar system and
 *   - make them distinct colors
 *   - place them in the correct relative distance from the sun.
 *   - make them the correct relative size.
 *   - make them orbit around the sun at the correct relative speed.
 * 
 * 1. In the Planet class:
 *    a. Add member variables for:
 *       - planet color
 *       - distance from sun
 *       - orbital period (how many days it takes the planet to circle the sun)
 *    b. Assign the member variables in the constructor
 * 
 * 2. In this class:
 *    a. Create Planet objects as member variables for each planet using the
 *       information for each planet below.
 *    b. Call each planet object's draw method inside this class' draw method.
 *
 * EXTRA: Add moons for each planet that has them!
 *
 *        |              | Average distance  | Days to orbit | Number of
 * Body   | Diameter (km)| from the sun (km) | around sun    | moons
 * -------+--------------+-------------------+---------------+-----------+
 * Sun    | 1,391,000    | 0                 | N/A           | N/A
 * Mercury| 4,879        | 57,900,000        | 88            | 0
 * Venus  | 12,104       | 108,200,000       | 224.7         | 0
 * Earth  | 12,756       | 149,600,000       | 365.2         | 1
 * Mars   | 6,792        | 227,900,000       | 687           | 2
 * Jupiter| 142,984      | 778,600,000       | 4,331         | 79
 * Saturn | 120,536      | 1,433,500,000     | 10,747        | 82
 * Uranus | 51,118       | 2,872,500,000     | 30,589        | 27
 * Neptune| 49,528       | 4,495,100,000     | 59,800        | 14
 * Pluto  | 2,377        | 6,000,000,000     | 90,520        | 5
 * https://nssdc.gsfc.nasa.gov/planetary/factsheet/
 */

public class SolarSystem implements GameControlScene {
    static final int DISPLAY_WIDTH = 1000;
    static final int DISPLAY_HEIGHT = DISPLAY_WIDTH;
    static final int CENTER_X = DISPLAY_WIDTH / 2;
    static final int CENTER_Y = DISPLAY_HEIGHT / 2;
    static final int SUN_RADIUS_PIXELS = (1391 / 2) / 20;
    static final int SUN_DIAMETER_PIXELS = 2 * SUN_RADIUS_PIXELS;
    static final int MS_PER_DAY = 10;

    /*
     * Member variables
     */
    Color saturnColor = new Color(191, 185, 143);
    Color jupiterColor = new Color(153, 88, 3);
    Color uranusColor = new Color(148, 230, 235);
    Color neptuneColor = new Color(66, 2, 242);
    
    int sunX, sunY;
    Long startTimeMs = null;
    Game gameFrame = new Game("Solar System");
    Planet mercury = new Planet(12,88, Color.GRAY,57);
    Planet venus = new Planet(12,224, Color.ORANGE,108);
    Planet earth = new Planet(12,365, Color.BLUE,149);
    Planet mars = new Planet(12,687, Color.RED,228);
    Planet jupiter = new Planet(12,4331, jupiterColor,778);
    Planet saturn = new Planet(12,10747, saturnColor, 1433);
    Planet uranus = new Planet(12,30589, uranusColor, 2872);
    Planet neptune = new Planet(12,59800, neptuneColor, 4495);
    Planet pluto = new Planet(12,59800, saturnColor, 6000);
    
   
    		
    public SolarSystem() {
        gameFrame.setScene(this);
        gameFrame.start();
        gameFrame.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        
        /*
         * Add Earth's moon
         */
        earth.addMoon();
        for(int m=0; m<2; m++) {
        mars.addMoon();
        }
        for(int j=0; j<79; j++) {
        jupiter.addMoon();
        }
        for(int s=0; s<82; s++) {
        saturn.addMoon();
        }
        for(int u=0; u<27; u++) {
        uranus.addMoon();
        }
        for(int n=0; n<14; n++) {
        neptune.addMoon();
        }
        for(int p=0; p<5; p++) {
        pluto.addMoon();
        }
        
        /*       | Number of
        * Body   | moons
        * -------+-----------+
        * Sun    | N/A
        * Mercury| 0
        * Venus  | 0
        * Earth  | 1
        * Mars   | 2
        * Jupiter| 79
        * Saturn | 82
        * Uranus | 27
        * Neptune| 14
        * Pluto  | 5 
        */
       
        sunX = CENTER_X - SUN_RADIUS_PIXELS;
        sunY = CENTER_Y - SUN_RADIUS_PIXELS;
        startTimeMs = System.currentTimeMillis();
    }

    int getDays() {
        if(startTimeMs != null) {
            return (int)((System.currentTimeMillis() - startTimeMs) / MS_PER_DAY);
        }
        
        return 0;
    }

    @Override
    public void draw(Graphics g) {
        /*
         * Space
         */
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        
        /*
         * Days
         */
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        g.drawString("Days: " + getDays(), 0, 50);
        
        /*
         * Sun
         */
        g.setColor(Color.YELLOW);
        g.fillOval(sunX, sunY, SUN_DIAMETER_PIXELS, SUN_DIAMETER_PIXELS);
        
        int numDays = getDays();
        
        /*
         * Add planets here
         */
        mercury.draw(g, numDays);
        venus.draw(g, numDays);
        earth.draw(g, numDays);
        mars.draw(g, numDays);
        jupiter.draw(g, numDays);
        saturn.draw(g, numDays);
        uranus.draw(g, numDays);
        neptune.draw(g, numDays);
        pluto.draw(g, numDays);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
}
