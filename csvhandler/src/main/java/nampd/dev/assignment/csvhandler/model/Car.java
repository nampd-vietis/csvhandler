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
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvColumn(column = "Code")
    private Long code;

    @CsvColumn(column = "Brand")
    private String brand;

    @CsvColumn(column = "Model")
    private int model;

    @CsvColumn(column = "Color")
    private String color;
}
