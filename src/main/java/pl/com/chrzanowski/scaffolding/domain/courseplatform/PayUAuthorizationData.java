package pl.com.chrzanowski.scaffolding.domain.courseplatform;

import pl.com.chrzanowski.scaffolding.api.courseplatform.external.payu.AuthorizationResponse;

public class PayUAuthorizationData {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private String grantType;

    public PayUAuthorizationData(AuthorizationResponse response) {
        this.accessToken = response.getAccess_token();
        this.tokenType = response.getToken_type();
        this.expiresIn = response.getExpires_in();
        this.grantType = response.getGrant_type();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public String getGrantType() {
        return grantType;
    }
}
