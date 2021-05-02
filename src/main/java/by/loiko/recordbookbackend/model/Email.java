package by.loiko.recordbookbackend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Embeddable
public class Email {
    @NotEmpty
    @javax.validation.constraints.Email
    @Column(name = "EMAIL_ADDRESS", nullable = false, unique = true)
    private String emailAddress;

    @Column(name = "PREFFERED", nullable = false)
    private boolean preffered;

    public Email() {
    }

    public Email(String emailAddress, boolean preffered) {
        this.emailAddress = emailAddress;
        this.preffered = preffered;
    }

    public String getEmailAddress() {
        return emailAddress;
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
        Email email = (Email) o;
        return emailAddress.equals(email.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }
}
