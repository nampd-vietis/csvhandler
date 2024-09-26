//package nampd.dev.assignment.csvhandler.controller;
//
//import nampd.dev.assignment.csvhandler.model.Car;
//import nampd.dev.assignment.csvhandler.model.Film;
//import nampd.dev.assignment.csvhandler.model.Person;
//import nampd.dev.assignment.csvhandler.repository.CarRepository;
//import nampd.dev.assignment.csvhandler.repository.FilmRepository;
//import nampd.dev.assignment.csvhandler.repository.PersonRepository;
//import nampd.dev.assignment.csvhandler.service.CsvImportService;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Set;
//
//@Controller
//@RequestMapping("/api/v1/csv")
//public class CsvImportController {
//    private final CsvImportService csvImportService;
//    private final PersonRepository personRepository;
//    private final CarRepository carRepository;
//    private final FilmRepository filmRepository;
//
//    public CsvImportController(CsvImportService csvImportService, CsvImportService csvImportService1, PersonRepository personRepository, CarRepository carRepository, FilmRepository filmRepository) {
//        this.csvImportService = csvImportService1;
//        this.personRepository = personRepository;
//        this.carRepository = carRepository;
//        this.filmRepository = filmRepository;
//    }
//
//    @PostMapping("/import/{dataType}")
//    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file,
//                                            @PathVariable("dataType") String dataType) {
//        try {
//            Class<T> clazz = getClassForType(dataType);
//            JpaRepository<T, Long> repository = getRepositoryForType(dataType);
//            Set<?> importedData = csvImportService.importCsv(file, clazz, repository);
//
//            return ResponseEntity.ok("Import successfully: " + importedData.size() + " records!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Import failed: " + e.getMessage());
//        }
//    }
//
//    //lấy Class dựa trên tên loại dữ liệu
//    private <T> Class<T> getClassForType(String type) throws Exception {
//        switch (type.toLowerCase()) {
//            case "person":
//                return (Class<T>) Person.class;
//            case "car":
//                return (Class<T>) Car.class;
//            case "film":
//                return (Class<T>) Film.class;
//            default:
//                throw new Exception("Data type not valid");
//        }
//    }
//
//    //lấy repo dựa trên data type
//    private <T> JpaRepository<T, Long> getRepositoryForType(String type) throws IllegalAccessException {
//        switch (type.toLowerCase()) {
//            case "person":
//                return (JpaRepository<T, Long>) personRepository;
//            case "car":
//                return (JpaRepository<T, Long>) carRepository;
//            case "film":
//                return (JpaRepository<T, Long>) filmRepository;
//            default:
//                throw new IllegalAccessException("Data type not valid");
//        }
//    }
//}
