/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.entity.staticobjects;

import ir.ac.kntu.Renderer;
import ir.ac.kntu.animations.BombAnimations;
import ir.ac.kntu.animations.Sprite;
import ir.ac.kntu.entity.Entity;
import ir.ac.kntu.entity.StaticEntity;
import ir.ac.kntu.entity.boundedbox.RectBoundedBox;

import java.util.Date;

/**
 *
 * @author Ashish
 */
public class BlackBomb implements StaticEntity {
    public int positionX = 0;
    public int positionY = 0;
    private int height;
    private int width;
    private Sprite sprite;
    RectBoundedBox entityBoundary;
    BombAnimations bomb_animations;
    Date addedDate;
    int timerDurationInMillis = 2000; 
    STATE bombState;
    int layer;
    int scale=1;

    enum STATE
    {
        INACTIVE,   //INACTIVE when bomb's timer hasnt yet started
        ACTIVE,     //Active when bomb's timer has started and it will explode soon
        EXPLODING,  //when bomb is exploding
        DEAD;   //when the bomb has already exploded
    }
    
    public BlackBomb(int x, int y) {
        positionX = x;
    	positionY = y;
    	width = 16;
    	height = 16;
    	layer=-2;
    	setScale(2);
        bomb_animations=new BombAnimations(this);
        sprite=bomb_animations.getBlackBomb();
        entityBoundary = new RectBoundedBox(positionX, positionY, width * getScale(), height  * getScale());
        addedDate=new Date();
        bombState=STATE.ACTIVE;
    }
    
    public boolean isAlive(){
        STATE s = checkBombState();
        if(s==STATE.DEAD){
            return false;
        }
        else{
            if(s==STATE.ACTIVE||s==STATE.INACTIVE){
                return true;
            }
            return true;
        }
    }
    
    public STATE checkBombState(){
        if(new Date().getTime()>timerDurationInMillis+addedDate.getTime()){
            return STATE.DEAD;
        }else{
            return STATE.ACTIVE;
        }
    }
    
    @Override
    public boolean isColliding(Entity b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw() {
        Renderer.playAnimation(sprite);
    }

    @Override
    public void removeFromScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public RectBoundedBox getBoundingBox() {
        return entityBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return true;
    }

    @Override
    public int getLayer() { return layer; }

    @Override
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
