package Model;

public class Player {
	String id;
	public String name;
	PlayingPiece piece;
	
	public Player(String id, String name, PlayingPiece piece) {
		this.id = id;
		this.name = name;
		this.piece = piece;
	}
}
