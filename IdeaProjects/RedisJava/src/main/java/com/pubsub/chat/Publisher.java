package com.pubsub.chat;


import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anuj on 05-08-2017.
 */
public class Publisher {

    private static final Logger logger = Logger.getLogger(Publisher.class);
    private final Jedis pubJedi;
    private final String channel;

    public Publisher(Jedis publisherJedis, String channel) {
        this.pubJedi = publisherJedis;
        this.channel = channel;
    }
    public void start() {
        logger.info("Start chatting enter \"quit\" to terminate");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String line = reader.readLine();

                if (!"quit".equals(line)) {
                    pubJedi.publish(channel, line);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("IO failure while reading input, e");
        }
    }

}
