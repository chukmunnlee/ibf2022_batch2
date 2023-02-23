package ibf2022.batch2.ssf.day18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.batch2.ssf.day18.services.HttpBinService;
import ibf2022.batch2.ssf.day18.services.WeatherService;

@SpringBootApplication
public class Day18Application implements CommandLineRunner {

	@Autowired
	private HttpBinService httpBinSvc;

	@Autowired
	private WeatherService weatherSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day18Application.class, args);
	}

	@Override
	public void run(String... args) {
		//httpBinSvc.get();
		//httpBinSvc.get("barney", "barney@gmail.com");
		//httpBinSvc.post("fred", "fred@gmail.com");
		//httpBinSvc.postAsJson("fred", "fred@gmail.com");
		//
		//weatherSvc.getWeather("Tokyo");
	}

}
