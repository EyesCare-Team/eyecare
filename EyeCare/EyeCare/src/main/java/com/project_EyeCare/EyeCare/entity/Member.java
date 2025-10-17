package com.project_EyeCare.EyeCare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_name;
    private String email;
    private String password;
    private float vision_left;
    private float vision_right;
    private String address;
    private String phone;

}