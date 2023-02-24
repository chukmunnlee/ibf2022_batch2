package ibf2022.batch2.ssf.day19;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.batch2.ssf.day19.models.News;
import ibf2022.batch2.ssf.day19.services.NewsService;

@SpringBootApplication
public class Day19Application implements CommandLineRunner {

	@Autowired
	private NewsService newsSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day19Application.class, args);
	}

	@Override
	public void run(String... args) {
		List<News> articles = newsSvc.getNews("bitcoin");
		for (News n: articles) {
			System.out.printf("Title: %s\n", n.getTitle());
			System.out.printf("Author: %s\n", n.getAuthor());
			System.out.printf("Published At: %s\n", n.getPublishedAt());
			System.out.printf("Content At: %s\n", n.getContent());
			System.out.println("---------------------------");
		}
		System.out.printf(">total articles: %d\n", articles.size());
	}

}
