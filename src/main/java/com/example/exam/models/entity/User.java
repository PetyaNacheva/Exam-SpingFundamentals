package com.example.exam.models.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Song> playlist;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<Song> playlist) {
        this.playlist = playlist;
    }
}
