import java.util.ArrayList;

public class Figure {
	ArrayList<Block> blocks;
	int rotationPhase;
	boolean rotationSet;
	public Figure() {}
	public Figure(int oneX, int oneY, int twoX, int twoY,
			int threeX, int threeY, int fourX, int fourY, int textureImg) {
	
		blocks = new ArrayList<>();
		blocks.add(new Block(oneX, oneY, textureImg));
		blocks.add(new Block(twoX, twoY, textureImg));
		blocks.add(new Block(threeX, threeY, textureImg));
		blocks.add(new Block(fourX, fourY, textureImg));
		
		rotationPhase = 0;
		rotationSet = false;
	}
}
