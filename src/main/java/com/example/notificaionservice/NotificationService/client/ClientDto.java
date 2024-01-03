package com.example.notificaionservice.NotificationService.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Long uniqueCardNumber;
    private int nubmerOfTrainings;

    public ClientDto(Long id, String username, String email, String firstName, String lastName, Long uniqueCardNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniqueCardNumber = uniqueCardNumber;
    }

    public ClientDto() {
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uniqueCardNumber='" + uniqueCardNumber + '\'' +
                ", nubmerOfTrainings=" + nubmerOfTrainings +
                '}';
    }
}
