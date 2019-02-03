package com.example.securitymvc.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "members")
@Setter
@Getter
@ToString(exclude = "roleList")
@EqualsAndHashCode
public class Member {

    @Id
    private String urid;

    private String password;

    private String username;

    @CreationTimestamp
    private LocalDateTime regDt;

    @UpdateTimestamp
    private LocalDateTime updateDt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member")
    private List<Role> roleList;
}
