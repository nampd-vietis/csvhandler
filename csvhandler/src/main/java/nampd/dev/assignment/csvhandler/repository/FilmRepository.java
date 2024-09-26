package nampd.dev.assignment.csvhandler.repository;

import nampd.dev.assignment.csvhandler.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
