package ibf2022.batch2.paf.day27.models;

public class Comment {

	private int id;
	private String username;
	private int rating;
	private String comment;

	public void setId(int id) { this.id = id; }
	public int getId() { return this.id; }

	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return this.username; }

	public void setRating(int rating) { this.rating = rating; }
	public int getRating() { return this.rating; }

	public void setComment(String comment) { this.comment = comment; }
	public String getComment() { return this.comment; }

	@Override
	public String toString() {
		return "Comment{id=%d, username=%s, rating=%d, comment=%s}"
				.formatted(id, username, rating, comment);
	}

}
