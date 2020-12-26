package object;

import javax.swing.*;
import java.awt.*;

public class Wall {
    private int x;
    private int y;
    private boolean horizontal;    //水平
    private int bricks;            //磚塊數量
    private Image image;

    public Wall(int x, int y, boolean horizontal, int bricks) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
        image=new ImageIcon("assets/images/brick.png").getImage();
    }
    public void draw(Graphics g){
        if(horizontal){
            for(int i=0;i<bricks;i++){
                g.drawImage(image,x+i*30,y,null);
            }
        }else{
            for(int i=0;i<bricks;i++){
                g.drawImage(image,x,y+i*30,null);
        }

        }
    }
}
