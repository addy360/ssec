package com.addy360.sec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private long id;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
