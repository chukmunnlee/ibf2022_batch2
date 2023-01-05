package sdf;

public class PlaystoreData {

	private String category;
	private String highestRatedGame;
	private float highestRating = -100f;
	private String lowestRatedGame;
	private float lowestRating = 100f;
	private float totalRating = 0f;
	private int count = 0;

	public PlaystoreData(String category) { this.category = category; }

	public void setCategory(String category) { this.category = category; }
	public String getCategory() { return this.category; }

	public float getAverageRating() { return this.totalRating / this.count; }

	public void setHighestRatedGame(String highestRatedGame) { this.highestRatedGame = highestRatedGame; }
	public String getHighestRatedGame() { return this.highestRatedGame; }

	public void setHighestRating(float highestRating) { this.highestRating = highestRating; }
	public float getHighestRating() { return this.highestRating; }

	public void setLowestRatedGame(String lowestRatedGame) { this.lowestRatedGame = lowestRatedGame; }
	public String getLowestRatedGame() { return this.lowestRatedGame; }

	public void setLowestRating(float lowestRating) { this.lowestRating = lowestRating; }
	public float getLowestRating() { return this.lowestRating; }

	public void add(float rating) {
		totalRating += rating;
		count++;
	}
	public void evaluate(String name, float rating) {
		if (rating > this.highestRating) {
			this.highestRating = rating;
			this.highestRatedGame = name;
		} else if (rating < this.lowestRating) {
			this.lowestRating = rating;
			this.lowestRatedGame = name;
		}
	}
}
