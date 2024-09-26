package nampd.dev.assignment.csvhandler.model;

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
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvColumn(column = "Code")
    private Long code;

    @CsvColumn(column = "Title")
    private String title;

    @CsvColumn(column = "Genre")
    private String genre;

    @CsvColumn(column = "Year")
    private int year;

}
