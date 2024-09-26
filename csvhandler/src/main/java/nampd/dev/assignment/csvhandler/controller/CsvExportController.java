//package nampd.dev.assignment.csvhandler.controller;
//
//import nampd.dev.assignment.csvhandler.service.CsvExportService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/api/v1/csv")
//public class CsvExportController {
//    private final CsvExportService csvExportService;
//
//    public CsvExportController(CsvExportService csvExportService) {
//        this.csvExportService = csvExportService;
//    }
//
//    @PostMapping("/export")
//    public ResponseEntity<String> exportCsv(@RequestParam("className") String className) {
//        try {
//            // Dùng Reflection để lấy danh sách dữ liệu
//            Class<?> clazz = Class.forName("com.example.demo.domain." + className);
//
//            // Lấy danh sách dữ liệu từ cơ sở dữ liệu (ví dụ: sử dụng một service khác để lấy dữ liệu)
//            List<?> dataList = String filePath = "path/to/exported/file.csv";
//            csvExportService.exportCsv(dataList, filePath);
//
//            return ResponseEntity.ok("Export file successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Export file failed: " + e.getMessage());
//        }
//    }
//}
