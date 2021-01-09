import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//遊戲啟動
public class TankGame {
    public static GameClient gameClient;

    public static GameClient getGameClient() {
        return gameClient;
    }

    public static void main(String[] args) {
        gameClient = new GameClient(1024, 768);
        //JFrame為啟動用類別
        JFrame frame = new JFrame();
        frame.setTitle("坦克大戰!");
        frame.setResizable(false);
        frame.add(gameClient);
        frame.pack();
        //置中顯示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameClient.repaint();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });
    }
}
