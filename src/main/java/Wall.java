import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject {
    private boolean horizontal;    //水平
    private int bricks;            //磚塊數量

    public Wall(int x, int y, boolean horizontal, int bricks, Image[] image) {
        super(x, y, image);
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g) {
        if (horizontal) {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x + i * width, y, null);
            }
        } else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x, y + i * height, null);
            }
        }
    }
    @Override
    public Rectangle getRectangle() {
        if(horizontal){
            return new Rectangle(x,y,width*bricks,height);
        }else{
            return new Rectangle(x,y,width,height*bricks);
        }
    }
}
