
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RealProgramming4Kids
 */
public class Asteroid extends VectorSprite{
    int size;
    public Asteroid(){
        size = 3;
        initializeAsteroid();
    }
    public Asteroid(double x, double y, int s){
        size = s;
        initializeAsteroid();
        xposition = x;
        yposition = y;
    }
    public void initializeAsteroid(){
        shape = new Polygon();
        shape.addPoint(15*size, 6*size);
        shape.addPoint(7*size, 17*size);
        shape.addPoint(-13*size,8*size);
        shape.addPoint(-11*size,-10*size);
        shape.addPoint(12*size,-16*size);
        drawShape = new Polygon();
        drawShape.addPoint(15*size, 6*size);
        drawShape.addPoint(7*size, 17*size);
        drawShape.addPoint(-13*size,8*size);
        drawShape.addPoint(-11*size,-10*size);
        drawShape.addPoint(12*size,-16*size);
        double h,a;
        h = Math.random()*400+100;
        a = Math.random()*2*Math.PI;
        xposition = Math.cos(a)*h + 450;
        yposition = Math.sin(a)*h + 300;
        h = Math.random()+2;
        a = Math.random()*2*Math.PI;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
        ROTATION = 0.1;
        active = true;
        if(size==2){
            xspeed = Math.cos(a)*h+1;
            yspeed = Math.sin(a)*h+1;
            ROTATION = 0.2;
        }
        if(size==1){
            xspeed = Math.cos(a)*h+2;
            yspeed = Math.sin(a)*h+2;
            ROTATION = 0.3;
        }
        // SPEED CHANGE
    }
    public void updatePosition(){
        angle += ROTATION;
        super.updatePosition();
        //super means calling parent method and this method
    }
}
