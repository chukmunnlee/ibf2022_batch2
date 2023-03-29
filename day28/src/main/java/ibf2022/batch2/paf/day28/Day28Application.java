package ibf2022.batch2.paf.day28;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.batch2.paf.day28.repositories.TvShowRepository;

@SpringBootApplication
public class Day28Application implements CommandLineRunner {

	@Autowired 
	private TvShowRepository tvshowRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day28Application.class, args);
	}

	@Override
	public void run(String... args) {

		//tvshowRepo.findTvShowsByType("Animation");
		//tvshowRepo.groupShowsByTimezone();
		//tvshowRepo.summarizeTvShows("scripted");
		tvshowRepo.showCategories();
	}

}
