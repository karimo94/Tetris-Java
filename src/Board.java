import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * In the tetrisUI class, we'll use this Board object as it extends JPanel
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
 * that extends from KeyAdapter class
 * 
 * In essence, the Board class is pretty much the game controller*/

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
	private void dropPieceDown() {
		int newY = curY;
		while(newY > 0) {
			if(!tryMove(curPiece, curX, newY - 1)) {
				break;
			}
			--newY;
		}
		pieceDropped();
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
	private boolean tryMove(Shape piece, int newX, int newY) {
		// grab the piece location
		// if the piece cannot be moved left, right, or rotated
		// or if the the board is full, and the piece coord is outside board bounds
		// return false
		
		// otherwise, update the current piece
		// repaint the board
		// return true
		for(int i = 0; i < 4; i++) {
			int x = newX + piece.x(i);
			int y = newY + piece.y(i);
			if(x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
				return false;
			}
			if(shapeAt(x,y) != Tetrominoes.NoShape) {
				return false;
			}
		}
		curPiece = piece;
		curX = newX;
		curY = newY;
		repaint();
		return true;
	}
	private void removeFullLines() {
		// TODO delete one or more lines that have been completed in the board
		// go thru each row in the board
		// if there is nothing at a coordinate, the line is not full, do nothing
		
		// if the line is full, increment the amount of lines full
		// then 
		int numFullLines = 0;
		for (int i = BOARD_HEIGHT - 1; i >= 0; --i) {
			boolean lineIsFull = true;

			for (int j = 0; j < BOARD_WIDTH; ++j) {
				if (shapeAt(j, i) == Tetrominoes.NoShape) {
					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) {
				++numFullLines;
				// not sure what this does
				for (int k = i; k < BOARD_HEIGHT - 1; ++k) {
					for (int j = 0; j < BOARD_WIDTH; ++j) {
						board[k * BOARD_WIDTH + j] = shapeAt(j, k + 1);
					}
				}	
			}

			if (numFullLines > 0) {
				numLinesRemoved += numFullLines;
				statusBar.setText(String.valueOf(numLinesRemoved));
				isFallingFinished = true;
				curPiece.setShape(Tetrominoes.NoShape);
				repaint();
			}
	    }
		
	}
	private void drawSquare(Graphics g, int x, int y, Tetrominoes shape) {
		// fill in a square block in the board
		// get and set the color to use from the shape passed in
		// fill in a square by using squareWidth/Height methods to fill in a graphical block
		// use the x and y passed in to define where to draw rects
		Color color = shape.color;
		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);
		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//TODO implement
		Dimension size = getSize(); //this is obtained from inheriting JPanel
		int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();
		
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			for(int j = 0; j < BOARD_WIDTH; j++) {
				Tetrominoes shape = shapeAt(j, BOARD_HEIGHT - i - 1);
				
				if(shape != Tetrominoes.NoShape) 
					drawSquare(g, j * squareWidth(), boardTop +  i * squareHeight(), shape);
			}
		}
		
		if(curPiece.getShape() != Tetrominoes.NoShape) {
			for(int i = 0; i < 4; ++i) {
				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);
				drawSquare(g, x * squareWidth(), 
						boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
						curPiece.getShape());
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(isFallingFinished) {
			isFallingFinished = false;
			generateNewPiece();
		}
		else {
			//shift the piece one line down
			oneLineDown();
		}
	}
	public void startGame() {
		/* Begin the timer, 
		 * clear the board, 
		 * create a new piece 
		 * initialize game state*/
		if(isPaused) {
			return;
		}
		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		clearBoard();
		generateNewPiece();
		timer.start();
	}
	public void pauseGame() {
		if(!isStarted) {
			return;
		}
		isPaused = !isPaused;
		if(isPaused) {
			timer.stop();
			statusBar.setText("PAUSE");
		}
		else {
			timer.start();
			statusBar.setText(String.valueOf(numLinesRemoved));
		}
		repaint();
	}
	public class MyTetrisAdapter extends KeyAdapter implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// arrow keys to move the piece
			if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {
				return;
			}
			int keyCode = arg0.getKeyCode();
			
			if(keyCode == 'p' || keyCode == 'P') {
				pauseGame();
			}
			if(isPaused) {
				return;
			}
			
			switch(keyCode) {
			case KeyEvent.VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;
			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;
			case KeyEvent.VK_DOWN:
				tryMove(curPiece, curX, curY + 1);
				break;
			case KeyEvent.VK_UP:
				tryMove(curPiece, curX, curY);
				break;
			case KeyEvent.VK_SPACE:
				dropPieceDown();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}

	}
}
