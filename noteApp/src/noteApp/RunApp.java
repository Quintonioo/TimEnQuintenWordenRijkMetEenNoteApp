package noteApp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class RunApp {

    public static int pageNumber = 0;

    public static void changePageNumber(Boolean upOrDown) {

        if (upOrDown) {
            RunApp.pageNumber++;
        }

        else {
            RunApp.pageNumber--;
        }
    }

    public static void setNoteButtonText(NoteService noteService, ArrayList<JButton> noteButtons, int i) {
        ArrayList<Note> currentNotes = noteService.setCurrentNotes(i, i + 5);

        for (int j = 0; j <= 4; j++) {
            if (j < currentNotes.size()) {
            noteButtons.get(j).setText(currentNotes.get(j).getName());
            }
            else {
                noteButtons.get(j).setText("Empty");
            }
        }

        //for (int k = j; k <= 4; k++;) {
        //    noteButtons.get(k).setText("Empty");
        //}
    }

	public static void main(String[] args)
    {
        NoteService noteService = new NoteService();

        //*-------------- NOTE EDITOR FRAME --------------*//
        JFrame noteFrame = new JFrame("Note Editor");
        noteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        noteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BorderLayout());
        JButton saveNoteButton = new JButton("Save Note");
        JButton backButton = new JButton("Back to Home");
        JLabel noteNameLabel = new JLabel("Note Name");
        JTextArea noteTextArea = new JTextArea();
        noteTextArea.setLineWrap(true);

        // Show note name at the top and text area in the center
        notePanel.add(noteNameLabel, BorderLayout.NORTH);
        notePanel.add(noteTextArea, BorderLayout.CENTER);

        // Show the buttons at the bottom
        JPanel noteButtonPanel = new JPanel();
        noteButtonPanel.setLayout(new FlowLayout());
        noteButtonPanel.add(saveNoteButton);
        noteButtonPanel.add(backButton);
        notePanel.add(noteButtonPanel, BorderLayout.SOUTH);
        noteFrame.add(notePanel);

        // Save note action
        saveNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedText = noteTextArea.getText();
                noteService.saveNote(updatedText);
            }
        });

        // Back to home action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteFrame.dispose();
                // TODO: Refresh home screen notes and show home page
            }
        });

        //*-------------- HOME SCREEN FRAME --------------*//
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
                JFrame nameFrame = new JFrame("Note Name");
                JPanel namePanel = new JPanel();
                namePanel.setLayout(new GridLayout(3,1));

                JLabel nameExplanation = new JLabel("Enter name of the note:");
                namePanel.add(nameExplanation);

                JTextField nameText = new JTextField(16);
                namePanel.add(nameText);

                JButton nameSubmit = new JButton("Submit");
                namePanel.add(nameSubmit);

                nameFrame.add(namePanel);
                nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nameFrame.setSize(300, 100);
                nameFrame.setVisible(true);

                nameSubmit.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                        String noteName = nameText.getText();
                        Note newNote = noteService.createNote(noteName, "");
                        try {
                            noteService.openNote(newNote);
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        noteNameLabel.setText(newNote.getName());
                        noteTextArea.setText(newNote.getText());
                        nameFrame.dispose();
                        startFrame.dispose();
                        noteFrame.setVisible(true);
                   }
                });
            }
        });  

        JButton firstNoteButton = new JButton("Empty");
        firstNoteButton.setFont(new Font("Arial", Font.PLAIN, 100));
        JButton secondNoteButton = new JButton("Empty");
        secondNoteButton.setFont(new Font("Arial", Font.PLAIN, 100));
        JButton thirdNoteButton = new JButton("Empty");
        thirdNoteButton.setFont(new Font("Arial", Font.PLAIN, 100));
        JButton fourthNoteButton = new JButton("Empty");
        fourthNoteButton.setFont(new Font("Arial", Font.PLAIN, 100));
        JButton fifthNoteButton = new JButton("Empty");

        fifthNoteButton.setFont(new Font("Arial", Font.PLAIN, 100));       
        JButton leftArrowButton = new JButton("<");
        leftArrowButton.setFont(new Font("Arial", Font.PLAIN, 100));
        JButton rightArrowButton = new JButton(">");
        rightArrowButton.setFont(new Font("Arial", Font.PLAIN, 100));

        ArrayList<JButton> noteButtons = new ArrayList<>();
        noteButtons.add(firstNoteButton);
        noteButtons.add(secondNoteButton);
        noteButtons.add(thirdNoteButton);
        noteButtons.add(fourthNoteButton);
        noteButtons.add(fifthNoteButton);


        leftArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        rightArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePageNumber(true);
                setNoteButtonText(noteService, noteButtons, (RunApp.pageNumber)*5);
            }
        });

        firstNoteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Note newNote = noteService.getNoteByNoteName(firstNoteButton.getText());
                try {
                    noteService.openNote(newNote);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                noteNameLabel.setText(newNote.getName());
                noteTextArea.setText(newNote.getText());
                startFrame.dispose();
                noteFrame.setVisible(true);
            }
        });



        // noteService.createNote("Naam1", "");
        // noteService.createNote("Naam2", "");
        // noteService.createNote("Naam3", "");
        // noteService.createNote("Naam4", "");
        // noteService.createNote("Naam5", "");
        // noteService.createNote("Naam6", "");
        // noteService.createNote("Naam7", "");
        // noteService.createNote("Naam8", "");

        setNoteButtonText(noteService, noteButtons, 0);
        
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
