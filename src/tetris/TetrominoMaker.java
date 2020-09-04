package se.liu.ida.leoer843.tddd30.tetris;

/**
 * This class creates a two dimensional array for each block in the game
 */
public class TetrominoMaker {
    	//Not an issue
	private SquareType[][] squares;

	public int getNumberOfTypes() {
		return SquareType.values().length - 2;
	}

	public Poly getPoly(int num) {
		switch (num) {
			case 0:
				return IPoly();
			case 1:
				return OPoly();
			case 2:
				return TPoly();
			case 3:
				return SPoly();
			case 4:
				return ZPoly();
			case 5:
				return JPoly();
			case 6:
				return LPoly();
			default:
				throw new IllegalArgumentException("Invalid index");
		}
	}

	//In this instance this type of name is easiest to understand
	public Poly IPoly() {
		SquareType.values();
		int height = 4;
		int width = 4;
		squares = new SquareType[height][width];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 1) {
					squares[i][j] = SquareType.values()[1];
				} else {
					squares[i][j] = SquareType.EMPTY;
				}
			}
		}
		Poly IShape = new Poly(squares);
		return IShape;
	}

	public Poly OPoly() {
		SquareType.values();
		int height = 3;
		int width = 3;
		squares = new SquareType[height][width];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 1) || (i == 0 && j == 0)) {
					squares[i][j] = SquareType.values()[2];
				} else {
					squares[i][j] = SquareType.EMPTY;
				}
			}
		}
		Poly OShape = new Poly(squares);
		return OShape;
	}

	public Poly TPoly() {
		SquareType.values();
		int height = 3;
		int width = 3;
		squares = new SquareType[height][width];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 1) || (i == 0 && j == 2)) {
					squares[i][j] = SquareType.values()[3];
				} else {
					squares[i][j] = SquareType.EMPTY;
				}
			}
		}
		Poly TShape = new Poly(squares);
		return TShape;
	}

	public Poly SPoly() {
		SquareType.values();
		int height = 2;
		int width = 2;
		squares = new SquareType[height][width];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				squares[i][j] = SquareType.values()[4];
			}
		}
		Poly SShape = new Poly(squares);
		return SShape;
	}

	public Poly ZPoly() {
		SquareType.values();
		int height = 3;
		int width = 3;
		squares = new SquareType[height][width];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 0) && (j == 1) || (i == 0 && j == 2)) {
					squares[i][j] = SquareType.values()[5];
				} else if ((i == 01) && (j == 0) || (i == 1 && j == 1)) {
					squares[i][j] = SquareType.values()[5];
				} else {
					squares[i][j] = SquareType.EMPTY;
				}
			}
		}
		Poly ZShape = new Poly(squares);
		return ZShape;
	}

	public Poly JPoly() {
		SquareType.values();
		int height = 3;
		int width = 3;
		squares = new SquareType[height][width];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 1) || ((i == 0) && (j == 1))) {
					squares[i][j] = SquareType.values()[6];
				} else {
					squares[i][j] = SquareType.EMPTY;
				}
			}
		}
		Poly JShape = new Poly(squares);
		return JShape;
	}

	public Poly LPoly() {
		SquareType.values();
		int height = 3;
		int width = 3;
		squares = new SquareType[height][width];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 0) && (j == 0) || (i == 0 && j == 1)) {
					squares[i][j] = SquareType.values()[7];
				} else if ((i == 1) && (j == 1) || (i == 1 && j == 2)) {
					squares[i][j] = SquareType.values()[7];
				} else {
					squares[i][j] = SquareType.EMPTY;
				}
			}
		}
		Poly ZShape = new Poly(squares);
		return ZShape;
	}
}
