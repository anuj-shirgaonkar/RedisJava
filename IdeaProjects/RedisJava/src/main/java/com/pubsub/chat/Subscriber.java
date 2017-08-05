package com.pubsub.chat;


import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Anuj on 05-08-2017.
 */
public class Subscriber extends JedisPubSub {
    private static Logger logger = Logger.getLogger(Subscriber.class);

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Message: "+message);
        logger.info("Message received. Channel: {" + channel + "}, Msg: {" + message + "}");
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}
