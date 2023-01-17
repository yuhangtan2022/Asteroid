/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.Font;
import java.applet.AudioClip;
/**
 *
 * @author RealProgramming4Kids
 */

public class Asteroids extends Applet implements KeyListener, ActionListener{
    ArrayList<Asteroid> asteroidList;
    ArrayList<Bullets> bulletsList;
    ArrayList<Debris> explosionList;
    ArrayList<PowerUps> powerList;
    PowerUps power;
    Spacecraft ship;
    Timer timer;
    Image offscreen;
    Graphics offg;
    AudioClip laser,shipHit,asteroidHit,thruster,background;
    boolean upkey,leftkey,rightkey,spacekey,enterkey;
    int asteroidCounter;
    Font font = new Font("Courier", Font.BOLD, 30);
    Font font2 = new Font("Onyx", Font.BOLD, 30);
    // change size and font and bold or italic...
            
    
    /**
     * Initialization method that will be called after the 'applet' is loaded into
     * the browser.
     */
    
    public void init(){
        this.setSize(900, 600);
        this.addKeyListener(this);
        power = new PowerUps();
        ship = new Spacecraft();
        timer = new Timer(20,this);
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics(); 
        asteroidList = new ArrayList();
        for(int i = 0; i<6; i++){
            asteroidList.add(new Asteroid());
        }
        bulletsList = new ArrayList();
        explosionList = new ArrayList();
        
        powerList = new ArrayList();
        powerList.add(power);
        
        
        laser = getAudioClip(getCodeBase(),"laser80.wav");
        shipHit = getAudioClip(getCodeBase(),"explode1.wav");
        asteroidHit = getAudioClip(getCodeBase(),"explode0.wav");   
        thruster = getAudioClip(getCodeBase(),"thruster.wav");
        background = getAudioClip(getCodeBase(),"background.wav");
        background.loop();
    }

    // TODO overwrite start(), stop() and destroy() methods
    public void paint(Graphics g){
        offg.setColor(Color.black);
        offg.fillRect(0, 0, 900, 600);
        offg.setColor(Color.gray);
        for(int i = 0; i<explosionList.size(); i++){
            explosionList.get(i).paint(offg);
        }
        for(int i = 0; i<asteroidList.size(); i++){
            asteroidList.get(i).paint(offg);
        }
        offg.setColor(Color.blue);
        for(int i = 0; i<bulletsList.size(); i++){
            bulletsList.get(i).paint(offg);
        }
        offg.setColor(Color.green);
        if(ship.active == true){
            ship.paint(offg);
        }
        
        if(asteroidList.isEmpty()){
            offg.setFont(font);
            offg.setColor(Color.orange);
            offg.drawString("GAME OVER & YOU WON!", 300, 300);
        }
        
        if(ship.lives==0){
            offg.setFont(font2);
            offg.setColor(Color.red);
            offg.drawString("GAME OVER & YOU LOSE!", 350, 300);
            timer.stop();
        }
        offg.setColor(Color.magenta);
        if(power.counter>100 && power.active==true){
            power.paint(offg);
        }
        
        offg.setFont(font2);
        offg.setColor(Color.white);
        offg.drawString("Lives:"+ ship.lives, 20, 30);
        g.drawImage(offscreen,0,0,this);
        repaint();
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void keyTyped(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            rightkey = true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            leftkey = true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            upkey = true;
            thruster.loop();
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            spacekey = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            enterkey = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_H){
            
        }
         //To change body of generated methods, choose Tools | Templates.
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            rightkey = false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            leftkey = false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            upkey = false;
            thruster.stop();
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            spacekey = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            enterkey = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_H){
            
        }
        //To change body of generated methods, choose Tools | Templates.
    }
   
    public void keyCheck(){
        if(upkey == true){
            ship.accelerate();
        }
        if(upkey == false){
            ship.deaccelerate();
        }
        if(leftkey == true){
            ship.rotateLeft();
        }
        if(rightkey == true){
            ship.rotateRight();
        } 
        if(spacekey == true){
            fireBullets2();
        } 
        if(enterkey == true){
            init();
        } 
    }

    public void actionPerformed(ActionEvent e) {
        keyCheck();
        respawnedShip();
        power.updatePosition();
        ship.updatePosition();
        for(int i = 0; i<explosionList.size(); i++){
            explosionList.get(i).updatePosition();
            if(explosionList.get(i).counter==25){
                explosionList.remove(i);
            }
        }
        for(int i = 0; i<asteroidList.size(); i++){
            asteroidList.get(i).updatePosition();
            checkAsteroidsDistruction();
        }
        for(int i = 0; i<bulletsList.size(); i++){
            bulletsList.get(i).updatePosition();
            if(bulletsList.get(i).counter==50 || bulletsList.get(i).active==false){
                bulletsList.remove(i);
            }
        }
        checkCollisions();
    }
    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    public boolean collision(VectorSprite thing2, VectorSprite thing1){
        // thing2 is asteroidList.get(i), thing1 is ship
        // asteroidList.get(i) = rocks = an array of rocks = arraylist for rocks
        int x,y;
        for(int i = 0; i< thing1.drawShape.npoints; i++){
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if(thing2.drawShape.contains(x,y)){
                return true;
            }
        }

        for(int i = 0; i< thing2.drawShape.npoints; i++){
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if(thing1.drawShape.contains(x,y)){
                return true;
            }
        }
        return false;
    }
    public void checkCollisions(){
        for(int p = 0; p<powerList.size(); p++){
            if(collision(powerList.get(p), ship)){
                fireBullets();
                power.active=false;
            }
        }
        for(int i = 0; i<asteroidList.size(); i++){
            double random;
            if(collision(asteroidList.get(i), ship) == true && ship.active){
                ship.hit();
                asteroidList.get(i).active=false;
                random = Math.random()*5+5;
                // random is between 0 and 1
                for(int k = 0; k<random; k++){
                    explosionList.add(new Debris(ship.xposition, ship.yposition));
                }
                shipHit.play();
            }
            for(int j = 0; j<bulletsList.size(); j++){
                if(collision(asteroidList.get(i), bulletsList.get(j)) == true){
                    bulletsList.get(j).active=false;
                    asteroidList.get(i).active=false;
                    random = Math.random()*5+5;
                    for(int k = 0; k<random; k++){
                        explosionList.add(new Debris(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                    }
                    asteroidHit.play();
                }
            }
        }
    }
    public void respawnedShip(){
        if(ship.active == false && ship.counter > 50 && isRespawnedSafe()){
            ship.reset();
        }
    }
    public boolean isRespawnedSafe(){
        double x,y,h;
        for(int i = 0; i<asteroidList.size(); i++){
            x = asteroidList.get(i).xposition-450;
            y = asteroidList.get(i).yposition-300;
            h = Math.sqrt(x*x+y*y);
            
            if(h<100){
            // change safe range or radius
                return false;
            }
            
        }
        return true;
    }
    public void fireBullets(){
        if(ship.counter>5 && ship.active==true){
            // this is how you u print in java: System.out.println("fire bullet");
            bulletsList.add(new Bullets(ship.drawShape.xpoints[0],ship.drawShape.ypoints[0],ship.angle));
            ship.counter=0;
            laser.play();
        }
    }
    public void fireBullets2(){
        if(ship.counter>10 && ship.active==true){
            // this is how you u print in java: System.out.println("fire bullet");
            bulletsList.add(new Bullets(ship.drawShape.xpoints[0],ship.drawShape.ypoints[0],ship.angle));
            bulletsList.add(new Bullets(ship.drawShape.xpoints[1],ship.drawShape.ypoints[1],ship.angle));
            bulletsList.add(new Bullets(ship.drawShape.xpoints[2],ship.drawShape.ypoints[2],ship.angle));
            ship.counter=0;
            laser.play();
        }
    }
    public void checkAsteroidsDistruction(){
        for(int i = 0; i<asteroidList.size(); i++){
            if(asteroidList.get(i).active==false){
                if(asteroidList.get(i).size > 1){
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition + 5, asteroidList.get(i).yposition + 5, asteroidList.get(i).size-1));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition - 5, asteroidList.get(i).yposition - 5, asteroidList.get(i).size-1));
                }
                asteroidList.remove(i);
                //upgrade
                //asteroidCounter += 1;
                //for(int a = 0; a<bulletsList.size(); a++){
                    //if(asteroidCounter == 7){
                        //bulletsList.get(a).THRUST = 15;
                    //}
                    //if(asteroidCounter == 21){
                        //bulletsList.get(a).THRUST = 15;
                        //if(ship.counter>5 && ship.active==true){
                            //bulletsList.add(new Bullets(ship.drawShape.xpoints[1],ship.drawShape.ypoints[1],ship.angle));
                            //bulletsList.add(new Bullets(ship.drawShape.xpoints[1],ship.drawShape.ypoints[1],ship.angle));
                            //ship.counter=0;
                            //laser.play();
                        //}
                    //}
                //}
            }
        }
    }
}
