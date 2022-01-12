package learn.mcu_dashboard.data;

import learn.mcu_dashboard.models.Person;
import org.springframework.transaction.annotation.Transactional;

public interface PersonRepository {
    Person findById(int idPerson);

    Person findPersonByName(String name);

    Person add(Person person);

    @Transactional
    boolean deleteById(int idPerson);
}
