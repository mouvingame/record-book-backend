package by.loiko.recordbookbackend.service;

import by.loiko.recordbookbackend.exceptions.NonUniqueDataException;
import by.loiko.recordbookbackend.exceptions.ResourceNotFoundException;
import by.loiko.recordbookbackend.exceptions.TooMuchPrefferedWaysException;
import by.loiko.recordbookbackend.model.Email;
import by.loiko.recordbookbackend.model.Human;
import by.loiko.recordbookbackend.model.Phone;
import by.loiko.recordbookbackend.repositories.HumanRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.stream.Collectors;

@Service
@Validated
public class HumanService {
    private final HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public Human create(@Valid Human newest) {
        long count = humanRepository.countOfPhoneNumbers(
                newest.getPhones().stream().map(Phone::getPhoneNumber).collect(Collectors.toSet()));

        if (count > 0) {
            throw new NonUniqueDataException();
        } else {
            count = humanRepository.countOfEmailAddresses(
                    newest.getEmails().stream().map(Email::getEmailAddress).collect(Collectors.toSet()));

            if (count > 0)
                throw new NonUniqueDataException();
        }

        checkPrefferedTags(newest);
        return humanRepository.save(newest);
    }

    public Iterable<Human> getAll() {
        return humanRepository.findAll();
    }

    public Human getHuman(@Positive long id) {
        return humanRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Human updateHuman(@Valid Human updater, @Positive long id) {
        Human human = humanRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        long count = humanRepository.countOfPhoneNumbersBesidesHuman(
                updater.getPhones().stream().map(Phone::getPhoneNumber).collect(Collectors.toSet()), id);

        if (count > 0) {
            throw new NonUniqueDataException();
        } else {
            count = humanRepository.countOfEmailAddressesBesidesHuman(
                    updater.getEmails().stream().map(Email::getEmailAddress).collect(Collectors.toSet()), id);

            if (count > 0)
                throw new NonUniqueDataException();
        }

        checkPrefferedTags(updater);
        human.setFio(updater.getFio());
        human.setBirthday(updater.getBirthday());
        human.setAddress(updater.getAddress());
        human.getPhones().clear();
        human.getPhones().addAll(updater.getPhones());
        human.getEmails().clear();
        human.getEmails().addAll(updater.getEmails());
        return humanRepository.save(human);
    }

    public void deleteHuman(@Positive long id) {
        Human human = humanRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        humanRepository.delete(human);
    }

    private void checkPrefferedTags(Human human) {
        long count = prefferedCount(human.getPhones().stream().map(Phone::isPreffered).toArray(Boolean[]::new));

        if (count > 1) {
            throw new TooMuchPrefferedWaysException();
        } else {
            count = prefferedCount(human.getEmails().stream().map(Email::isPreffered).toArray(Boolean[]::new));

            if (count > 1)
                throw new TooMuchPrefferedWaysException();
        }
    }

    private int prefferedCount(Boolean[] prefferedTags) {
        int count = 0;
        for (boolean tag : prefferedTags)
            if (tag) count++;
        return count;
    }
}
