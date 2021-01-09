import object.GameObject;

import java.awt.*;
import java.util.List;

public class Bullet extends Tank {

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 25;
    }

    @Override
    public void draw(Graphics g) {
        if(!alive){
            return;
        }
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);

    }

    @Override
    public boolean collision() {
        boolean isCollision = false;
        if(collisionBound()){
            alive=false;
            return true;
        }
        for (GameObject gameObjects : TankGame.gameClient.getGameObjects()) {
            if (gameObjects == this) {
                continue;
            }
            //偵測坦克碰撞
            if (gameObjects instanceof Tank) {
                if (enemy == ((Tank) gameObjects).enemy) {
                    continue;
                }
            }
            //碰撞後消失
            if (getRectangle().intersects(gameObjects.getRectangle())) {
                alive=false;
                //敵方坦克消失
                if(gameObjects instanceof Tank){
                    gameObjects.setAlive(false);
                }
                isCollision = true;

            }
        }
        return isCollision;
    }
}
