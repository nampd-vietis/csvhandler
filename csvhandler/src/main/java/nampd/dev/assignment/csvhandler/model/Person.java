package nampd.dev.assignment.csvhandler.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nampd.dev.assignment.csvhandler.validator.CsvColumn;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvColumn(column = "Id")
    private Long id;

    @CsvColumn(column = "Name")
    private String name;

    @CsvColumn(column = "Phone_Number")
    private String phoneNumber;
}
