import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import object.Direction;
import object.Tank;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;
    private boolean stop;

    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stop){
                    //更新遊戲畫面
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

    public void init(){
        playerTank=new Tank(400,300, Direction.DOWN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,screenWidth,screenHeight);
        //image object
        g.drawImage(playerTank.getImage(),
                playerTank.getX(), playerTank.getY(), null);
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){

            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                //playerTank.setY(playerTank.getY()-playerTank.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                //playerTank.setY(playerTank.getY()+playerTank.getSpeed());
                break;

            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                //playerTank.setX(playerTank.getX()-playerTank.getSpeed());
                break;

            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                //playerTank.setX(playerTank.getX()+playerTank.getSpeed());
                break;
        }

        playerTank.move();


        //repaint();

    }
}