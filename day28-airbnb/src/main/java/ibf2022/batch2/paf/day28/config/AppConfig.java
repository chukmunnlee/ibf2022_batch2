package ibf2022.batch2.paf.day28.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {

	@Value("${connectionString}")
	private String connectionString;

	@Bean
	public MongoTemplate createTemplate() {
		MongoClient client = MongoClients.create(connectionString);
		return new MongoTemplate(client, "airbnb");
	}
}
