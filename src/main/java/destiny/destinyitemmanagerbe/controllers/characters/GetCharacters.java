package destiny.destinyitemmanagerbe.controllers.characters;

import destiny.destinyitemmanagerbe.repository.auth.AuthTokenRepository;
import destiny.destinyitemmanagerbe.responses.auth.AccessTokenResponse;
import destiny.destinyitemmanagerbe.services.BungieUser.GetCurrentUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetCharacters {

    @Autowired
    private GetCurrentUserInfo getCurrentUserInfoService;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @GetMapping("/bungie/user-info")
    public ResponseEntity<String> getBungieUserInfo(@RequestHeader("userToken") String userToken) throws Exception {
        AccessTokenResponse accessToken = authTokenRepository.findByUserToken(userToken);
        if (accessToken == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return getCurrentUserInfoService.getCurrentBungieUser(accessToken.getAccessToken());
    }

}
