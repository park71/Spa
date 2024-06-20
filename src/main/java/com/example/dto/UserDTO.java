package com.example.dto;

import com.example.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    private String name;
    private String password;
    private String email;
    //private String phone_num;
   // private String school;
   // private String year;
   // private String major;
    public static UserDTO toMemberDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
       // userDTO.setPhone_num(userEntity.getPhone_num());
        //userDTO.setSchool(userEntity.getSchool());
       // userDTO.setYear(userEntity.getYear());
       // userDTO.setMajor(userEntity.getMajor());

        return userDTO;
    }



}
