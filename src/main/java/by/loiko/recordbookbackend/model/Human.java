package by.loiko.recordbookbackend.model;

import by.loiko.recordbookbackend.StringConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HUMAN")
public class Human {
    @Id
    @GeneratedValue(generator = StringConstants.ID_GENERATOR)
    private Long id;

    @Valid
    @NotNull
    private Fio fio;

    @NotNull
    @Past
    @Column(name = "BIRTHDAY", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    @Valid
    @NotNull
    private Address address;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "PHONE", joinColumns = @JoinColumn(name = "HUMAN_ID"))
    private Set<@NotNull @Valid Phone> phones = new HashSet<>();

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "EMAIL", joinColumns = @JoinColumn(name = "HUMAN_ID"))
    private Set<@NotNull @Valid Email> emails = new HashSet<>();

    public Human() {
    }

    public Long getId() {
        return id;
    }

    public Fio getFio() {
        return fio;
    }

    public void setFio(Fio fio) {
        this.fio = fio;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public Set<Email> getEmails() {
        return emails;
    }
}
