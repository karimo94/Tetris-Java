import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * In he tetrisUI class, we'll use this Board object as it extends JPanel
 * To display the tetrominoes, we need a board, define a width and height
 * To animate the game, we use javax.swing.Timer, which is in charge to call 
 * at a define frequency the method 'acitonPerformed' of an implementation
 * of interface ActionListener.
 * 
 * Our board class must implement ActionListener interface from Swing API
 * 
 * Rendering the shapes is done using the paint method
 * 
 * To manager interactions with players, we need to add our own key listener
 * that extends from KeyAdapter class*/

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener{

	//rows and cols
	private static final int BOARD_WIDTH = 10;
	private static final int BOARD_HEIGHT = 22;
	//main timer
	private Timer timer;
	private boolean isFallingFinished = false;
	//is the game started
	private boolean isStarted = false;
	//is the game paused
	private boolean isPaused = false;
	//number of lines removed
	private int numLinesRemoved = 0;
	//current shape location
	private int curX = 0;
	private int curY = 0;
	private JTextField statusBar;
	//store our current piece
	private Shape curPiece;
	//board will take the dimensions above
	private Tetrominoes[] board;
	
	public Board(tetrisUI parent) {
		setFocusable(true);
		//start the game with a new piece
		curPiece = new Shape();
		//timer for lines down
		timer = new Timer(400, this); //400ms delay, context
		
		statusBar = parent.scoreTextField;
		board = new Tetrominoes[BOARD_WIDTH * BOARD_HEIGHT];
		clearBoard();
		addKeyListener(new MyTetrisAdapter());
	}
	public int squareWidth() {
		return (int) getSize().getWidth() / BOARD_WIDTH;
	}
	public int squareHeight() {
		return (int) getSize().getHeight() / BOARD_HEIGHT;
	}
	public Tetrominoes shapeAt(int x, int y) {
		return board[y * BOARD_WIDTH + x];
	}
	private void clearBoard() {
		for(int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
			board[i] = Tetrominoes.NoShape;
		}
	}
	//drop a piece
	private void pieceDropped() {
		for(int i = 0; i < 4; i++) {
			int x = curX + curPiece.x(i);
			int y = curY + curPiece.y(i);
			board[y * BOARD_WIDTH + x] = curPiece.getShape();
		}
		removeFullLines();
		if(!isFallingFinished) {
			generateNewPiece();
		}
	}
	
	private void generateNewPiece() {
		// generate a new random piece in the middle of the board
		curPiece.setRandomShape();
		curX = BOARD_WIDTH / 2 + 1;
		curY = BOARD_HEIGHT / 2 + 1;
		
		if(!tryMove(curPiece, curX, curY - 1)) {
			//if the piece created overflows, then game over
			curPiece.setShape(Tetrominoes.NoShape);
			timer.stop();
			isStarted = false;
			statusBar.setText("GAME OVER");
		}
	}
	
	private void oneLineDown() {
		if(!tryMove(curPiece, curX, curY - 1)) {
			pieceDropped();
		}
	}
	private boolean tryMove(Shape piece, int x, int y) {
		// TODO figure out what this does
		return false;
	}
	private void removeFullLines() {
		// TODO delete one or more lines
		
	}
	private void drawSquare(Graphics g, int x, int y, Tetrominoes shape) {
		// TODO you are here
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(isFallingFinished) {
			isFallingFinished = false;
			generateNewPiece();
		}
		else {
			oneLineDown();
		}
	}

}
