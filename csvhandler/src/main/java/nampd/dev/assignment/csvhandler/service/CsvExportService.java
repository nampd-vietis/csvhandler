package nampd.dev.assignment.csvhandler.service;

import jakarta.servlet.http.HttpServletResponse;
import nampd.dev.assignment.csvhandler.model.Book;
import nampd.dev.assignment.csvhandler.model.Car;
import nampd.dev.assignment.csvhandler.model.Film;
import nampd.dev.assignment.csvhandler.repository.BookRepository;
import nampd.dev.assignment.csvhandler.repository.CarRepository;
import nampd.dev.assignment.csvhandler.repository.FilmRepository;
import nampd.dev.assignment.csvhandler.validator.CsvColumn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

@Service
public class CsvExportService {

    private final CarRepository carRepository;
    private final BookRepository bookRepository;
    private final FilmRepository filmRepository;

    public CsvExportService(CarRepository carRepository, BookRepository bookRepository, FilmRepository filmRepository) {
        this.carRepository = carRepository;
        this.bookRepository = bookRepository;
        this.filmRepository = filmRepository;
    }

    public void exportCsv(HttpServletResponse response, Class<?> clazz) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.csv\"");

        PrintWriter writer = response.getWriter();

        // Ghi header cho file CSV
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(CsvColumn.class)) {
                CsvColumn annotation = field.getAnnotation(CsvColumn.class);
                writer.print(annotation.column() + ",");
            }
        }
        writer.println();

        // Lấy danh sách dữ liệu từ repository và ghi vào file CSV
        List<?> records = getRecords(clazz);
        for (Object record : records) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(CsvColumn.class)) {
                    field.setAccessible(true);
                    try {
                        writer.print(field.get(record) + ",");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            writer.println();
        }
        writer.flush();
    }

    //lấy dữ liệu từ repository dựa trên kiểu đối tượng
    private List<?> getRecords(Class<?> clazz) {
        if (clazz == Car.class) {
            return carRepository.findAll();
        } else if (clazz == Film.class) {
            return filmRepository.findAll();
        } else if (clazz == Book.class) {
            return bookRepository.findAll();
        }
        return Collections.emptyList();
    }
}

