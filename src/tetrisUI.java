import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class tetrisUI {

	private JFrame frame;
	public JTextField scoreTextField;
	public JTextField levelTextField;
	private Board gamePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tetrisUI window = new tetrisUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tetrisUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Tetris");
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/*our current game jpanel, should be replaced by Board*/
		gamePanel = new Board(this);
		gamePanel.setBounds(5,  5, 217,  462);
		gamePanel.setPreferredSize(new Dimension(200, 500));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.getContentPane().add(gamePanel);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setBounds(285, 32, 46, 14);
		frame.getContentPane().add(lblScore);
		
		scoreTextField = new JTextField();
		scoreTextField.setEditable(false);
		scoreTextField.setBounds(285, 51, 86, 20);
		frame.getContentPane().add(scoreTextField);
		scoreTextField.setColumns(10);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(285, 102, 46, 14);
		frame.getContentPane().add(lblLevel);
		
		levelTextField = new JTextField();
		levelTextField.setEditable(false);
		levelTextField.setBounds(285, 127, 86, 20);
		frame.getContentPane().add(levelTextField);
		levelTextField.setColumns(10);
		
		JLabel lblUseArrowKeys = new JLabel("Use arrow keys and space bar");
		lblUseArrowKeys.setBounds(240, 300, 200, 14);
		frame.getContentPane().add(lblUseArrowKeys);
		
		JButton btnNewGame = new JButton("New");
		btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gamePanel.startGame();
			}
		});
		btnNewGame.setBounds(285, 200, 89, 23);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnContinue = new JButton(">");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gamePanel.startGame();
			}
		});
		btnContinue.setBounds(285, 234, 41, 23);
		frame.getContentPane().add(btnContinue);
		
		JButton btnPause = new JButton("||");
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gamePanel.pauseGame();
			}
		});
		btnPause.setBounds(333, 234, 41, 23);
		frame.getContentPane().add(btnPause);
	}
}
