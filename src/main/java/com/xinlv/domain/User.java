package com.xinlv.domain;

import static org.springframework.data.graph.core.Direction.INCOMING;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.annotation.RelatedToVia;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.neo4j.annotation.Indexed;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

@NodeEntity
public class User {
    private static final String SALT = "cewuiqwzie";
    @Indexed
    String login;
    String name;
    String password;
    String info;
    private Roles[] roles;

    public User() {
    }
    
    public User(String name) {
    	this(name,name,"password",new Roles[] {Roles.ROLE_USER});
    }
    
    public User(String login, String name, String password, Roles... roles) {
        this.login = login;
        this.name = name;
        this.password = encode(password);
        this.roles = roles;
    }

    private String encode(String password) {
        return new Md5PasswordEncoder().encodePassword(password, SALT);
    }

    @RelatedTo(elementClass = User.class, type="FRIEND",direction = Direction.BOTH)
    Set<User> friends;
    
    public void knows(User friend) {
        this.friends.add(friend);
    }
    
    public Set<User> getFriends() {
        return friends;
    }
    
    @RelatedToVia(elementClass = ConcernTo.class, type = "CONCERN_TO")
    Iterable<ConcernTo> concerns;
    
    public ConcernTo concern(User someone,Date requestDate) {
    	ConcernTo concernTo = relateTo(someone,ConcernTo.class,"CONCERN_TO");
    	concernTo.setRequestDate(requestDate);
    	return concernTo;
    }
    
    public Iterable<ConcernTo> getConcerns() {
    	return concerns;
    }
    
    @RelatedTo(elementClass = User.class, type = "CONCERN_TO", direction = INCOMING)
    Set<User> concernedBy;
    
    public Collection<User> getConcernedBy() {
    	return concernedBy;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, login);
    }

    public String getName() {
        return name;
    }



    public Roles[] getRole() {
        return roles;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    public void updatePassword(String old, String newPass1, String newPass2) {
        if (!password.equals(encode(old))) throw new IllegalArgumentException("Existing Password invalid");
        if (!newPass1.equals(newPass2)) throw new IllegalArgumentException("New Passwords don't match");
        this.password = encode(newPass1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFriend(User other) {
        return other!=null && getFriends().contains(other);
    }

    public enum Roles implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}
