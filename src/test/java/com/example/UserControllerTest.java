package com.example;

import com.example.user.User;
import com.example.user.UserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserController controller;

    private String uri;

    @BeforeEach
    public void before() {
        uri = "http://localhost:" + port + "/user";
    }

    @AfterEach
    public void after() {
        controller.getRepository().deleteAll();
    }

    @Test
    public void addUser() throws Exception {
        User user = User.builder().name("hj").age(18).build();
        User retUser = restTemplate.postForObject(uri, user, User.class);

        assertThat(user.getName()).isEqualTo(retUser.getName());
        assertThat(user.getAge()).isEqualTo(retUser.getAge());
    }

    @Test
    public void updateUser() {
        User user = User.builder().name("hj").age(18).build();
        User retUser = restTemplate.postForObject(uri, user, User.class);

        retUser.setAge(100);
        restTemplate.put(uri, retUser);
        ResponseEntity<User> changedUserEntity = restTemplate.getForEntity(uri + "/" + retUser.getId(), User.class);
        assertThat(changedUserEntity.getBody().getAge()).isEqualTo(100);
    }



    @Test
    public void deleteUser() {
        User user = User.builder().name("hj").age(18).build();
        User retUser = restTemplate.postForObject(uri, user, User.class);

        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {};
        List<User> users = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, typeRef).getBody();
        assertThat(users.size()).isEqualTo(1);

        restTemplate.delete(uri + "/" + retUser.getId());
        users = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, typeRef).getBody();
        assertThat(users.size()).isEqualTo(0);
    }

}
