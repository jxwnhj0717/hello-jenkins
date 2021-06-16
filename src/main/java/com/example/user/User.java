package com.example.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private int id;
    private String name;
    private int age;
}
