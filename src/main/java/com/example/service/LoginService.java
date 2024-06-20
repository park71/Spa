package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean save(UserDTO userDTO) {
        // db에 이미 동일한 password을 가진 회원이 존재하는지 확인
        boolean isUser = userRepository.existsByEmail(userDTO.getEmail());
        if (isUser) {
            return false;
        }
        UserEntity data = new UserEntity();
        data.setName(userDTO.getName());
        data.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        data.setEmail(userDTO.getEmail());
       /* data.setPhone_num(userDTO.getPhone_num()); // Assuming getPhoneNum() method
        data.setSchool(userDTO.getSchool());
        data.setYear(userDTO.getYear());
        data.setMajor(userDTO.getMajor());
*/
        userRepository.save(data);
        return true;
    }
    public Optional<UserDTO> sign_in(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByPassword(userDTO.getPassword());
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if (bCryptPasswordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())) {
                UserDTO dto = UserDTO.toMemberDTO(userEntity);
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }
    public List<UserDTO> findAll() {
        List<UserEntity> memberEntityList = userRepository.findAll();
        List<UserDTO> memberDTOList = new ArrayList<>();
        for (UserEntity userEntity: memberEntityList) {
            memberDTOList.add(UserDTO.toMemberDTO(userEntity));
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }



}
