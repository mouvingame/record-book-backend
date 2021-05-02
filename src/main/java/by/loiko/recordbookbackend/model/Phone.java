package by.loiko.recordbookbackend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Embeddable
public class Phone {
    @NotEmpty
    @Pattern(
            regexp = "^\\+?\\d{3}\\s?\\d{2}\\s?\\d{3}\\s?\\d{4}$",
            message = "номер телефона должен быть в формате [+]NNN[ ]NN[ ]NNN[ ]NNNN")
    @Column(name = "PHONE_NUMBER", length = 16, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "PREFFERED", nullable = false)
    private boolean preffered;

    public Phone() {
    }

    public Phone(String phoneNumber, boolean preffered) {
        this.phoneNumber = phoneNumber;
        this.preffered = preffered;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isPreffered() {
        return preffered;
    }

    public void setPreffered(boolean preffered) {
        this.preffered = preffered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return phoneNumber.equals(phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
