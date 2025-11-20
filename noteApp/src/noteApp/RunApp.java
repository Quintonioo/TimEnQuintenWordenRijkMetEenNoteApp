package noteApp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class RunApp {

	public static void main(String[] args)
    {
        JFrame startFrame = new JFrame("GoatNote");
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel homeScreen = new JPanel();
        homeScreen.setLayout(new GridLayout(4, 2));
        
        JButton newNoteButton = new JButton("+");
        newNoteButton.setFont(new Font("Arial", Font.PLAIN, 160));
        newNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        
        
        
        JButton firstNoteButton = new JButton();
        JButton secondNoteButton = new JButton();
        JButton thirdNoteButton = new JButton();
        JButton fourthNoteButton = new JButton();
        JButton fifthNoteButton = new JButton();
        JButton leftArrowButton = new JButton();
        JButton rightArrowButton = new JButton();
        
        homeScreen.add(newNoteButton);
        homeScreen.add(firstNoteButton);
        homeScreen.add(secondNoteButton);
        homeScreen.add(thirdNoteButton);
        homeScreen.add(fourthNoteButton);
        homeScreen.add(fifthNoteButton);
        homeScreen.add(leftArrowButton);
        homeScreen.add(rightArrowButton);
        
        
        startFrame.add(homeScreen);
        
        
        startFrame.setVisible(true);
    }
}
