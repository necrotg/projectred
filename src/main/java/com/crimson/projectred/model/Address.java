package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbaddresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_SEQ", allocationSize = 1)
    private Long addressId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int residenceNumber;

    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false, length = 9)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @ToString.Exclude
    @JsonIgnore
    private Customer customer;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId != null && addressId.equals(address.addressId);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}