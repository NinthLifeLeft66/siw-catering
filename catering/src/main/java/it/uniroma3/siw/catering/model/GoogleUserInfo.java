package it.uniroma3.siw.catering.model;

import java.util.Map;

public class GoogleUserInfo {
	
	private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getGivenName() {
        return (String) attributes.get("given_name");
    }
    
    public String getFamilyName() {
        return (String) attributes.get("family_name");
    }
}
