package com.bramerlabs.solar.main;

import com.bramerlabs.solar.bodies.Body;

import javax.swing.*;
import java.awt.*;

public class Main {

    // the celestial bodies
    public static Body sun, planet;

    // the frame
    public static JFrame frame;

    // the panel
    public static JPanel panel;

    public static int width = 800, height = 600;

    /**
     * main runnable
     * @param args - jvm arguments
     */
    public static void main(String[] args) {

        sun = new Body(1000, 0, 0, 0, 0, 60, new Color(200, 200, 0));
        planet = new Body(10, 150, 0, 0, 5f, 10, new Color(0, 100, 100));

        frame = new JFrame("Solar");
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                sun.paint(g, width, height);
                planet.paint(g, width, height);
            }
        };
        panel.setSize(new Dimension(width, height));
        frame.add(panel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.move(screenSize.width/2-width/2, screenSize.height/2-height/2);
        frame.setVisible(true);

        run();
    }

    static float G = 100f;

    /**
     * determines the force on a celestial body
     * @return - a float array containing the force and angle
     */
    public static float[] calculateForce() {
        float dx = planet.x - sun.x;
        float dy = planet.y - sun.y;
        float radius = (float) Math.sqrt(dx * dx + dy * dy);
        float angle = (float) Math.toDegrees(Math.atan(-dy / dx));
        float force = (G * planet.mass * sun.mass) / (radius * radius);
        float fx = (float) (force * Math.cos(angle));
        float fy = (float) (force * Math.sin(angle));
        return new float[]{fx, fy};
    }

    /**
     * handles the main game loop
     */
    public static void run() {
        while (true) {
            try {
                Thread.sleep(10); // delay 20 ms
            } catch (InterruptedException ignored) {
                break;
            }

            float[] planetForce = calculateForce();
            planet.force(planetForce[0], planetForce[1]);

            System.out.println(planetForce[0] + ", " + planetForce[1]);
            System.out.println(planet+"\n");

            sun.update();
            planet.update();
            panel.repaint();
        }
    }
}
