package nampd.dev.assignment.csvhandler.repository;

import nampd.dev.assignment.csvhandler.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
