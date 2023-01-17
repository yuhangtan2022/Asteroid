
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
public class PowerUps extends VectorSprite{
    public PowerUps(){
        shape = new Polygon();
        shape.addPoint(-6, 10);
        shape.addPoint(6, 10);
        shape.addPoint(12, 0);
        shape.addPoint(6, -10);
        shape.addPoint(-6, -10);
        shape.addPoint(-12, 0);
        drawShape = new Polygon();
        drawShape.addPoint(-6, 10);
        drawShape.addPoint(6, 10);
        drawShape.addPoint(12, 0);
        drawShape.addPoint(6, -10);
        drawShape.addPoint(-6, -10);
        drawShape.addPoint(-12, 0);
        double h,a;
        h = Math.random()*400+100;
        a = Math.random()*2*Math.PI;
        xposition = Math.cos(a)*h + 450;
        yposition = Math.sin(a)*h + 300;
        h = Math.random()+2;
        a = Math.random()*2*Math.PI;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
        active = true;
    }
}
