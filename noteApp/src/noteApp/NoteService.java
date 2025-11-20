package noteApp;

import java.util.ArrayList;

public class NoteService {
    private ArrayList<Note> allNotes = new ArrayList<Note>();
    private ArrayList<Note> currentNotes = new ArrayList<Note>();
    private Note activeNote;

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
    }
    
}
