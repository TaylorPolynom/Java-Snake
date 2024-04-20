/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.project;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javafx.scene.layout.BorderWidths;
import javax.swing.*;
import javax.swing.Timer;
/**
 *
 * @author Vekic
 */
public class SnakeGame extends JPanel implements ActionListener,KeyListener{

    private void placefood() {
        food.x=random.nextInt(boardwidth/tileSize);
        food.y=random.nextInt(boardheight/tileSize);
    }
    
    public boolean collision(Tile tile1,Tile tile2){
     return tile1.x==tile2.x && tile1.y==tile2.y;
        
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver==true){
          gameloop.stop();
          //Nastavi od 37:19!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }

    private void move() {
        //eat food
        if(collision(snakehead, food)){
         count++;   
        snakeBody.add(new Tile(food.x, food.y));
        placefood();
        }
        //SnakeBody
        for(int i=snakeBody.size()-1;i>=0;i--){
          Tile snakePart=snakeBody.get(i);
          //continue 34:00 CONTINUE!!!!!!!!
          if(i==0){
          snakePart.x=snakehead.x;
          snakePart.y=snakehead.y;
          
          }
          else{
          Tile snakePrev=snakeBody.get(i-1);
          snakePart.x=snakePrev.x;
          snakePart.y=snakePrev.y;
          
          }
        }
        
        //SnakeHead
        snakehead.x+=velocityX;
        snakehead.y+=velocityY;
        
        //gameOver conditions
        for(int i=0;i<snakeBody.size();i++){
          Tile snakePart = snakeBody.get(i);
          //collision with snake head
          if(collision(snakehead, snakePart)){
          
          gameOver=true;
          }
        
        }
        if(snakehead.x*tileSize<0 || snakehead.x*tileSize>boardwidth ||
                snakehead.y*tileSize<0 || snakehead.y*tileSize>boardheight){
        gameOver=true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1){
        velocityX=0;
        velocityY=-1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityY!=-1){
        velocityX=0;
        velocityY=1;
        }
         else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityX!=1){
        velocityX=-1;
        velocityY=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX!=-1){
        velocityX=1;
        velocityY=0;
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    
    private class Tile{
    int x;
    int y;
    Tile(int x,int y){
    this.x = x;
    this.y = y;
    }
    
    }
    int boardwidth;
    int boardheight;
    int tileSize=25;
    
    //Snake
    Tile snakehead;
    ArrayList<Tile>snakeBody;
    //Food
    Tile food;
    Random random;
    
    //Game logic
    Timer gameloop;
    int velocityX;
    int velocityY;
    boolean gameOver=false;
    int count=0;
    
    SnakeGame(int boardwidth,int boardheight){
        
    this.boardwidth=boardwidth;
    this.boardheight=boardheight;
        setPreferredSize(new Dimension(this.boardwidth, this.boardheight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        snakehead=new Tile(5,5);
        snakeBody = new ArrayList<>();
        food = new Tile(10,10);
        random = new Random();
        placefood();
        velocityX=0;
        velocityY=0;
        gameloop = new Timer(100,this);
        gameloop.start();
    }
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
    
    
    }
    private void draw(Graphics g) {
        //Grid
        for(int i=0;i<boardwidth/tileSize;i++){
          g.drawLine(i*tileSize,0,i*tileSize,boardwidth);
           g.drawLine(0,i*tileSize,boardwidth,i*tileSize);
        }
        //Food
        g.setColor(Color.RED);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
         //Snake head
         
         g.setColor(Color.GREEN);
         g.fillRect(snakehead.x*tileSize, snakehead.y*tileSize, tileSize, tileSize);
         //snake body
         for(int i=0;i<snakeBody.size();i++){
          Tile snakePart=snakeBody.get(i);
          g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
          
         }
         //result
         String result="Result: "+count;
         g.drawString(result,10, 10);
         if(gameOver==true){
         
         g.drawString("GameOVER", tileSize-16, tileSize);
         }
         
    }
}
