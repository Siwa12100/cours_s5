package jeanmarcillac.ClientRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import redis.clients.jedis.JedisPooled;

public class RedisClientRepository implements IClientRepository {

    protected JedisPooled jedisPooled;

    public RedisClientRepository(JedisPooled jedisPooled) {
        this.jedisPooled = jedisPooled;
    }

    @Override
    public Optional<Map<Integer, List<Integer>>> recupererDonneesClients() {
        
        Set<String> clientKeys = jedisPooled.keys("client:*");
        if (clientKeys.isEmpty()) {
            return Optional.ofNullable(null);
        }

        Map<Integer, List<Integer>> donneesClients = new HashMap<>();
        for (String key : clientKeys) {

            String[] keyParts = key.split(":");
            Integer clientId = Integer.parseInt(keyParts[1]);

            List<String> stringValues = jedisPooled.lrange(key, 0, -1);
            List<Integer> intValues = new ArrayList<>();
            for (String value : stringValues) {
                if (value.equals("")) {
                    break;
                }
                intValues.add(Integer.parseInt(value));
            }

            donneesClients.put(clientId, intValues);
        }

        return Optional.of(donneesClients);
    }

    @Override
    public void sauvegarderDonneesClients(Map<Integer, List<Integer>> donneesClients) {

        for (Map.Entry<Integer, List<Integer>> entry : donneesClients.entrySet()) {
            
            String clientKey = "client:" + entry.getKey();
            jedisPooled.del(clientKey);
            
            if (entry.getValue().isEmpty()) {
                jedisPooled.rpush(clientKey, "");
            } else {
                for (Integer valeur : entry.getValue()) {
                    jedisPooled.rpush(clientKey, valeur.toString());
                }
            }
        }
    }
}
