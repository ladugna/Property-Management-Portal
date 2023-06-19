package edu.miu.cs.cs545.propertymanagementsystem.model;

import edu.miu.cs.cs545.propertymanagementsystem.model.enums.Roles;
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
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //private String role;  //Owner, Admin, Customer, and Viewer.
    @Enumerated(EnumType.STRING)
   private Roles roles;
    @ManyToMany(mappedBy = "role")
    private List<User> user;
}
