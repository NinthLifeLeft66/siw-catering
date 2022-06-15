package it.uniroma3.siw.catering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.GoogleUserInfo;
import it.uniroma3.siw.catering.model.User;

@Service
public class CustomOidcUserService extends OidcUserService{
	
	@Autowired
	private UserService userService;
	
	@Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		
        OidcUser oidcUser = super.loadUser(userRequest);
        
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
        
        User user = userService.findByoAuthUniqueIdentifier(googleUserInfo.getId());
        
        if(user != null) {
            	return oidcUser;
        } else {
        	try {
                return processOidcUser(userRequest, oidcUser);
           } catch (Exception ex) {
               throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
           }
        }

    }
	
	private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
        
        User user = new User(googleUserInfo.getId(), googleUserInfo.getGivenName(),
        		googleUserInfo.getFamilyName(), false);

        userService.save(user);

        return oidcUser;
    }

}
