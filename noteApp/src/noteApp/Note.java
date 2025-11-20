package noteApp;
import java.util.*;

public class Note {
	
	private String name;
	private String text;
	private List<Image> images = new ArrayList<>();
	private List<Sketch> sketches = new ArrayList<>();

	public Note(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	// TODO: Change to editName 
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void addImage(Image image) {
		this.images.add(image);
	}
	
	public List<Image> getImages() {
		return this.images;
	}
	
	public void addSketch(Sketch sketch) {
		this.sketches.add(sketch);
	}
	
	public List<Sketch> getSketches() {
		return sketches;
	}
}
