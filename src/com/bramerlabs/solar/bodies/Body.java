package com.bramerlabs.solar.bodies;

import java.awt.*;

public class Body {

    // the position of the body
    public float x, y;

    // the mass of the body
    public float mass;

    // the velocity of the body
    public float dx, dy;

    // the radius of the body
    public int radius;

    // the color of the body
    public Color color;

    /**
     * default constructor for position and initial velocities
     * @param x - the x position of this celestial body
     * @param y - the y position of this celestial body
     * @param dx - the initial x velocity of this celestial body
     * @param dy - the initial y velocity of this celestial body
     */
    public Body(float mass, float x, float y, float dx, float dy, int radius, Color color) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
    }

    /**
     * apply a force to this object
     * @param fx - the x component of the force
     * @param fy - the y component of the force
     */
    public void force(float fx, float fy) {

        // calculate the component accelerations
        float ax = fx / mass;
        float ay = fy / mass;

        // accelerate the velocities
        this.dx += ax;
        this.dy -= ay;
    }

    /**
     * updates the position
     */
    public void update() {
        this.x += dx;
        this.y -= dy;
    }

    /**
     * paints the planet
     * @param g - the graphics component
     * @param width - the width of the screen
     * @param height - the height of the screen
     */
    public void paint(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        g2d.fillOval((int)(this.x+width/2-radius), (int)(this.y+height/2-radius), radius*2, radius*2);
    }

    @Override
    public String toString() {
        return "Body{" +
                "x=" + x +
                ", y=" + y +
                ", dx=" + dx +
                ", dy=" + dy +
                '}';
    }
}
