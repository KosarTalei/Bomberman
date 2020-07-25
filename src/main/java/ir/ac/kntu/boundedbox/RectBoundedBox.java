package ir.ac.kntu.boundedbox;

import javafx.geometry.Rectangle2D;

public class RectBoundedBox {

    private double x;
    private double y;
    private double width;
    private double height;
    private Rectangle2D boundary;

    public RectBoundedBox(double x, double y, double w, double h){
        this.x=x;
        this.y=y;
        width=w;
        height=h;
        boundary = new Rectangle2D(x, y, width, height);
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public void setBoundary(Rectangle2D boundaryRect){
        boundary = boundaryRect;
    }

    public boolean checkCollision(RectBoundedBox b) {
        return b.getBoundary().intersects(getBoundary());
    }

    public void setPosition(int x, int y, double reductionPercent) {
    	this.x = x;//player height and width//+(int)(30*reductionPercent);
    	this.y = y;//+(int)(30*reductionPercent);
    	boundary = new Rectangle2D(this.x, this.y, width, height);
    }

}
