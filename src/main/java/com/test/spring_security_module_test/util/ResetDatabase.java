package com.test.spring_security_module_test.util;

import com.test.spring_security_module_test.model.Developer;
import com.test.spring_security_module_test.model.security_database.Permission;
import com.test.spring_security_module_test.model.security_database.Role;
import com.test.spring_security_module_test.model.security_database.User;
import com.test.spring_security_module_test.model.security_database.UserStatus;
import com.test.spring_security_module_test.repository.DeveloperRepository;
import com.test.spring_security_module_test.repository.security_database.PermissionRepository;
import com.test.spring_security_module_test.repository.security_database.RoleRepository;
import com.test.spring_security_module_test.repository.security_database.UserRepository;
import com.test.spring_security_module_test.repository.security_database.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ResetDatabase {
    public static Boolean restActive = false;

    final UserStatusRepository userStatusRepository;
    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;
    final UserRepository userRepository;

    final DeveloperRepository developerRepository;

    @Autowired
    public ResetDatabase(final UserStatusRepository userStatusRepository,
                         final RoleRepository roleRepository,
                         final PermissionRepository permissionRepository,
                         final UserRepository userRepository,
                         final DeveloperRepository developerRepository) {
        this.userStatusRepository = userStatusRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.developerRepository = developerRepository;
    }

    public void reset() {
        userRepository.deleteAll();
        permissionRepository.deleteAll();
        roleRepository.deleteAll();
        userStatusRepository.deleteAll();
        userStatusRepository.save(new UserStatus(null, "not active", false));
        userStatusRepository.save(new UserStatus(null, "active", true));
        userStatusRepository.save(new UserStatus(null, "suspicious", false));
        userStatusRepository.save(new UserStatus(null, "blocked", false));
        userStatusRepository.save(new UserStatus(null, "banned", false));
        permissionRepository.save(new Permission(null, "developer_read"));
        permissionRepository.save(new Permission(null, "developer_write"));
        roleRepository.save(new Role(null, "admin", Set.of(permissionRepository.findByName("developer_read").orElseThrow(),
            permissionRepository.findByName("developer_write").orElseThrow())));
        roleRepository.save(new Role(null, "user", Set.of(permissionRepository.findByName("developer_read").orElseThrow())));
        roleRepository.save(new Role(null, "viewer", Set.of(permissionRepository.findByName("developer_read").orElseThrow())));
        /*
        insert into public.user (id, name, password, role, status) values (0, 'admin', '$2a$10$hVu3eV2t6Vp2DPjYtbYFJusw.yF2slLHm8p1OZwgj7ln2pgmTu6Du', 0, 1);
        insert into public.user (id, name, password, role, status) values (1, 'user', '$2a$10$I8.RBW.ApI2f4EfUyVZ.ROWor/nk26MwpovZC7FMUVCYm0afFAS2q', 1, 1);
        insert into public.user (id, name, password, role, status) values (2, 'user1', '$2a$10$bvTBRfzlNVcR1VfwSPF6B.0cIYY4IN4N5aSBnM/AXBTOvHTa49cAS', 2, 1);
        */
        userRepository.save(new User(
            null,
            "admin",
            "$2a$10$hVu3eV2t6Vp2DPjYtbYFJusw.yF2slLHm8p1OZwgj7ln2pgmTu6Du",
            roleRepository.findByName("admin").orElseThrow(),
            userStatusRepository.findByName("active").orElseThrow()
        ));
        userRepository.save(new User(
            null,
            "user",
            "$2a$10$I8.RBW.ApI2f4EfUyVZ.ROWor/nk26MwpovZC7FMUVCYm0afFAS2q",
            roleRepository.findByName("user").orElseThrow(),
            userStatusRepository.findByName("active").orElseThrow()
        ));
        userRepository.save(new User(
            null,
            "user1",
            "$2a$10$bvTBRfzlNVcR1VfwSPF6B.0cIYY4IN4N5aSBnM/AXBTOvHTa49cAS",
            roleRepository.findByName("viewer").orElseThrow(),
            userStatusRepository.findByName("active").orElseThrow()
        ));

        developerRepository.deleteAll();
        developerRepository.save(new Developer(null, "firstname1", "secondname1"));
        developerRepository.save(new Developer(null, "firstname2", "secondname2"));
        developerRepository.save(new Developer(null, "firstname3", "secondname3"));
    }
}
