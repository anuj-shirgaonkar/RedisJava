package com.pubsub.chat;

/**
 * Created by Anuj on 05-08-2017.
 */


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.logging.Logger;

public class Application {

    public static final String CHANNEL_NAME = "yoda";

    private static Logger logger = Logger.getLogger("Application.class");

    public static void main(String[] args) throws Exception {

        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        final JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379, 0);
        final Jedis subscriberJedis = jedisPool.getResource();
        final Subscriber subscriber = new Subscriber();

        new Thread(new Runnable() {

            public void run() {
                try {
                    logger.info("Subscribing to \"yoda\". This thread will be blocked.");
                    subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
                    logger.info("Subscription ended.");
                } catch (Exception e) {
                    //logger.error("Subscribing failed.", e);
                }
            }
        }).start();

        final Jedis publisherJedis = jedisPool.getResource();

        new Publisher(publisherJedis, CHANNEL_NAME).start();

        subscriber.unsubscribe();
        jedisPool.returnResource(subscriberJedis);
        jedisPool.returnResource(publisherJedis);
    }
}
