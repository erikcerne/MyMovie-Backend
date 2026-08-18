package com.example.MyMovies.user;

import com.example.MyMovies.UserRating.UserRating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRating> ratings;
}