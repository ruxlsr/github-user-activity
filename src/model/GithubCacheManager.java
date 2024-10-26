package model;

import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

public class GithubCacheManager {
    int port;
    RedisServer redisServer;
    Jedis jedis;

    public GithubCacheManager(int port) {
        this.port = port;
        redisServer = new RedisServer(port);
        jedis = new Jedis("Localhost", port);
    }

    public void startServices() throws IOException {
        this.redisServer.start();
    }

    public void storeCache(String key, String value) {
        if (!key.isBlank() && !value.isBlank()) {
            this.jedis.set(key, value);
            return;
        }
        System.err.println("Error caching: blank key or value");
    }

    public String getFromCache(String key) {
        if (!key.isEmpty()) {
            return this.jedis.get(key);
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
