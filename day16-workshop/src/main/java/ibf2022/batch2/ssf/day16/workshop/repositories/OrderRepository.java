package ibf2022.batch2.ssf.day16.workshop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.ssf.day16.workshop.models.Order;

@Repository
public class OrderRepository {

	@Autowired @Qualifier("my-redis")
	private RedisTemplate<String, String> template;

	public void insertOrder(Order order) {
	}
}
