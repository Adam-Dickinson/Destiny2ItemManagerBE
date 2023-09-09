package destiny.destinyitemmanagerbe.services.auth;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    private Map<String, String> tokenMap = new ConcurrentHashMap<>();

    public String generateUserToken() {
        return UUID.randomUUID().toString();
    }

    public void storeToken(String userToken, String oauthToken) {
        tokenMap.put(userToken, oauthToken);
    }

    public String retrieveToken(String userToken) {
        return tokenMap.get(userToken);
    }
}
