package noteApp;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class RunApp {

	public static void main(String[] args)
    {
        NoteService noteService = new NoteService();

        JFrame startFrame = new JFrame("GoatNote");
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
        // // Create new Note
        // // Prompt for name
        // Note newNote = noteService.createNote(name, "Lorum ipsum");
        // // Open note
        // try {
        //     noteService.openNote(newNote);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        
        startFrame.setVisible(true);
    }
}
