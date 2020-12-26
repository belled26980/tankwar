package object;

import java.awt.*;

//所有物件的父類別
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image[] image;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        width = image[0].getWidth(null);
        height = image[0].getHeight(null);
    }

    public abstract void draw(Graphics g);
}
