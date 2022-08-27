package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitUsersAndRoles {

    private final UserService userService;
    private final RoleService roleService;

    public InitUsersAndRoles(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsers() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        User user1 = new User("test", "test");
        user1.addRole(roleService.findByName("ROLE_ADMIN"));
        user1.addRole(roleService.findByName("ROLE_USER"));


        user1.setFirstName("Михаил");
        user1.setLastName("Ильич");
        user1.setEmail("test@test.com");

        User user2 = new User("test1", "test1");
        user2.addRole(roleService.findByName("ROLE_USER"));
        user2.addRole(roleService.findByName("ROLE_ADMIN"));


        user2.setFirstName("Test1");
        user2.setLastName("Test1");
        user2.setEmail("test1@test.com");

        userService.saveUser(user1);
        userService.saveUser(user2);


    }
}

