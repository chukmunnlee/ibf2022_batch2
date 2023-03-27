package ibf2022.batch3.paf.server;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.batch3.paf.server.respositories.ShowsRepository;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	@Autowired
	private ShowsRepository showsRepo;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) {

		//List<Document> results = showsRepo.findTvShowByName("Game of Thrones");
		//List<Document> results = showsRepo.findShowtimeLessThan(30);
		List<Document> results = showsRepo.findMoviesByStatus("Ended");
		for (Document d: results) {
			System.out.printf(">> %s\n", d.toJson());
		}

	}
}
