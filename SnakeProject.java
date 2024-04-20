/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.project;
import javax.swing.*;
/**
 *
 * @author Vekic
 */
public class SnakeProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int boardwith=600;
        int boardheight=boardwith;
        JFrame frame = new JFrame("Zmija");
        frame.setVisible(true);
        frame.setSize(boardwith, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //
        SnakeGame game = new SnakeGame(boardwith, boardheight);
        frame.add(game);
        frame.pack();
        game.requestFocus();
        
        
        
    }
    
}
