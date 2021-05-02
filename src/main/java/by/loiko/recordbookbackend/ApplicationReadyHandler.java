package by.loiko.recordbookbackend;

import by.loiko.recordbookbackend.model.*;
import by.loiko.recordbookbackend.repositories.HumanRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationReadyHandler {
    private final HumanRepository humanRepository;

    public ApplicationReadyHandler(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        Human human1 = new Human();
        human1.setFio(new Fio("shesholko", "igor", "aleksandrovich"));
        human1.setBirthday(LocalDate.of(1998, 5, 7));
        human1.setAddress(new Address("minsk", "lermontova", 27, 1));
        human1.getPhones().add(new Phone("+375 11 111 1111", true));
        human1.getPhones().add(new Phone("+375 22 222 2222", false));
        human1.getEmails().add(new Email("111@mail.ru", true));
        human1.getEmails().add(new Email("222@mail.ru", false));
        humanRepository.save(human1);

        Human human2 = new Human();
        human2.setFio(new Fio("sharai", "dima", "igorevich"));
        human2.setBirthday(LocalDate.of(1993, 5, 15));
        human2.setAddress(new Address("minsk", "jukovskogo", 2, 10));
        human2.getPhones().add(new Phone("+375 33 333 3333", true));
        human2.getPhones().add(new Phone("+375 44 444 4444", false));
        human2.getEmails().add(new Email("333@mail.ru", true));
        human2.getEmails().add(new Email("444@mail.ru", false));
        humanRepository.save(human2);
    }
}
