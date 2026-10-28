
package util;

import model.annotation.MaxLength;
import model.annotation.Positive;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationValidator {
    public static String[] validate(Object obj) {
        ArrayList<String> errors = new ArrayList<>();

        Class<?> type = obj.getClass();

        while (type != null) {
            for (Field field : type.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);

                    if (field.isAnnotationPresent(Positive.class)
                            && value instanceof Number
                            && ((Number) value).doubleValue() <= 0) {
                        errors.add(field.getName() + ": "
                                + field.getAnnotation(Positive.class).message());
                    }

                    if (field.isAnnotationPresent(MaxLength.class)
                            && value instanceof String
                            && ((String) value).length()
                            > field.getAnnotation(MaxLength.class).value()) {
                        errors.add(field.getName()
                                + ": maximum length is "
                                + field.getAnnotation(MaxLength.class).value());
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Cannot validate " + field.getName());
                }
            }

            type = type.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}
