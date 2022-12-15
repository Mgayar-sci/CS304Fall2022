/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soldier;

import Soldier.AnimGLEventListener3.Directions;


/**
 *
 * @author moham
 */
public class Bullet {
    long start, lifeTime;
    Directions direction;
    float x, y;
    boolean fired;
    float initX, initY;
    
    public Bullet(Directions direction, float x, float y, int lifetime){
        this.direction = direction;
        this.x = initX = x;
        this.y = initY = y;        
        this.fired = true;
        start = System.currentTimeMillis();
        lifeTime = lifetime;
    }
    
    public void invalidate(){
        fired = start+lifeTime > System.currentTimeMillis();
//        int dX = (initX - x) * (initX - x);
//        int dY = (initY - y) * (initY - y);
//        this.fired = dX + dY < 15000;
    }

    void bounce(int X, int Y) {
        if(fired){
            switch(direction)
            {
                case up: bounceUp(Y);break;
                case right: bounceRight(X); break;
                case down: bounceDown(Y);break;
                case left: bounceLeft(X); break;
                case up_left: bounceUpLeft(Y);break;
                case up_right: bounceUpRight(X, Y); break;
                case down_left: bounceDownLeft();break;
                case down_right: bounceDownRight(X); break;
            }
        }
    }
    void bounceUp(int Y){
        direction = ++y < Y-10 ? Directions.up: Directions.down;
    }
    void bounceDown(int Y){
        direction = --y > 0 ? Directions.down: Directions.up;
    }
    void bounceRight(int X){
        direction = ++x < X-10 ? Directions.right: Directions.left;
    }
    void bounceLeft(int X){
        direction = --x > 0 ? Directions.left: Directions.right;
    }
    void bounceUpRight(int X, int Y){
        direction = ++y < Y-10 ? Directions.up_right: Directions.down_right;
        if(direction==Directions.up_right)
            direction = ++x < X-10 ? Directions.up_right: Directions.up_left;
        if(direction==Directions.down_right)
            direction = ++x < X-10 ? Directions.down_right: Directions.down_left;
    }
    void bounceUpLeft(int Y){
        direction = ++y < Y-10 ? Directions.up_left: Directions.down_left;
        if(direction==Directions.up_left)
            direction = --x > 0 ? Directions.up_left: Directions.up_right;
        if(direction==Directions.down_left)
            direction = --x > 0 ? Directions.down_left: Directions.down_right;
    }
    void bounceDownRight(int X){
        direction = --y > 0 ? Directions.down_right: Directions.up_right;
        if(direction==Directions.down_right)
            direction = ++x < X-10 ? Directions.down_right: Directions.down_left;
        if(direction==Directions.up_right)
            direction = ++x < X-10 ? Directions.up_right: Directions.up_left;
    }
    void bounceDownLeft(){
        direction = --y > 0 ? Directions.down_left: Directions.up_left;
        if(direction==Directions.down_left)
            direction = --x > 0 ? Directions.down_left: Directions.down_right;
        if(direction==Directions.up_left)
            direction = --x > 0 ? Directions.up_left: Directions.up_right;
    }
}
