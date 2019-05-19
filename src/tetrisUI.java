import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class tetrisUI {

	private JFrame frame;
	private JTextField scoreTextField;
	private JTextField levelTextField;
	private ArrayList<JPanel[]> grid;
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
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(5,  5, 217,  462);
		gamePanel.setPreferredSize(new Dimension(200, 500));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.getContentPane().add(gamePanel);
		
		initGrid();
		
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
	}

	private void initGrid() {
		grid = new ArrayList<JPanel[]>();
		for(int i = 0; i < 20; i++) {
			grid.add(new JPanel[10]);
			for(int j = 0; j < 10; j++) {
				grid.get(i)[j] = new JPanel();
			}
		}
	}
}
