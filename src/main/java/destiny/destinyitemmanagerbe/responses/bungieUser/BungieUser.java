package destiny.destinyitemmanagerbe.responses.bungieUser;

import jakarta.persistence.*;

@Entity
@Table(name = "bungie-user")
public class BungieUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "membership_type")
    private Integer membershipType;

    @Column(name = "membership_id")
    private String membershipId;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "user_token")
    private String userToken;

    public BungieUser() {}

    public BungieUser(Integer membershipType, String membershipId, String displayName) {
        this.membershipType = membershipType;
        this.membershipId = membershipId;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(Integer membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
