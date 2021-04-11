package com.course.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class User {
    private int id;
    private String name;
    private int age;
    private String sex;
    private int attack;
    private String location;
    private int life;
    private int magic;
    private int is_hot;
    private String grounding_data;
    private double max_score;
}
