package by.loiko.recordbookbackend.repositories;

import by.loiko.recordbookbackend.model.Human;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface HumanRepository extends CrudRepository<Human, Long> {

    @Query(value = "SELECT COUNT(*) FROM PHONE WHERE PHONE_NUMBER IN :phoneNumbers", nativeQuery = true)
    long countOfPhoneNumbers(Set<String> phoneNumbers);

    @Query(value = "SELECT COUNT(*) FROM PHONE WHERE PHONE_NUMBER IN :phoneNumbers AND HUMAN_ID != :humanId", nativeQuery = true)
    long countOfPhoneNumbersBesidesHuman(Set<String> phoneNumbers, long humanId);

    @Query(value = "SELECT COUNT(*) FROM EMAIL WHERE EMAIL_ADDRESS IN :emailAddresses", nativeQuery = true)
    long countOfEmailAddresses(Set<String> emailAddresses);

    @Query(value = "SELECT COUNT(*) FROM EMAIL WHERE EMAIL_ADDRESS IN :emailAddresses AND HUMAN_ID != :humanId", nativeQuery = true)
    long countOfEmailAddressesBesidesHuman(Set<String> emailAddresses, long humanId);
}
