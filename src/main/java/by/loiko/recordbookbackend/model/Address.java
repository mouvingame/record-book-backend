package by.loiko.recordbookbackend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Embeddable
public class Address {
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "CITY", nullable = false)
    private String city;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "STREET", nullable = false)
    private String street;

    @NotNull
    @Positive
    @Column(name = "HOUSE_NUMBER", nullable = false)
    private Integer houseNumber;

    @NotNull
    @Positive
    @Column(name = "FLAT_NUMBER", nullable = false)
    private Integer flatNumber;

    public Address() {
    }

    public Address(String city, String street, Integer houseNumber, Integer flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city.equals(address.city) &&
                street.equals(address.street) &&
                houseNumber.equals(address.houseNumber) &&
                flatNumber.equals(address.flatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber, flatNumber);
    }
}
