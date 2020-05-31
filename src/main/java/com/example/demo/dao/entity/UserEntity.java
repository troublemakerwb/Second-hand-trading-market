package com.example.demo.dao.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@Entity
@Table(name="c_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length=20,name="username")
    private String userName;

    @Column(length=20,name="userpwd")
    private String userPwd;

    @Column(length = 4)
    private Integer ranks; //用户等级

}
