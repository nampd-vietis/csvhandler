package nampd.dev.assignment.csvhandler.service;

import nampd.dev.assignment.csvhandler.validator.CsvColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Service
public class CsvImportService {

    // Import CSV dữ liệu tổng quát cho bất kỳ kiểu đối tượng nào
    public <T> Set<T> importCsv(MultipartFile file, Class<T> clazz, JpaRepository<T, Long> repository) throws Exception {
        Set<T> resultSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            //đọc header trong file CSV
            String[] headers = reader.readLine().split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                //dùng reflection tạo một instance của class kiểu T
                T instance = clazz.getDeclaredConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(CsvColumn.class)) {
                        CsvColumn annotation = field.getAnnotation(CsvColumn.class);
                        int columnIndex = getColumnIndex(headers, annotation.column());
                        if (columnIndex != -1) {
                            field.setAccessible(true);
                            setFieldValue(field, instance, values[columnIndex]);
                        }
                    }
                }
                System.out.println("Save: " + instance);
                resultSet.add(instance);
                saveData(instance, repository);
            }
        }
        return resultSet;
    }

    //tìm index của cột CSV dựa vào header
    private int getColumnIndex(String[] headers, String columnName) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    // Set giá trị cho thuộc tính dựa trên kiểu dữ liệu
    private void setFieldValue(Field field, Object instance, String value) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if (fieldType == String.class) {
            field.set(instance, value);
        } else if (fieldType == int.class || fieldType == Integer.class) {
            field.set(instance, Integer.parseInt(value));
        } else if (fieldType == long.class || fieldType == Long.class) {
            field.set(instance, Long.parseLong(value));
        }
    }

    private <T> void saveData (T data, JpaRepository<T, Long> repository) {
        repository.save(data);
    }
}

