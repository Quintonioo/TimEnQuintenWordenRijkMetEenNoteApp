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
    private static ArrayList<JButton> noteButtons = new ArrayList<>();

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
    }

    private static void openNewNote(NoteService noteService, JFrame startFrame, JPanel notePanel, JLabel noteNameLabel,
        JTextArea noteTextArea, String noteName) {
        Note newNote = noteService.getNoteByNoteName(noteName);
        try {
            noteService.openNote(newNote);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        noteNameLabel.setText(newNote.getName());
        noteTextArea.setText(newNote.getText());
        loadPage(noteService, notePanel, startFrame);
    }

	public static void main(String[] args)
    {
        NoteService noteService = new NoteService();

        // Initialize the start frame (only frame)
        JFrame startFrame = new JFrame("GoatNote");
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //*-------------- NOTE EDITOR PANEL--------------*//
        // JFrame noteFrame = new JFrame("Note Editor");
        // noteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // noteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        //*-------------- HOME SCREEN PANEL --------------*//
        JPanel homeScreen = new JPanel();
        homeScreen.setLayout(new GridLayout(4, 2));
        
        JButton newNoteButton = new JButton("+");
        newNoteButton.setFont(new Font("Arial", Font.PLAIN, 160));

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

        noteButtons.add(firstNoteButton);
        noteButtons.add(secondNoteButton);
        noteButtons.add(thirdNoteButton);
        noteButtons.add(fourthNoteButton);
        noteButtons.add(fifthNoteButton);

        //*----------------------BUTTON LISTENERS------------------------- */
        // Note panel buttons 

        // Save note action
        saveNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteService.saveNote(noteTextArea.getText());
            }
        });

        // Back to home action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteService.saveNote(noteTextArea.getText());
                loadPage(noteService, homeScreen, startFrame);
            }
        });

        // Home screen buttons
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
                openNewNote(
                    noteService,
                    startFrame,
                    notePanel,
                    noteNameLabel,
                    noteTextArea,
                    firstNoteButton.getText()
                );
            }
        });

        secondNoteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewNote(
                    noteService,
                    startFrame,
                    notePanel,
                    noteNameLabel,
                    noteTextArea,
                    secondNoteButton.getText()
                );
            }
        });

        thirdNoteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewNote(
                    noteService,
                    startFrame,
                    notePanel,
                    noteNameLabel,
                    noteTextArea,
                    thirdNoteButton.getText()
                );
            }
        });

        fourthNoteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewNote(
                    noteService,
                    startFrame,
                    notePanel,
                    noteNameLabel,
                    noteTextArea,
                    fourthNoteButton.getText()
                );
            }
        });

        fifthNoteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewNote(
                    noteService,
                    startFrame,
                    notePanel,
                    noteNameLabel,
                    noteTextArea,
                    fifthNoteButton.getText()
                );
            }
        });

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
                            noteService.saveNote("");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        noteNameLabel.setText(newNote.getName());
                        noteTextArea.setText(newNote.getText());
                        loadPage(noteService, notePanel, startFrame);
                        nameFrame.dispose();
                   }
                });
            }
        });  

        // add the create new note button
        homeScreen.add(newNoteButton);
        //add all the note buttons
        for (int i = 0; i < noteButtons.size(); i++) {
            homeScreen.add(noteButtons.get(i));
        }      
        // add the left and right arrow button      
        homeScreen.add(leftArrowButton);
        homeScreen.add(rightArrowButton);
        
        // Load the home screen
        loadPage(noteService, homeScreen, startFrame);  
        startFrame.setVisible(true);
    }

    private static void loadPage(NoteService noteService, JPanel page, JFrame startFrame) {
        startFrame.getContentPane().removeAll();
        startFrame.add(page); 
        startFrame.revalidate();
        startFrame.repaint();
        setNoteButtonText(noteService, noteButtons, 0);
    }
}
