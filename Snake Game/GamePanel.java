import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements ActionListener {

  static final int SCREEN_WIDTH = 600;
  static final int SCREEN_HEIGHT = 600;
  static final int UNIT_SIZE = 25;
  static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
  static final int DELAY = 75;
  final int x[] = new int[GAME_UNITS] ;
  final int y[] = new int[GAME_UNITS] ;
  int bodyparts = 6;
  int appleEaten;
  int appleX;
  int appleY;
  char direction = 'R';
  boolean running = false;
  Timer timer;
  Random random;

  GamePanel() {
    random = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    startGame();
  }
  //To start a Snake Game
  public void startGame() {
    newApple();
    running= true;
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
    // 15 : 16
  }

  public void draw(Graphics g) {

  }

  public void newApple() {

  }

  public void move() {

  }

  public void cheakApple() {

  }

  public void cheakCollision() {

  }
  public void gameOver() {

  }
  @Override
  public void actionPerformed(ActionEvent e) {
    
  }

  public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {

    }
  }
  
}