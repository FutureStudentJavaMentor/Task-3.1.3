package com.java.preproject.Task311.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users_crud")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Не оставляй пожалуйста поле Имя  пустое ")
    @Size(min = 3 ,max = 30 , message = "Допустимое количество символов от 3 до 30, повторите попытку!")
    private String name;

    @Column(name = "last_Name")
    @NotEmpty(message = "Не оставляй пожалуйста поле Фамилия   пустое ")
    @Size(min = 3 ,max = 30 , message = "Допустимое количество символов от 3 до 30, повторите попытку!")
    private String lastName;

    @Column(name = "age")
    @NotNull( message = "Возраст не может быть отрицательным")
    @Max(value = 150,message = "Люди столько не живут ")
    private Integer age;

    @Column(name = "username")
    @NotEmpty(message = "Не оставляй пожалуйста поле Логин   пустое ")
    @Size(min = 3 ,max = 30 , message = "Допустимое количество символов от 3 до 30, повторите попытку!")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Не оставляй пожалуйста поле Пароль  пустое ")
    @Size(min = 3 ,max = 100 , message = "Допустимое количество символов от 3 до 100, повторите попытку!")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.REFRESH)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getMyUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getMyUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
