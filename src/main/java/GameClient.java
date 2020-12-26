import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import object.Direction;
import object.Tank;
import object.Wall;

//遊戲客戶端(使用者設定)
public class GameClient extends JComponent {
    //螢幕大小
    private int screenWidth;
    private int screenHeight;
    //己方坦克
    private Tank playerTank;
    //敵方坦克跟牆壁(因要調整數量 所以用list 可隨時調整)
    private ArrayList<Tank> enemyTanks=new ArrayList<>();
    private ArrayList<Wall> walls=new ArrayList<Wall>();
    //
    private boolean stop;

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stop){
                    //每50毫秒更新一次遊戲畫面
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
    //初始位置
    public void init(){
        //我方坦克
        playerTank=new Tank(500,50, Direction.DOWN);
        //敵方坦克
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                enemyTanks.add(new Tank(350+j*90,400+i*90, Direction.UP,true));
            }
        }
        //牆壁
        walls.add(new Wall(270,150, true,15));
        walls.add(new Wall(200,200, false,16));
        walls.add(new Wall(750,200, false,16));
    }

    @Override
    //繪製
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,screenWidth,screenHeight);
        //image object
        playerTank.draw(g);
        for(Tank tank:enemyTanks){
            tank.draw(g);
        }
        for(Wall wall:walls){
            wall.draw(g);
        }
    }
    //壓下按鍵移動
    public void keyPressed(KeyEvent e){
        boolean[] dirs=playerTank.getDirs();
        switch (e.getKeyCode()){

            case KeyEvent.VK_UP:
               dirs[0]=true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=true;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2]=true;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3]=true;
                break;
        }
    }
    //放開按鍵停止
    public void keyReleased(KeyEvent e) {
        boolean[] dirs=playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }
    }
}