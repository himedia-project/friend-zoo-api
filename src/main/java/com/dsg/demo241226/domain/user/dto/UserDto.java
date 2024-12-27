package com.dsg.demo241226.domain.user.dto;

import com.dsg.demo241226.domain.test.dto.TestResDTO;
import com.dsg.demo241226.domain.test.entity.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private String email;
    private String name;
    private List<TestResDTO> tests = new ArrayList<>(); // Eager로딩(select * from user + select * from tset * N = 1+N 문제) JPA 프록시 객체 lazy loading getter로 가져오지 않는 한 sql 호출x

}
