
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
public class Spacecraft extends VectorSprite{
    
    
    int lives;
    
    
    public Spacecraft(){
        lives = 10;
        shape = new Polygon();
        shape.addPoint(20, 0);
        shape.addPoint(-10, -15);
        shape.addPoint(-10, 15);
        drawShape = new Polygon();
        drawShape.addPoint(20, 0);
        drawShape.addPoint(-10, -15);
        drawShape.addPoint(-10, 15);
        xposition = 450;
        yposition = 300;
        ROTATION = 0.1;
        THRUST = 5;
        angle = - Math.PI/2;
        active = true;
    } 
    public void accelerate(){
        if(xspeed > 10){
            xspeed = 10;
        }
        else if(xspeed < -10){
            xspeed = -10;
        }
        else{
            xspeed += Math.cos(angle);
    //put THRUST in the code above to make a different type of spin for the ship
    //though glitch may occur
        }
        
        if(yspeed > 10){
            yspeed = 10;
        }
        else if(yspeed < -10){
            yspeed = -10;
        }
        else{
            yspeed += Math.sin(angle);
    //put THRUST in the code above to make a different type of spin for the ship
    //though glitch may occur
        }
    }
    public void deaccelerate(){
        if((int)xspeed < 0){
            xspeed ++;
        }
        else if((int)xspeed > 0){
            xspeed --;
        }
        else if((int)xspeed == 0){
            xspeed = 0;
        }
        if((int)yspeed < 0){
            yspeed ++;
        }
        else if((int)yspeed > 0){
            yspeed --;
        }
        else if((int)yspeed == 0){
            yspeed = 0;
        }
    }
    public void rotateRight(){
        angle += ROTATION;
    }
    public void rotateLeft(){
        angle -= ROTATION;
    }
    public void hit(){
        lives -= 1;
        active = false;
        counter = 0;
    }
    public void reset(){
        xposition = 450;
        yposition = 300;
        xspeed = 0;
        yspeed = 0;
        angle = - Math.PI/2;
        active = true;
    }
}