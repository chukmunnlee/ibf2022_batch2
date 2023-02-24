package ibf2022.batch2.ssf.day19.models;

import jakarta.json.JsonObject;

public class News {

	private String author;
	private String title;
	private String content;
	private String publishedAt;

	public void setAuthor(String author) { this.author = author; }
	public String getAuthor() { return this.author; }

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setContent(String content) { this.content = content; }
	public String getContent() { return this.content; }

	public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
	public String getPublishedAt() { return this.publishedAt; }

	public static News create(JsonObject jo) {
		System.out.printf("body: %s\n", jo.toString());
		News news = new News();
		news.setAuthor(jo.getString("author", "anon"));
		news.setTitle(jo.getString("title"));
		news.setContent(jo.getString("content"));
		news.setPublishedAt(jo.getString("publishedAt"));
		return news;
	}
}
