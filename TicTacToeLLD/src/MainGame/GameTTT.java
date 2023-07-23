package MainGame;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Model.Player;
import Model.PlayingPieceO;
import Model.PlayingPieceX;
import Model.Board;
import Model.Pair;

public class GameTTT {
	Deque<Player> players;
	Board gameBoard;
	
	public GameTTT() {
		initializeGame();
	}
	
	private void initializeGame() {
		players = new LinkedList<>();
		PlayingPieceX crossPiece = new PlayingPieceX();
		PlayingPieceO noughtsPiece = new PlayingPieceO();
		
		Player player1 = new Player("1","Ram",crossPiece);
		Player player2 = new Player("2","Laxman",noughtsPiece);
		
		players.add(player1);
		players.add(player2);
		
		gameBoard = new Board(3);

	}
	
	public String startGame() {
		boolean noWinner = true;
		while(noWinner) {
			// So, one scenario is, if there is no free space left, We break 7 return tie.
			List<Pair> freeSpaces = gameBoard.getFreeSpaces();
			if(freeSpaces.isEmpty()) {
				break;
			}
			
			Player playerTurn = players.getFirst();
			gameBoard.printBoard();
			
			System.out.println("It is " + playerTurn.name + "'s move");
			
			System.out.println("Select Row: ");
			Scanner buffer = new Scanner(System.in);
			int row = buffer.nextInt();
			
			System.out.println("Select Col: ");
			int col = buffer.nextInt();
			
			boolean isCellEmpty = gameBoard.isCellEmpty(row, col);
			if(!isCellEmpty) {
				System.out.println("Cell is not empty, Enter Again");
				continue;
			}
			
			// Otherwise put the Symbol in the Cell.
			gameBoard.putSymbol(row, col, playerTurn);
			
			// Now once the player puts the Symbol, he might become winner.
			// So, check that. We should start checking from current Symbol.
			
			if(gameBoard.isWinner(row, col)) {
				noWinner = false;
				return playerTurn.name;
			}
			
			// Now it will be next player's turn. So, remove this player and add it to last.
			playerTurn = players.removeFirst();
			
			players.add(playerTurn);
			
		}
		
		return("It's a Tie");
		
	}
}
