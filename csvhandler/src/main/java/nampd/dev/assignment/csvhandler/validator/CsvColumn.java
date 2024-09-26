package nampd.dev.assignment.csvhandler.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Giữ annotation trong runtime
@Target(ElementType.FIELD) // Áp dụng cho các trường (fields)
public @interface CsvColumn {
    String column();
    boolean required() default false;
}
