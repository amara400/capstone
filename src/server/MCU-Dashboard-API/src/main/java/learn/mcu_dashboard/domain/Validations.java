package learn.mcu_dashboard.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Validations {

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNull(LocalDate value) {
        return value == null;
    }

    public static boolean isNegative(BigDecimal value){
        if(value == null){
            return false;
        }
        return value.compareTo(BigDecimal.ZERO) < 0;
    }
}
