package noteApp;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class NoteService {
    private ArrayList<Note> allNotes = new ArrayList<Note>();
    // Path to saved notes
    private String folderPath = "noteApp/notes";
    private File folder = new File(folderPath);
    private ArrayList<Note> currentNotes = new ArrayList<Note>();
    private Note activeNote;

    public NoteService() {
        // Load notes from storage
        // Check if folder exists
        if (!folder.exists()) {
            // Create the folder if possible
            boolean created = folder.mkdir();

            if (created) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create folder.");
            }
        } else {
            System.out.println("Folder already exists.");
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isFile()) {
                    System.out.println("File is not recognized as file");
                    return;
                }
                String fullFileName = file.getName();
                int extenstionIndex = fullFileName.lastIndexOf(".");
                String fileName = fullFileName.substring(0, extenstionIndex);
                String fileExt = fullFileName.substring(extenstionIndex);
                if (fileExt.equals(".txt")) {
                    Note note = new Note(fileName, "");
                    allNotes.add(note);
                    try {
                        note.setText(Files.readString(file.toPath()));
                    } catch (Exception e) {
                        System.out.println("Failed to read file: " + e.getMessage());
                    }
                } else {
                    System.out.println("File is not a .txt file");
                }
            }
        }
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    public Note createNote(String name, String text) {
        Note newNote = new Note(name, text);
        allNotes.add(newNote);
        return newNote;
    }

    // Opens note, by setting it as the active note
    public void openNote(Note note) throws Exception {
        if (!allNotes.contains(note)) {
            throw new Exception("Note does not exist in the note service.");
        }
        this.activeNote = note;
    }

    public Note getActiveNote() {
        return this.activeNote;
    }

    public Note getNoteByNoteName(String name) {
        for (Note note : allNotes) {
            if (note.getName().equals(name)) {
                return note;
            }
        }
        return null;
    }

    public ArrayList<Note> setCurrentNotes(int startIndex, int count) {
        ArrayList<Note> nextNotes = new ArrayList<Note>();
        for (int i = startIndex; i < startIndex + count && i < allNotes.size(); i++) {
            if (i >= allNotes.size()) {
                break;
            }
            nextNotes.add(allNotes.get(i));
        }
        this.currentNotes = nextNotes;
        return nextNotes;
    }

    public ArrayList<Note> getCurrentNotes() {
        return currentNotes;
    }

    public void saveNote(String text) {
        System.out.println("Saving note: " + activeNote.getName());
        this.activeNote.setText(text);
        // write text to the file
        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get(folderPath, activeNote.getName() + ".txt"),
                text.getBytes()
            );
        } catch (java.io.IOException e) {
            System.out.println("Failed to save note: " + e.getMessage());
        }        
    }
    
}
