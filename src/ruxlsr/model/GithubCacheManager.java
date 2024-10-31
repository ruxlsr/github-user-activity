package ruxlsr.model;

import java.io.File;
import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;
import redis.embedded.core.RedisServerBuilder;

public class GithubCacheManager {
    int port;
    RedisServer redisServer;
    Jedis jedis;

    public GithubCacheManager(int port) {
        this.port = port;

        File dir = new File("/tmp/redis");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        redisServer = new RedisServerBuilder()
                .setting("save 1 100")
                .setting("appendonly yes")
                .setting("dir /tmp/redis")
                .port(port)
                .build();

        jedis = new Jedis("localhost", port);
    }

    public void startServices() throws IOException {
        this.redisServer.start();
    }

    public void storeCache(String key, String value) {
        if (!key.isBlank() && !value.isBlank()) {
            String res = this.jedis.setex(key, 30, value);
            System.out.println("store cache res:" + res);
            return;
        }
        System.err.println("Error caching: blank key or value");
    }

    public String getFromCache(String key) {
        if (!key.isEmpty()) {
            String res = this.jedis.get(key);
            System.out.println("get cache :" + res == null ? "empty" : "ok");
            if (jedis.ttl(key) > 0)
                return res;
            return null;
        }
        System.err.println("Error caching: blank key");
        return null;
    }

    public void removeFromCache(String key) {
        if (!key.isBlank()) {
            this.jedis.del(key);
            return;
        }
        System.err.println("Error caching: blank key");
    }

    public void clearCache() {
        this.jedis.flushAll();
    }

    public void stopServices() throws IOException {
        this.jedis.close();
        this.redisServer.stop();
    }
}
