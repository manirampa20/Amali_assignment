package com.managament.product_app.DTO;

import com.managament.product_app.module.Product;
import com.managament.product_app.module.Role;

import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private List<Product> products;

    // constructors, getters, and setters

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String email, List<Role> roles, List<Product> products) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
