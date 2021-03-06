package com.cranesteam.partylist.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * User class
 * create for auth services
 *
 * @author ilyaivankin
 *
 * @see org.springframework.security.core.userdetails.UserDetails
 * @see java.io.Serializable
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Username")
    private String username;
    @Column(name = "SecondName")
    private String secondName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Name")
    private String name;
    @Column(name = "Avatar")
    private boolean image;
    @Column(name = "PhoneNumber")
    private String number;
    @Column(name = "Password")
    private String password;
    @Column(name = "Active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
