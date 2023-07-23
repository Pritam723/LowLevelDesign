package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public int size;
	public PlayingPiece[][] mtx;
	
	public Board(int size) {
		this.size = size;
		this.mtx = new PlayingPiece[size][size];
	}
	
	public boolean addPiece(int row, int col, PlayingPiece piece) {
		if(mtx[row][col] != null) {
			return false;
		}else {
			mtx[row][col] = piece;
			return true;
		}
	}

	public List<Pair> getFreeSpaces() {
		// TODO Auto-generated method stub
		List<Pair> freeSpaces = new ArrayList<Pair>();

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(mtx[i][j] == null) {
					freeSpaces.add(new Pair(i, j));
				}
			}
		}
		return freeSpaces;
	}

	public void printBoard() {
		// TODO Auto-generated method stub
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(mtx[i][j] == null) {
					System.out.print(" ");
				}else {
					System.out.print(mtx[i][j].getSymbol());
				}
				System.out.print("|");
			}
			System.out.println();
		}
		
	}

	public boolean isCellEmpty(int row, int col) {
		if(mtx[row][col] == null) {
			return true;
		}else {
			return false;
		}
	}

	public void putSymbol(int row, int col, Player playerTurn) {
		// TODO Auto-generated method stub
		mtx[row][col] = playerTurn.piece;
	}

	public boolean isWinner(int row, int col) {
		// TODO Auto-generated method stub
		int cellCount = 0;
		
		String symbolToCheck = mtx[row][col].getSymbol();
		// Checking the Entire Row
		for(int j = 0; j < size; j++) {
			if(mtx[row][j] != null && mtx[row][j].getSymbol().equals(symbolToCheck)) {
				cellCount++;
			}
		}
		if(cellCount == size) return true;
		
		cellCount = 0;
		// Checking the Entire Column.
		for(int i = 0; i < size; i++) {
			if(mtx[i][col] != null && mtx[i][col].getSymbol().equals(symbolToCheck)) {
				cellCount++;
			}
		}
		
		if(cellCount == size) return true;
		
		cellCount = 0;		
		// Checking the Diagonals. Now we need to check 2 diagonals.
		// So, first figure out start of the 2 diagonals.
		
		int r = row;
		int c = col;
		while(r > 0 && c > 0) {
			r--;
			c--;
		}
		// So we will get start of one diagonal. Check with it first.
		
		while(r < size-1 && c < size-1) {
			if(mtx[r][c] != null && mtx[r][c].getSymbol().equals(symbolToCheck)) {
				cellCount++;
			}
			r++;
			c++;
		}
		
		if(cellCount == size) return true;
		
		cellCount = 0;	
		
		// Now check the other Diagonal.
		r = row;
		c = col;
		
		while(r > 0 && c < size-1) {
			r--;
			c++;
		}
		
		// So we will get start of other diagonal. Check with it now.
		
		while(r < size && c >= 0) {
			if(mtx[r][c] != null && !mtx[r][c].getSymbol().equals(symbolToCheck)) {
				cellCount++;
			}
			r++;
			c--;
		}
		
		if(cellCount == size) return true;
		
		return false;
	}
	
}
