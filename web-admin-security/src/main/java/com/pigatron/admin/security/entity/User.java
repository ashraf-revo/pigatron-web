package com.pigatron.admin.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private List<Role> roles;

    private boolean enabled;

    // remember me token data
    @JsonIgnore
    private String tokenSeries;

    @JsonIgnore
    private String tokenValue;

    @JsonIgnore
    private Date tokenDate;


    public User() {
        //Empty constructor required for spring data
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.roles.add(new Role(role));
        this.enabled = true;
    }

    public static Builder aUser() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTokenSeries() {
        return tokenSeries;
    }

    public void setTokenSeries(String tokenSeries) {
        this.tokenSeries = tokenSeries;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }


    public static final class Builder {
        private String id;
        private String username;
        private String password;
        private String role;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            User user = new User(username, password, role);
            user.id = id;
            return user;
        }
    }
}
