import java.awt.Color;
/*
 * Design tetrominoes, using a Java enum with 2 fields, 
 * one for coords (using 2D int array of 4r * 2c since each shape fits into this dimension), 
 * and one for color
 * The constructor takes in coords and color*/
enum Tetrominoes {
	
	NoShape(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, new Color(0, 0, 0)),
	ZShape(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, new Color(204, 102, 102)),
	SShape(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, new Color(102, 204, 102)),
	LineShape(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, new Color(102, 102, 204)),
	TShape(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, new Color(204, 204, 102)),
	SquareShape(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, new Color(204, 102, 204)),
	LShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, new Color(102, 204, 204)),
	MirroredLShape(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, new Color(218, 170, 0));
 
    public int[][] coords;
    public Color color;
 
    private Tetrominoes(int[][] coords, Color c) {
        this.coords = coords;
        color = c;
    }
}

