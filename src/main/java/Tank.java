import object.GameObject;

import javax.swing.*;
import java.awt.*;

//坦克移動
public class Tank extends GameObject {
    protected int speed;
    protected Direction direction;
    protected boolean enemy;
    private boolean[] dirs = new boolean[4];

    public boolean[] getDirs() {
        return dirs;
    }

    private void determineDirection() {
        if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.UP;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.DOWN;
        } else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.LEFT;
        } else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.RIGHT;
        } else if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.LEFT_UP;
        } else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.RIGHT_UP;
        } else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.LEFT_DOWN;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.RIGHT_DOWN;
        }

    }

    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        speed = 15;
        this.enemy = enemy;
    }

    public int getSpeed() {
        return speed;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
    public void fire(){
        Bullet bullet=new Bullet(x+width/2-GameClient.bulletImage[0].getWidth(null)/2,
                y+height/2-GameClient.bulletImage[0].getHeight(null)/2,
                direction,enemy,GameClient.bulletImage);
        TankGame.getGameClient().addGameObject(bullet);
    }

    public Image getImage() {
        String name = enemy ? "etank" : "itank";
        if (direction == Direction.UP) {
            return new ImageIcon("assets/images/" + name + "U.png").getImage();
        }

        if (direction == Direction.DOWN) {
            return new ImageIcon("assets/images/" + name + "D.png").getImage();
        }

        if (direction == Direction.LEFT) {
            return new ImageIcon("assets/images/" + name + "L.png").getImage();
        }

        if (direction == Direction.RIGHT) {
            return new ImageIcon("assets/images/" + name + "R.png").getImage();
        }


        if (direction == Direction.LEFT_DOWN) {
            return new ImageIcon("assets/images/" + name + "LD.png").getImage();
        }

        if (direction == Direction.RIGHT_DOWN) {
            return new ImageIcon("assets/images/" + name + "RD.png").getImage();
        }

        if (direction == Direction.LEFT_UP) {
            return new ImageIcon("assets/images/" + name + "LU.png").getImage();
        }

        if (direction == Direction.RIGHT_UP) {
            return new ImageIcon("assets/images/" + name + "RU.png").getImage();
        }


        return null;
    }

    public void move() {
        oldX=x;
        oldY=y;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case LEFT_UP:
                x -= speed;
                y -= speed;
                break;
            case LEFT_DOWN:
                x -= speed;
                y += speed;
                break;
            case RIGHT_DOWN:
                x += speed;
                y += speed;
                break;
            case RIGHT_UP:
                x += speed;
                y -= speed;
                break;
        }

    }
    public boolean collisionBound(){
        boolean isCollision=false;
        if(x<0){
            x=0;
            isCollision=true;
        }else if(x>TankGame.gameClient.getScreenWidth()-width){
            x=TankGame.gameClient.getScreenWidth()-width;
            isCollision=true;
        }
        if(y<0){
            y=0;
            isCollision=true;
        }else if(y>TankGame.gameClient.getScreenHeight()-height){
            y=TankGame.gameClient.getScreenHeight()-height;
            isCollision=true;
        }return isCollision;
    }
    public boolean collisionObjects(){
        boolean isCollision=false;
        for(GameObject gameObject:TankGame.getGameClient().getGameObjects()){
            if(gameObject==this){
                continue;
            }
            if(gameObject!=this && getRectangle().intersects(gameObject.getRectangle())){
                System.out.println("hit!");
                x=oldX;
                y=oldY;
                isCollision=true;
            }
        }return isCollision;
    }
    public boolean collision(){
        boolean isCollision=false;
        isCollision=collisionBound();
        if(!isCollision){
            isCollision=collisionObjects();
        }
        return isCollision;
    }

    public void draw(Graphics g) {
        if(!alive){
            return;
        }
        if (!isStop()) {
            determineDirection();
            move();
            collision();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

}