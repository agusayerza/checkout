package com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto;

public class CreateUserDto {
    private String name;
    private String surname;

    public CreateUserDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}
