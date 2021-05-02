package by.loiko.recordbookbackend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Embeddable
public class Fio {
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "PATRONYMIC", nullable = false)
    private String patronymic;

    public Fio() {
    }

    public Fio(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fio fio = (Fio) o;
        return surname.equals(fio.surname) &&
                name.equals(fio.name) &&
                patronymic.equals(fio.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic);
    }
}
