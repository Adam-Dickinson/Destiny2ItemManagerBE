package destiny.destinyitemmanagerbe.services.auth;

import destiny.destinyitemmanagerbe.repository.auth.AuthTokenRepository;
import destiny.destinyitemmanagerbe.responses.auth.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public String generateUserToken() {
        return UUID.randomUUID().toString();
    }

    public void storeToken(AccessTokenResponse tokenResponse) {
        authTokenRepository.save(tokenResponse);
    }

    public String retrieveToken(String userToken) {
        AccessTokenResponse tokenEntity = authTokenRepository.findByUserToken(userToken);
        if (tokenEntity != null) {
            return tokenEntity.getAccessToken(); // Return the access token corresponding to the userToken
        }
        return null;
    }
}

