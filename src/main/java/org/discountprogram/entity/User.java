package org.discountprogram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.discountprogram.constant.UserType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "DP_USER")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private String name;

    /**
     * User Type is the Main key to difference between Users
     */
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreationTimestamp
    private OffsetDateTime registrationDate;

}
