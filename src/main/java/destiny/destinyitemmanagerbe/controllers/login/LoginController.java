package destiny.destinyitemmanagerbe.controllers.login;

import destiny.destinyitemmanagerbe.responses.auth.AccessTokenResponse;
import destiny.destinyitemmanagerbe.services.auth.GetAccessTokenService;
import destiny.destinyitemmanagerbe.services.auth.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {

    @Value("${bungie.apiKey}")
    private String apiKey;
    @Value("${security.oauth2.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client-id}")
    private String clientID;
    @Value("${security.oauth2.redirect-uri}")
    private String redirectUri;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private GetAccessTokenService getAccessTokenService;

    @GetMapping("/authorize")
    public RedirectView authorize() {
        String uri = "https";
        String bungieAuthUrl = "https://www.bungie.net/en/OAuth/Authorize?client_id=" + clientID + "&response_type=code&redirect_uri=" + uri + "/callback";
        return new RedirectView(bungieAuthUrl);
    }

    @GetMapping("/callback")
    public void handleCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oauthToken = request.getParameter("code");
        AccessTokenResponse tokenResponse = getAccessTokenService.getAccessToken(oauthToken);

        String userToken = tokenService.generateUserToken();
        tokenResponse.setUserToken(userToken); // Store the userToken in the AccessTokenResponse entity

        tokenService.storeToken(tokenResponse); // Adjusted to store the whole entity

        response.sendRedirect("http://localhost:4200/auth-callback?userToken=" + userToken);
    }
}
