package com.addy360.sec.service;


import com.addy360.sec.enums.RoleEnum;
import com.addy360.sec.models.Role;
import com.addy360.sec.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void insertingDefaultRoles() {
        List<Role> roles = Arrays.stream(RoleEnum.values()).map(roleEnum -> {
            Role role = new Role();
            role.setRoleName(roleEnum.getRoleName());
            return role;
        }).collect(Collectors.toList());
        log.info("Inserting default roles {} to database.", roles.size());
        roleRepository.saveAll(roles);

    }
}
