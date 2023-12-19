package com.example.Healthbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Entity(name="app_user")
@Table(name ="app_user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class AppUser implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty(message = "Username must not be empty")
        private String username;
        @NotNull(message = "Email must not be empty")
        @Email
        private String email;
        @NotEmpty(message = "Password must not be empty")
        private String password;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_timestamp", updatable = false)
        private Date createdAt;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_timestamp", nullable = true)
        private Date updatedAt;

        public AppUser(String email, String password, String username) {
                this.email = email;
                this.password = password;
                this.username = username;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
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
                return true;
        }
}
