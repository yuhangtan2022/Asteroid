/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
/**
 *
 * @aupkeythor RealProgramming4Kids
 */
public class VectorSprite {
    boolean active;
    double xposition;
    double yposition;
    double xspeed;
    double yspeed;
    double angle;
    double ROTATION;
    double THRUST;
    Polygon shape;
    Polygon drawShape;
    int counter;
    int activeCounter;
    
    public void paint(Graphics g){
        g.drawPolygon(drawShape);
    }
    public void updatePosition(){
        counter++;
        activeCounter++;
        xposition += xspeed;
        yposition += yspeed;
        wrapAround();
        int x,y;
        for(int i = 0; i< shape.npoints; i++){
            x = (int) Math.round(shape.xpoints[i]*Math.cos(angle)-shape.ypoints[i]*Math.sin(angle));
            y = (int) Math.round(shape.xpoints[i]*Math.sin(angle)+shape.ypoints[i]*Math.cos(angle));
            drawShape.xpoints[i] = x;
            drawShape.ypoints[i] = y;
        }    
        drawShape.invalidate();
        drawShape.translate((int)Math.round(xposition), (int)Math.round(yposition));    
    }
    public void wrapAround(){
        if(xposition <0){
            xposition = 900;
        }
        if(xposition >900){
            xposition = 0;
        }
        if(yposition < 0){
            yposition = 600;
        }     
        if(yposition > 600){ 
            yposition = 0;
        }      
    }
}
