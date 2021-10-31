package com.epam.spring.entity;

public class CustomerBuilder {
    private static final CustomerBuilder INSTANT = new CustomerBuilder();
    private int id;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";

    private CustomerBuilder() {
    }

    public static CustomerBuilder create() {
        return INSTANT;
    }

    public CustomerBuilder withId(int id) {
        this.id = id;
        return INSTANT;
    }

    public CustomerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return INSTANT;
    }

    public CustomerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return INSTANT;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return INSTANT;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return INSTANT;
    }

    public Customer builder() {
        Customer result = new Customer();
        result.setId(id);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setEmail(email);
        result.setEmail(phone);
        return result;
    }
}
