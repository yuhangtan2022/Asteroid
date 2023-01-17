
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
public class Debris extends VectorSprite{
    public Debris(double x, double y){
        shape = new Polygon();
        shape.addPoint(1, 1);
        shape.addPoint(-1, -1);
        shape.addPoint(-1, 1);
        shape.addPoint(1, -1);
        drawShape = new Polygon();
        drawShape.addPoint(1, 1);
        drawShape.addPoint(-1, -1);
        drawShape.addPoint(-1, 1);
        drawShape.addPoint(1, -1);
        xposition = x;
        yposition = y;
        double a;
        a = Math.random()*2*Math.PI;
        xspeed = Math.cos(a)*a;
        yspeed = Math.sin(a)*a;
    } 
}
