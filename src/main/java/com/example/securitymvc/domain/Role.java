package com.example.securitymvc.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "role")
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;

    private String roleName;
}
