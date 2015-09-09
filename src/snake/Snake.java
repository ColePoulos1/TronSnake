/*
 *TRONSNAKE XXL
 */
package snake;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Snake extends JFrame implements Runnable {
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 800;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 600;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;

    final int numRows = 60;
    final int numColumns = 80;

    final int EMPTY = 0;
    final int SNAKE = 1;
    final int SNAKE2 = 2;
    int board[][];
    
//snake direction and head variables
    int currentRow;
    int currentColumn;
    int snakeDirCol;
    int snakeDirRow;
    
    int currentRow2;
    int currentColumn2;
    int snakeDirCol2;
    int snakeDirRow2;
    int snakevel;
    
//technical variables    
    boolean gameover1;
    boolean gameover2;
    boolean tiegame;
    int score1;
    int score2;
    int scoredif1;
    int scoredif2;
    boolean kill1;
    boolean kill2; 
    boolean startgame;
    boolean title;
    int timecount;
    

//snake color variables    
    Color pink = new Color(255, 0, 128);
    Color blue = new Color(0,255,255);
    Color green = new Color(0,255,0);
    Color orange = new Color(255,128,0);
    Color red = new Color(255,0,0);
    Color snakecol1 = new Color(255, 0, 128);
    Color snakecol2 = new Color(0,255,255);
    Color theme = new Color(255,255,255);
    int themechangespeed;
    boolean themechange;
    
    int currentColor1;
    int currentColor2;

    static Snake frame1;
    public static void main(String[] args) {
        frame1 = new Snake();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public Snake() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button 
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_ESCAPE == e.getKeyCode())
                {
                    reset();
                }
//player 2 moving
                if (e.VK_RIGHT == e.getKeyCode())
                {
                    if (gameover1 == true | gameover2 == true | startgame == false)
                        return;
                    snakeDirCol2 = 1;
                    snakeDirRow2 = 0;
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    if (gameover1 == true | gameover2 == true | startgame == false)
                        return;
                    snakeDirCol2 = -1;
                    snakeDirRow2 = 0;
                }
                if (e.VK_UP == e.getKeyCode())
                {
                    if (gameover1 == true | gameover2 == true | startgame == false)
                        return;
                    snakeDirRow2 = -1;
                    snakeDirCol2 = 0;
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                    if (gameover1 == true | gameover2 == true | startgame == false)
                        return;
                    snakeDirRow2 = 1;
                    snakeDirCol2 = 0;
                }
                
//player 1 moving
                if (e.VK_D == e.getKeyCode())
                {
                    if (gameover2 == true | gameover1 == true | startgame == false)
                        return;
                    snakeDirCol = 1;
                    snakeDirRow = 0;
                }
                if (e.VK_A == e.getKeyCode())
                {
                    if (gameover2 == true | gameover1 == true | startgame == false)
                        return;
                    snakeDirCol = -1;
                    snakeDirRow = 0;
                }
                if (e.VK_W == e.getKeyCode())
                {
                    if (gameover2 == true | gameover1 == true | startgame == false)
                        return;
                    snakeDirRow = -1;
                    snakeDirCol = 0;
                }
                if (e.VK_S == e.getKeyCode())
                {
                    if (gameover2 == true | gameover1 == true | startgame == false)
                        return;
                    snakeDirRow = 1;
                    snakeDirCol = 0;
                }
                
//startgame button
                 if (e.VK_SPACE == e.getKeyCode())
                {
                    startgame = true;
                    kill1 = false;
                    kill2 = false;
                    title = false;
                    
                    for (int zrow = 0;zrow < numRows;zrow++)
                    {
                         for (int zcolumn = 0;zcolumn < numColumns;zcolumn++)
                         {
                             board[zrow][zcolumn] = EMPTY;
                         }
                    }
                    snakeDirCol = 0;
                    snakeDirRow = -1;
        
                    currentRow = (numRows)/2;
                    currentColumn = (numColumns)*1/3;
        
                    snakeDirCol2 = 0;
                    snakeDirRow2 = -1;

                    currentRow2 = (numRows)/2;
                    currentColumn2 = (numColumns)*2/3;

                    board[currentRow][currentColumn] = SNAKE;
                    board[currentRow2][currentColumn2] = SNAKE2;
                }
                 
//Color buttons
                  if (e.VK_0 == e.getKeyCode())
                    currentColor2++;
                    if(currentColor2>5)
                    currentColor2= 1;
                   if (e.VK_CONTROL == e.getKeyCode())
                    currentColor2--;
                    if(currentColor2<1)
                    currentColor2= 5;     
                  if (e.VK_Q == e.getKeyCode())
                    currentColor1--;
                    if(currentColor1<1)
                    currentColor1= 5;       
                  if (e.VK_E == e.getKeyCode())
                    currentColor1++;
                    if(currentColor1>5)
                    currentColor1= 1;

                repaint();
            }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
        g.setColor(Color.black);

        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.black);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(theme);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.setColor(Color.black);
//horizontal lines
        for (int zi=1;zi<numRows;zi++)
        {
            g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,
            getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows );
        }
//vertical lines
        for (int zi=1;zi<numColumns;zi++)
        {
            g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,
            getX(0)+zi*getWidth2()/numColumns,getY(getHeight2()));
        }

//boxes
        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (board[zrow][zcolumn] == SNAKE)
                {
                    g.setColor(snakecol1);

                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                if (board[zrow][zcolumn] == SNAKE2)
                {
                    g.setColor(snakecol2);

                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
            }
        }
//gameover text
          if (gameover1)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,40));
            g.drawString("Player 2 Wins!", getX(getWidth2()/7),getYNormal(getHeight2() /2)); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,30));
            g.drawString("By "+scoredif2, getX(getWidth2()*3/7),getYNormal(getHeight2()*3/7)); 
        }
          if (gameover2)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,40));
            g.drawString("Player 1 Wins!", getX(getWidth2()/7),getYNormal(getHeight2() /2)); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,30));
            g.drawString("By "+scoredif1, getX(getWidth2()*3/7),getYNormal(getHeight2()*3/7)); 
        }
          if (tiegame)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,40));
            g.drawString("TIE GAME", getX(getWidth2() / 4),getYNormal(getHeight2() /2)); 
        }
//titles text
          if (title)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.ITALIC,50));
            g.drawString("TRONSNAKE XXL", getX(10),getYNormal(getHeight2() /2)); 

            g.setFont(new Font("Goudy Stout",Font.ITALIC,12));
            g.drawString("Player 1 colors: 1=pink 2=blue 3=green 4=orange 5=red", getX(70),getYNormal(getHeight2() /3)); 
            g.drawString("Player 2 colors: 6=pink 7=blue 8=green 9=orange 0=red", getX(70),getYNormal(getHeight2() /3 -30));
            g.drawString("first to 3, press space to begin!", getX(getWidth2() / 4),getYNormal(getHeight2() /6));
        }
//death text
          if (kill1 && kill2 && gameover1 == false && gameover2 == false && tiegame == false)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,24));
            g.drawString("Both players died! Press Space", getX(15),getYNormal(getHeight2() /2)); 
        }
          if (kill1 && kill2 ==false&& gameover1 == false && gameover2 == false && tiegame == false)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,30));
            g.drawString("Player 1 died! Press Space", getX(0),getYNormal(getHeight2() /2)); 
        }
          if (kill2 && kill1 ==false&& gameover1 == false && gameover2 == false && tiegame == false)
        {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,30));
            g.drawString("Player 2 died! Press Space", getX(0),getYNormal(getHeight2() /2));          
        }
//points text          
        g.setColor(theme); 
        g.setFont(new Font("Goudy Stout",Font.PLAIN,17));
        g.drawString("Player 1 Points: "+score1, getX(20),getYNormal(getHeight2())); 
        
        g.setColor(theme); 
        g.setFont(new Font("Goudy Stout",Font.PLAIN,17));
        g.drawString("Player 2 Points: "+score2, getX(450),getYNormal(getHeight2())); 
         
        gOld.drawImage(image, 0, 0, null);
    }


////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.025;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        
//clear board
        board = new int[numRows][numColumns];
        for (int zrow = 0;zrow < numRows;zrow++)
        {
            for (int zcolumn = 0;zcolumn < numColumns;zcolumn++)
            {
                board[zrow][zcolumn] = EMPTY;
            }
        }
        
        currentColor1=1;
        currentColor2=2;
//clear tech variables
        gameover1 = false;
        gameover2 = false;
        tiegame = false;
        startgame = false;
        kill1 = false;
        kill2 = false;
        title = true;
        score1 = 0;
        score2 = 0; 
        timecount = 0;
        snakevel = 2;
        themechangespeed = 3;
        themechange = true;
        
//snake starts going up        
        snakeDirCol = 0;
        snakeDirRow = -1;
        currentRow = (numRows)/2;
        currentColumn = (numColumns)*1/3;
        snakeDirCol2 = 0;
        snakeDirRow2 = -1;
        currentRow2 = (numRows)/2;
        currentColumn2 = (numColumns)*2/3;
        
//draw initial snakes
        board[currentRow][currentColumn] = SNAKE;
        board[currentRow2][currentColumn2] = SNAKE2;
            
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }

            reset();
        }
//theme colors
        if(themechange)
        {
            if(timecount/themechangespeed == 0)
                theme = blue;   
            else if(timecount/themechangespeed == 1)
                theme = green;   
            else if(timecount/themechangespeed == 2)
                theme = orange;   
            else if(timecount/themechangespeed == 3)
                theme = red;  
            else if(timecount/themechangespeed == 4)   
                theme = pink;  
            else if(timecount/themechangespeed == 5)
                timecount = 0;
        }
        timecount++;
//point of return
if (gameover1|gameover2|tiegame| startgame == false)
    return;
//snake velocity   

        if(currentColor1 == 1)
            snakecol1 = blue;
        if(currentColor1 == 2)
            snakecol1 = pink;
        if(currentColor1 == 3)
            snakecol1 = orange;
        if(currentColor1 == 4)
            snakecol1 = green;
        if(currentColor1 == 5)
            snakecol1 = red;
        
        if(currentColor2 == 1)
            snakecol2 = blue;
        if(currentColor2 == 2)
            snakecol2 = pink;
        if(currentColor2 == 3)
            snakecol2 = orange;
        if(currentColor2 == 4)
            snakecol2 = green;
        if(currentColor2 == 5)
            snakecol2 = red;

        if(timecount % snakevel == snakevel-1)
        {
//snake1 killed
            if(currentColumn+snakeDirCol < 0 | currentRow+snakeDirRow < 0 | currentColumn
               +snakeDirCol >= numColumns | currentRow+snakeDirRow >= numRows )
            {
                score2++;
                startgame = false;
                kill1 = true;
            }
            else if(board[currentRow+snakeDirRow][currentColumn+snakeDirCol] == SNAKE | 
               board[currentRow+snakeDirRow][currentColumn+snakeDirCol] == SNAKE2 )     
            {
                score2++;
                startgame = false;
                kill1 = true;
            }
            else
            {
            currentColumn+=snakeDirCol;
            currentRow+=snakeDirRow;
            }
//snake2 killed
            if(currentColumn2+snakeDirCol2 < 0 | currentRow2+snakeDirRow2 < 0 | currentColumn2
               +snakeDirCol2 >= numColumns | currentRow2+snakeDirRow2 >= numRows )
            {
                score1++;
                startgame = false;
                kill2 = true;
            }
            else if(board[currentRow2+snakeDirRow2][currentColumn2+snakeDirCol2] == SNAKE | 
               board[currentRow2+snakeDirRow2][currentColumn2+snakeDirCol2] == SNAKE2 )     
            {
                score1++;
                startgame = false;
                kill2 = true;
            }
            else
            {
            currentColumn2+=snakeDirCol2;
            currentRow2+=snakeDirRow2;
            }        
        }
//draw snakes      
        board[currentRow][currentColumn] = SNAKE;
        board[currentRow2][currentColumn2] = SNAKE2;
        
//scores        
        if(score1 >= 3)
            gameover2 = true;
        if(score2 >= 3)
            gameover1 = true;
        scoredif1 = score1-score2;
        scoredif2 = score2-score1;
        
//tiegame       
        if(gameover1 && gameover2)
        {
            gameover1 = false;
            gameover2 = false;
            tiegame = true;         
        }
        
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE );
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    public int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

    public int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    }
}
