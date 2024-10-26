package model;

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
        redisServer = new RedisServerBuilder()
                .setting("save 30 100")
                .setting("appendonly yes")
                .port(port)
                .build();

        jedis = new Jedis("localhost", port);
    }

    public void startServices() throws IOException {
        this.redisServer.start();
    }

    public void storeCache(String key, String value) {
        if (!key.isBlank() && !value.isBlank()) {
            String res = this.jedis.set(key, value);
            System.out.println("store cache res:" + res);
            return;
        }
        System.err.println("Error caching: blank key or value");
    }

    public String getFromCache(String key) {
        if (!key.isEmpty()) {
            String res = this.jedis.get(key);
            System.out.println("get cache res:" + res);
            return res;
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
