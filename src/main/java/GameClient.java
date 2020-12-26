import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//遊戲客戶端(使用者設定)
public class GameClient extends JComponent {
    //螢幕大小
    private int screenWidth;
    private int screenHeight;
    private Image backGround;
    //己方坦克
    private Tank playerTank;
    //敵方坦克跟牆壁(因要調整數量 所以用list 可隨時調整)
    private ArrayList<Tank> enemyTanks = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<GameObject> objects = new ArrayList<>();

    private boolean stop;

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
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
    public void init() {
        //讀檔案
        Image[] brickImages = {Tools.getImage("brick.png")};
        Image[] iTankImages = new Image[8];
        Image[] eTankImages = new Image[8];
        String[] sub = {"U", "D", "L", "R", "LU", "RU", "LD", "RD"};
        Image[] backGround={Tools.getImage("sand.jpg")};

        for (int i = 0; i < iTankImages.length; i++) {
            iTankImages[i] = Tools.getImage("itank" + sub[i] + ".png");
            eTankImages[i] = Tools.getImage("etank" + sub[i] + ".png");
        }
        //我方坦克
        playerTank = new Tank(500, 50, Direction.DOWN, iTankImages);

        //敵方坦克
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                enemyTanks.add(new Tank(350 + j * 90, 400 + i * 90, Direction.UP, true, eTankImages));
            }
        }
        //牆壁
        walls.add(new Wall(300, 150, true, 16, brickImages));
        walls.add(new Wall(200, 250, false, 16, brickImages));
        walls.add(new Wall(850, 250, false, 16, brickImages));
        objects.add(playerTank);
        objects.addAll(walls);
        objects.addAll(enemyTanks);
    }

    @Override
    //繪製
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(backGround,0,0,null,null);
        g.fillRect(0, 0, screenWidth, screenHeight);
        //image object
        for (GameObject object : objects) {
            object.draw(g);
        }
    }

    //壓下按鍵移動
    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
        }
    }

    //放開按鍵停止
    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
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