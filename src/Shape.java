import java.util.Random;
/*
 * Create a Shape object having Tetrominoes as a field
 * and coords to define th position on the screen of a current shape
 * We also define methods to rotate a shape no the left/right in space
 * Also important: methods needed to change coords when a shape scrolls down*/
public class Shape {
	private Tetrominoes pieceShape;
	private int[][] coords;
	//create a new shape, set the coords
	public Shape() {
		coords = new int[4][2];
		setShape(Tetrominoes.NoShape);
	}
	//set the shape points from the enum passed in
	public void setShape(Tetrominoes shape) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 2; ++j) {
				coords[i][j] = shape.coords[i][j];
			}
		}
		pieceShape = shape;
	}
	//used for shape rotations
	private void setX(int index, int x) {
		coords[index][0] = x;
	}
	private void setY(int index, int y) {
		coords[index][1] = y;
	}
	public int x(int index) {
		return coords[index][0];
	}
	public int y(int index) {
		return coords[index][1];
	}
	//return the current shape
	public Tetrominoes getShape() {
		return pieceShape;
	}
	//generate the next random shape
	public void setRandomShape() {
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;
		Tetrominoes[] values = Tetrominoes.values();
		setShape(values[x]);
	}
	public int minX() {
		int m = coords[0][0];
		for(int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][0]);
		}
		return m;
	}
	public int minY() {
		int m = coords[0][0];
		for(int i = 0; i < 4; i++) {
			m = Math.min(m,  coords[i][1]);
		}
		return m;
	}
	public Shape rotateLeft() {
		//square shape does rotates the same way, nothing to do
		if(pieceShape == Tetrominoes.SquareShape) {
			return this;
		}
		Shape result = new Shape();
		result.pieceShape = pieceShape;
		for(int i = 0; i < 4; i++) {
			result.setX(i, y(i));
			result.setY(i, -x(i));
		}
		return result;
	}
	public Shape rotateRight() {
		//square shape does rotates the same way, nothing to do
		if(pieceShape == Tetrominoes.SquareShape) {
			return this;
		}
		Shape result = new Shape();
		result.pieceShape = pieceShape;
		for(int i = 0; i < 4; i++) {
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}
		return result;
	}
}
