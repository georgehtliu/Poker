package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Poker {

	private final int HAND_LENGTH = 5;
	private List<String> deck;

	public Poker() {
		deck = createDeck();
	}

	public String[] dealHand() { // n represents nth player...
		Collections.shuffle(deck);
		String[] hand = new String[HAND_LENGTH];
		for (int i = 0; i < HAND_LENGTH; i++) {
			hand[i] = deal(deck, i);
		}
		return hand;
	}

	public String[] getImgLocations(String[] hand) { // all links are in the form of "face + first letter of suit + .png"
		// ex. 10 of spades -> 10S.png
		Stream<String> myStream = Arrays.stream(hand).map(s -> {
			String[] tokens = s.split(" "); // creates array of elements that were originally separated by a space
			return tokens[0] + tokens[2].toUpperCase().charAt(0) + ".png";
		});
		return myStream.toArray(String[]::new);
	}

	public String[] getScore(String[] hand) {
		int score = 0;
		String msg = "";
		String[] message = new String[2];
		if (hasRoyalFlush(hand)) {
			score = 110;
			msg = "Royal flush!";
		} else if (hasStraightFlush(hand)) {
			score = 100;
			msg = "Straight flush!";
		} else if (hasFourOfAKind(hand)) {
			score = 90;
			msg = "Four of a kind!";
		} else if (hasFullHouse(hand)) {
			score = 80;
			msg = "Full house!";
		} else if (hasFlush(hand)) {
			score = 70;
			msg = "Flush!";
		} else if (hasStraight(hand)) {
			score = 60;
			msg = "Straight!";
		} else if (hasThreeOfAKind(hand)) {
			score = 50;
			msg = "Three of a kind!";
		} else if (hasTwoPair(hand)) {
			score = 40;
			msg = "Two pairs!";
		} else if (hasPair(hand)) {
			score = 30;
			msg = "Pair!";
		} else {
			score = returnHighCard(hand);
			msg = "High card!";
		}
		message[0] = Integer.toString(score);
		message[1] = msg;
		return message;
	}

	private List<String> createDeck() { // a
		String[] faces = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		String[] suits = { "Diamonds", "Hearts", "Spades", "Clubs" };
		List<String> deck = new ArrayList<>();
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				deck.add(faces[i] + " of " + suits[j]);
			}
		}
		return deck;
	}

	private String deal(List<String> deck, int i) { // c
		return deck.get(i);
	}

	private String getSuit(String card) {
		return card.substring(card.lastIndexOf(" ") + 1); // returns last word which is suit
	}

	private String getFace(String card) { // gets first word which is the face
		String word[] = card.split(" ", 2);
		String face = word[0];
		return face;
	}

	private boolean isSameSuit(String[] hand) {
		String suit = getSuit(hand[0]);
		for (int i = 1; i < hand.length; i++) {
			if (!getSuit(hand[i]).equals(suit)) { // searching for one card whose suit isn't the same as the first card's
				return false;
			}
		}
		return true; // means all cards have the same suit as the first card
	}

	private boolean hasDuplicate(String[] hand, int numOfSameFace) { // checks if there exist n cards with the same face
		boolean result = false;
		for (int i = 0; i < hand.length; i++) {
			int count = 0;
			String origCard = getFace(hand[i]);
			for (int j = 0; j < hand.length; j++) {
				if (getFace(hand[j]).equals(origCard)) {
					count++;
				}
			}
			if (count == numOfSameFace) {
				result = true;
				break;
			}
		}

		return result;
	}

	private int[] sortByRank(String[] hand) { // replaces the letter ranks with integer values
		int[] newFaces = new int[HAND_LENGTH];
		for (int i = 0; i < HAND_LENGTH; i++) {
			if (getFace(hand[i]).equals("K")) {
				newFaces[i] = 13;
			} else if (getFace(hand[i]).equals("Q")) {
				newFaces[i] = 12;
			} else if (getFace(hand[i]).equals("J")) {
				newFaces[i] = 11;
			} else if (getFace(hand[i]).equals("A")) {
				newFaces[i] = 14;
			} else {
				newFaces[i] = Integer.parseInt(getFace(hand[i]));
			}
		}
		Arrays.sort(newFaces); // puts elements in order
		return newFaces;
	}

	private boolean areConsecutives(int[] hand) {
		boolean isConsecutive = true;
		Arrays.sort(hand);
		for (int i = 0; i < hand.length; i++) {
			if (i + hand[0] != hand[i]) {
				isConsecutive = false;
				break;
			}
		}
		return isConsecutive;
	}

	private boolean hasRoyalFlush(String[] hand) {
		int[] intHand = sortByRank(hand);
		return areConsecutives(intHand) && intHand[0] == 10 && isSameSuit(hand);
	}

	private boolean hasStraightFlush(String[] hand) {
		int[] intHand = sortByRank(hand);
		return areConsecutives(intHand) && isSameSuit(hand);
	}

	private boolean hasFourOfAKind(String[] hand) {
		return hasDuplicate(hand, 4);
	}

	private boolean hasFullHouse(String[] hand) {
		int[] sorted = sortByRank(hand);
		int[] indices = new int[15];
		for (int i = 0; i < sorted.length; i++) {
			int card = sorted[i];
			indices[card] = ++indices[card];
		}

		int pairCount = 0;
		int tripCount = 0;
		for (int i = 0; i < indices.length; i++) {
			if (indices[i] == 2) {
				++pairCount;
			} else if (indices[i] == 3) {
				++tripCount;
			}
		}

		return pairCount == 1 && tripCount == 1;
	}

	private boolean hasFlush(String[] hand) {
		return isSameSuit(hand);
	}

	private boolean hasStraight(String[] hand) {
		return areConsecutives(sortByRank(hand));
	}

	private boolean hasThreeOfAKind(String[] hand) {
		return hasDuplicate(hand, 3);
	}

	public boolean hasTwoPair(String[] hand) {
		int[] sorted = sortByRank(hand);
		int[] indices = new int[15];
		for (int i = 0; i < sorted.length; i++) {
			int card = sorted[i];
			indices[card] = ++indices[card];
		}

		int pairCount = 0;
		for (int i = 0; i < indices.length; i++) {
			if (indices[i] == 2) {
				++pairCount;
			}
		}

		return pairCount == 2;
	}

	private boolean hasPair(String[] hand) {
		return hasDuplicate(hand, 2);
	}

	private int returnHighCard(String[] hand) {
		int newHand[] = sortByRank(hand);
		int max = newHand[0];
		for (int i = 1; i < newHand.length; i++) {
			if (newHand[i] > max) {
				max = newHand[i];
			}
		}
		return max;
	}

	private int tieBreaker(String[] hand) {
		int score = 0;
		int[] sortedHand = sortByRank(hand);
		for (int i = 0; i < hand.length; i++) {
			if (getSuit(hand[i]).equals("diamonds")) {
				score += 20;
			} else if (getSuit(hand[i]).equals("spades")) {
				score += 18;
			} else if (getSuit(hand[i]).equals("clubs")) {
				score += 16;
			} else if (getSuit(hand[i]).equals("hearts")) {
				score += 14;
			}
			score += sortedHand[i];
		}
		return score;
	}

	public String determineWinner(String[] hand1, String[] hand2) {
		String winner = "";
		int score1 = Integer.parseInt(getScore(hand1)[0]);
		int score2 = Integer.parseInt(getScore(hand2)[0]);
		if (score1 > score2) {
			winner = "player 1";
		} else if (score2 > score1) {
			winner = "player 2";
		} else {
			if (tieBreaker(hand1) > tieBreaker(hand2)) {
				winner = "player 1";
			} else {
				winner = "player 2";
			}
		}
		return "The winner is " + winner;
	}
}
