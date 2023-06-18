package edu.miu.cs.cs545.propertymanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Offer> offers;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Property> properties;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<FavoriteList> favorites;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
