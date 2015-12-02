package com.dianping.phoenix.lb.mainutil;

import com.dianping.phoenix.lb.dao.mongo.MongoModelStoreImpl;
import com.dianping.phoenix.lb.model.entity.Member;
import com.dianping.phoenix.lb.model.entity.Pool;
import com.dianping.phoenix.lb.model.entity.SlbModelTree;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;

/**
 * @author mengwenchao
 *         <p/>
 *         2014年12月5日 下午6:24:37
 */
public class PoolsWeightLarger {

	private String url;

	private String db;

	public PoolsWeightLarger(String url, String db) {
		this.url = url;
		this.db = db;
	}

	public static void main(String[] argc) throws UnknownHostException {

		String url = "10.1.130.137:8080";
		String db = "slb_config_online";

		if (argc.length >= 1) {
			url = argc[0];
		}
		if (argc.length >= 2) {
			db = argc[1];
		}

		new PoolsWeightLarger(url, db).start();
	}

	private void start() throws UnknownHostException {

		String[] urlDetail = url.split(":");
		@SuppressWarnings("deprecation")
		MongoTemplate mongoTemplate = new MongoTemplate(
				new SimpleMongoDbFactory(new Mongo(urlDetail[0], Integer.parseInt(urlDetail[1])), db));

		Query query = new Query();
		query.fields().include(MongoModelStoreImpl.SLB_MODEL_POOLS_KEY);

		SlbModelTree slbModelTree = mongoTemplate.findOne(query, SlbModelTree.class, "slb_base");

		for (Pool pool : slbModelTree.getPools().values()) {

			boolean result = false;
			for (Member m : pool.getMembers()) {
				if (m.getWeight() != 1 && m.getIp().indexOf("10.101") >= 0) {
					result = true;
					break;
				}
			}
			if (result) {
				System.out.println(pool.getName());
			}

		}

	}
}
