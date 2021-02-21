import redis.clients.jedis.Jedis;

/**
 * Created by Anuj on 03-08-2017.
 */
public class RedisConnection {
    static Jedis jedis = new Jedis("127.0.0.1",6379);
    public static void main(String[] args){
        System.out.println(jedis.ping());
    }
}
