/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.entity.staticobjects;

import ir.ac.kntu.Renderer;
import ir.ac.kntu.animations.Sprite;
import ir.ac.kntu.entity.Entity;
import ir.ac.kntu.entity.StaticEntity;
import ir.ac.kntu.entity.boundedbox.RectBoundedBox;
import javafx.scene.paint.Color;

/**
 * @author kdost
 */
public class Wall implements StaticEntity {
    public int positionX = 0;
    public int positionY = 0;
    private int height;
    private int width;
    private Color wallColor;
    private Sprite sprite;
    RectBoundedBox entityBoundary;
    int layer;
    int scale=1;

    public Wall(int x, int y) {
        positionX = x;
        positionY = y;
        setScale(2);
        width = 16;
        height = 16;
        layer = 1;
        sprite = new Sprite(this, 16, 0, 348, 123, 1, width * getScale(), height * getScale() ,getScale(), false);
        entityBoundary = new RectBoundedBox(positionX, positionY, width * getScale(), height * getScale());
    }

    public void changeColor(Color color) {
        wallColor = color;
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
        return false;
    }

    @Override
    public int getLayer() { return layer; }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
