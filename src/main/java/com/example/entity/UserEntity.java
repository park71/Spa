package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="member")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String name;
    private String password;
    private String email;
    private String sex;
    //private String phone_num;
    //private String school;
   // private String year;
    //private String major;



  /*  @SneakyThrows
    @Override
    public String toString() {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("password",password);
        jsonObject.put("email",email);
        jsonObject.put("phone_num",phone_num);
        jsonObject.put("school",school);
        jsonObject.put("year",year);
        jsonObject.put("major",major);
        jsonArray.put(jsonObject);

        return jsonObject.toString();
    }*/


}
