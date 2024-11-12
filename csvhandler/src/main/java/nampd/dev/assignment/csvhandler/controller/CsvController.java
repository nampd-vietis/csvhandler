package nampd.dev.assignment.csvhandler.controller;

import jakarta.servlet.http.HttpServletResponse;
import nampd.dev.assignment.csvhandler.model.Book;
import nampd.dev.assignment.csvhandler.model.Car;
import nampd.dev.assignment.csvhandler.model.Film;
import nampd.dev.assignment.csvhandler.repository.BookRepository;
import nampd.dev.assignment.csvhandler.repository.CarRepository;
import nampd.dev.assignment.csvhandler.repository.FilmRepository;
import nampd.dev.assignment.csvhandler.service.CsvExportService;
import nampd.dev.assignment.csvhandler.service.CsvImportService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/csv")
public class CsvController<T> {

    private final CsvImportService csvImportService;
    private final CsvExportService csvExportService;
    private final BookRepository bookRepository;
    private final CarRepository carRepository;
    private final FilmRepository filmRepository;

    public CsvController(CsvImportService csvImportService, CsvExportService csvExportService, BookRepository bookRepository, CarRepository carRepository, FilmRepository filmRepository) {
        this.csvImportService = csvImportService;
        this.csvExportService = csvExportService;
        this.bookRepository = bookRepository;
        this.carRepository = carRepository;
        this.filmRepository = filmRepository;
    }

    //import
    @PostMapping("/import/{dataType}")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file,
                                            @PathVariable("dataType") String dataType) {
        try {
            Class<T> classRef = getClassByType(dataType);
//            Class<T> classRef = Book.class;

            JpaRepository<T, Long> repository = getRepositoryByType(dataType);

            if (repository != null) {
                csvImportService.importCsv(file, classRef, repository);
                return ResponseEntity.status(HttpStatus.OK).body("Import file successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid data type");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Import failed: " + e.getMessage());
        }
    }

    //export
    @GetMapping("/export")
    public void exportCsv(@RequestParam("type") String dataType, HttpServletResponse response) throws Exception {
        Class<?> clazz = getClassByType(dataType);
        JpaRepository<T, Long> repository = getRepositoryByType(dataType);

        if (repository != null) {
            csvExportService.exportCsv(response, clazz);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    //lấy Class dựa trên data type
    private <T> Class<T> getClassByType(String type) throws Exception {
        switch (type.toLowerCase()) {
            case "book":
                return (Class<T>) Book.class;
            case "car":
                return (Class<T>) Car.class;
            case "film":
                return (Class<T>) Film.class;
            default:
                throw new Exception("Data type not valid");
        }
//        json
    }

    //lấy repo dựa trên data type
    private <T> JpaRepository<T, Long> getRepositoryByType(String type) throws IllegalAccessException {
        switch (type.toLowerCase()) {
            case "book":
                return (JpaRepository<T, Long>) bookRepository;
            case "car":
                return (JpaRepository<T, Long>) carRepository;
            case "film":
                return (JpaRepository<T, Long>) filmRepository;
            default:
                throw new IllegalAccessException("Data type not valid");
        }
    }
}

