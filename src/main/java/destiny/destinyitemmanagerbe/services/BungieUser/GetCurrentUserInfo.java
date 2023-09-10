package destiny.destinyitemmanagerbe.services.BungieUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCurrentUserInfo {

    @Value("${bungie.apiKey}")
    private String apiKey;

    public ResponseEntity<String> getCurrentBungieUser(String accessToken) {

        String uri = "https://www.bungie.net/Platform//User/GetMembershipsForCurrentUser/";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-Key", apiKey);
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> bungieResponse = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        // Create a new ResponseEntity using data from the Bungie response but not its headers
        ResponseEntity<String> customResponse = new ResponseEntity<>(bungieResponse.getBody(), HttpStatus.OK);
        return customResponse;
    }
}
