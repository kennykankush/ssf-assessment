package vttp.batch5.ssf.noticeboard.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeRepository {

	/*
	 * Write the redis-cli command that you use in this method in the comment. 
	 * HSET redisKey id timestamp

	 */

	 @Autowired
	 @Qualifier("jsonTemplate")
	 private RedisTemplate<String,Object> template;

	private String redisKey = "postIDs";

	public void insertNotices(String id, long timestamp) {

		template.opsForHash().put(redisKey, id, timestamp);

	}


}
