package poker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class PokerGUI {

	private Poker poker;
	private JButton play;
	private JFrame frame;
	private JLabel logo;
	private JLabel combLabelPlayer1;
	private JLabel combLabelPlayer2;
	private JLabel welcomeLabel;
	private JLabel winnerLabel;
	private JLabel card1;
	private JLabel card2;
	private JLabel card3;
	private JLabel card4;
	private JLabel card5;
	private JLabel card6;
	private JLabel card7;
	private JLabel card8;
	private JLabel card9;
	private JLabel card10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerGUI window = new PokerGUI();
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
	public PokerGUI() {
		poker = new Poker();
		initializeGui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeGui() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(50, 205, 50));
		frame.setBounds(300, 50, 928, 790);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		welcomeLabel = new JLabel("WELCOME TO POKER HANDS CARD SIMULATION");
		welcomeLabel.setForeground(SystemColor.controlHighlight);
		welcomeLabel.setFont(new Font("STIXSizeTwoSym", Font.PLAIN, 16));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(123, 6, 671, 492);
		welcomeLabel.setVisible(true);
		frame.getContentPane().add(welcomeLabel);
		
		logo = new JLabel();
		logo.setBounds(308, 400, 307, 227);
		frame.getContentPane().add(logo);
		logo.setVisible(true);
		getImg("poker-logo.png", logo);

		// Combination label player 1
		combLabelPlayer1 = new JLabel("");
		combLabelPlayer1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		combLabelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		combLabelPlayer1.setBounds(274, 120, 379, 52);
		frame.getContentPane().add(combLabelPlayer1);
		combLabelPlayer1.setVisible(false);

		// Combination label player 2
		combLabelPlayer2 = new JLabel("");
		combLabelPlayer2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		combLabelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		combLabelPlayer2.setBounds(274, 380, 379, 52);
		frame.getContentPane().add(combLabelPlayer2);
		combLabelPlayer2.setVisible(false);

		// Winner label
		winnerLabel = new JLabel("");
		winnerLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winnerLabel.setBounds(274, 700, 379, 52);
		frame.getContentPane().add(winnerLabel);
		winnerLabel.setVisible(false);

		// action button
		play = new JButton("Simulate");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processPoker();
			}
		});
		play.setBackground(UIManager.getColor("Button.light"));
		play.setBounds(405, 320, 117, 29);
		frame.getContentPane().add(play);

		// cards for player 1
		card1 = new JLabel();
		card1.setBounds(28, 162, 180, 227);
		frame.getContentPane().add(card1);

		card2 = new JLabel();
		card2.setBounds(201, 162, 180, 227);
		frame.getContentPane().add(card2);

		card3 = new JLabel();
		card3.setBounds(374, 162, 180, 227);
		frame.getContentPane().add(card3);

		card4 = new JLabel();
		card4.setBounds(547, 162, 180, 227);
		frame.getContentPane().add(card4);

		card5 = new JLabel();
		card5.setBounds(720, 162, 180, 227);
		frame.getContentPane().add(card5);

		// cards for player 1
		card6 = new JLabel();
		card6.setBounds(28, 420, 180, 227);
		frame.getContentPane().add(card6);

		card7 = new JLabel();
		card7.setBounds(201, 420, 180, 227);
		frame.getContentPane().add(card7);

		card8 = new JLabel();
		card8.setBounds(374, 420, 180, 227);
		frame.getContentPane().add(card8);

		card9 = new JLabel();
		card9.setBounds(547, 420, 180, 227);
		frame.getContentPane().add(card9);

		card10 = new JLabel();
		card10.setBounds(720, 420, 180, 227);
		frame.getContentPane().add(card10);
	}

	private void getImg(String imageName, JLabel card) {
		String resourceName = "/" + imageName;
		URL location = this.getClass().getResource(resourceName);
		Image img = new ImageIcon(location).getImage();
		Image scaledImg = img.getScaledInstance(card.getWidth(), card.getHeight(), Image.SCALE_SMOOTH);
		card.setIcon(new ImageIcon(scaledImg));
	}

	private void processPoker() {

		String[] handPlayer1 = poker.dealHand(); // random hand

		// ****Sample Hands (modify to liking)****

		// royal flush: String[] handPlayer1 = {"Q of diamonds", "A of diamonds","K of diamonds","10 of diamonds","J of diamonds"};
		// straight flush: String[] handPlayer1 = {"Q of diamonds", "J of diamonds","K of diamonds","10 of diamonds","9 of diamonds"};
		// four of a kind: String[] handPlayer1 = {"7 of diamonds", "7 of spades","7 of clubs","K of hearts","7 of diamonds"};
		// full house: String[] handPlayer1 = {"Q of diamonds", "A of hearts","Q of hearts","A of spades","A of clubs"};
		// flush: String[] handPlayer1 = {"A of diamonds", "3 of diamonds","9 of diamonds","4 of diamonds","K of diamonds"};
		// straight: String[] handPlayer1 = {"8 of spades", "J of hearts","Q of diamonds","10 of clubs","9 of diamonds"};
		// three of a kind: String[] handPlayer1 = {"A of diamonds", "7 of spades","A of clubs","K of hearts","A of diamonds"};
		// two pairs: String[] handPlayer1 = {"A of diamonds", "A of hearts","5 of spades","Q of clubs","5 of hearts"};
		// pair: String[] handPlayer1 = {"A of diamonds", "5 of hearts","9 of spades","Q of clubs","5 of hearts"};
		// high card: String[] handPlayer1 = {"A of diamonds", "5 of hearts","9 of spades","Q of clubs","3 of spades"};

		// ***************************************

		String[] handPlayer2 = poker.dealHand();
		String[] pathsPlayer1 = poker.getImgLocations(handPlayer1);
		String[] pathsPlayer2 = poker.getImgLocations(handPlayer2);
		
		getImg(pathsPlayer1[0], card1);
		getImg(pathsPlayer1[1], card2);
		getImg(pathsPlayer1[2], card3);
		getImg(pathsPlayer1[3], card4);
		getImg(pathsPlayer1[4], card5);
		getImg(pathsPlayer2[0], card6);
		getImg(pathsPlayer2[1], card7);
		getImg(pathsPlayer2[2], card8);
		getImg(pathsPlayer2[3], card9);
		getImg(pathsPlayer2[4], card10);
		
		String[] scorePlayer1 = poker.getScore(handPlayer1);
		String[] scorePlayer2 = poker.getScore(handPlayer2);
		
		welcomeLabel.setVisible(false);
		logo.setVisible(false);
		combLabelPlayer1.setVisible(true);
		combLabelPlayer2.setVisible(true);
		winnerLabel.setVisible(true);
		
		combLabelPlayer1.setText("Player 1: " + scorePlayer1[1]);
		combLabelPlayer2.setText("Player 2: " + scorePlayer2[1]);
		winnerLabel.setText(poker.determineWinner(handPlayer1, handPlayer2));
		
		play.setText("Simulate again");
		play.setBounds(405, 50, 117, 29);
	}
}
