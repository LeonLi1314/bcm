package my.dao.pool;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.util.SysParam;

public class PoolStatistics {
	private final static Logger logger = LoggerFactory.getLogger(DBPool.class);
	private static AtomicInteger conCount = new AtomicInteger(0);

	public static void add() {
		conCount.addAndGet(1);
		if (SysParam.isDevMode() && conCount.get() > 1) {
			// 开发时一般只有一个用户访问，最多只有一个连接
			logger.error("conn count>1");
		}
	}

	public static void close() {
		int v = conCount.addAndGet(-1);
		logger.info("数据库连接数:" + v);
	}

	public static int nowCount() {
		return conCount.get();
	}
}
