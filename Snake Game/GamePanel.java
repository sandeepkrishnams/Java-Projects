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
  static final int UNIT_SIZE = 15;
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
  }

  public void draw(Graphics g) {
    if(running) {
      // for(int i=0;i <= SCREEN_HEIGHT/UNIT_SIZE ; i++){
      //   g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
      //   g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i*UNIT_SIZE);
      // }
      g.setColor(Color.RED);
      g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
  
      for (int i = 0; i < bodyparts; i++) {
        if(i == 0) {
          g.setColor(Color.GREEN);
          g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        } else {
          g.setColor(new Color(45,180,0));
          g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
      }
      g.setColor(Color.red);
      g.setFont(new Font(Font.SERIF,Font.BOLD,40));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString("score: "+appleEaten, (SCREEN_WIDTH - metrics.stringWidth("score: "+appleEaten))/2, g.getFont().getSize());
    } else {
      gameOver(g);
    }
  }

  public void newApple() {
    appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
    appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
  }

  public void move() {
    for(int i = bodyparts;i>0;i-- ) {
      x[i] = x[i - 1];
      y[i] = y[i - 1];
    }

    switch (direction) {
      case 'U':
        y[0] =y[0]-UNIT_SIZE;
        break;
      case 'D':
        y[0] =y[0]+UNIT_SIZE;
        break;
      case 'L':
        x[0] =x[0]-UNIT_SIZE;
        break;
      case 'R':
        x[0] =x[0]+UNIT_SIZE;
        break;
    
      default:
        break;
    }
  }

  public void cheakApple() {
    if((x[0] == appleX) && (y[0] == appleY)){
      bodyparts++;
      appleEaten++;
      newApple();
    }
  }

  public void cheakCollision() {
    //check head collides with body of the snake 
    for(int i = bodyparts;i>0;i-- ) {
      if((x[0] == x[i]) && (y[0] == y[i])) {
          running=false;
      }
    }
    //check head collides with left of the screen 
    if(x[0] < 0) {
      running=false;
    }
    //check head collides with right of the screen 
    if(x[0] > SCREEN_WIDTH) {
      running=false;
    }
    //check head collides with TOP of the screen 
    if(y[0] < 0) {
      running=false;
    }
    //check head collides with bottom of the screen 
    if(y[0] > SCREEN_HEIGHT) {
      running=false;
    }
    if(!running) {
      timer.stop();
    }
  }
  public void gameOver(Graphics g) {
    g.setColor(Color.red);
    g.setFont(new Font(Font.SERIF,Font.BOLD,40));
    FontMetrics metrics1 = getFontMetrics(g.getFont());
    g.drawString("score: "+appleEaten, (SCREEN_WIDTH - metrics1.stringWidth("score: "+appleEaten))/2, g.getFont().getSize());


    g.setColor(Color.red);
    g.setFont(new Font(Font.SERIF,Font.BOLD,75));
    FontMetrics metrics2 = getFontMetrics(g.getFont());
    g.drawString("GAME OVER", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if(running) {
      move();
      cheakApple();
      cheakCollision();
    }
    repaint();
  }

  public class MyKeyAdapter extends KeyAdapter {
    /* (non-Javadoc)
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {

      switch(e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
                              if(direction != 'R') {
                                direction = 'L';
                              }
                              break;

        case KeyEvent.VK_RIGHT:
                              if(direction != 'L') {
                                direction = 'R';
                              }
                              break;

        case KeyEvent.VK_UP:
                              if(direction != 'D') {
                                direction = 'U';
                              }
                              break;

        case KeyEvent.VK_DOWN:
                              if(direction != 'U') {
                                direction = 'D';
                              }
                              break;
      }


    }
  }
  
}