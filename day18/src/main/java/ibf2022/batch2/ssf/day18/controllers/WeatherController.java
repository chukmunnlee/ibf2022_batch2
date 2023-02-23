package ibf2022.batch2.ssf.day18.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.ssf.day18.models.Weather;
import ibf2022.batch2.ssf.day18.services.WeatherService;

@Controller
@RequestMapping(path="/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherSvc;

	@GetMapping
	public String getWeather(Model model, @RequestParam String city) {

		Optional<Weather> opt = weatherSvc.getWeather(city);
		Weather weather = opt.get();

		model.addAttribute("weather", weather);
		model.addAttribute("flag", 
				"https://countryflagsapi.com/png/%s".formatted(weather.getCountry()));

		return "weather";
	}
}
