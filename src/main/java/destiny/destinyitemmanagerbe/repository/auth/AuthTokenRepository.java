package destiny.destinyitemmanagerbe.repository.auth;

import destiny.destinyitemmanagerbe.responses.auth.AccessTokenResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AccessTokenResponse, Long> {

    AccessTokenResponse findFirstByOrderByIdDesc();
}
