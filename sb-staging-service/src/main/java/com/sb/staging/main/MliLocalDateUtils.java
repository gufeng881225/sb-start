package com.sb.staging.main;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MliLocalDateUtils {
    public static LocalDate buildLocalDateByMinguoDate(String minguoDateStr){
        if(StringUtils.isNotBlank(minguoDateStr)){
            List<Integer> dateParts = Arrays.stream(minguoDateStr.split("/")).map(part ->
                Integer.parseInt(part)).collect(Collectors.toList());
            return LocalDate.from(MinguoDate.of(dateParts.get(0), dateParts.get(1), dateParts.get(2)));
        }
        return null;
    }
}
