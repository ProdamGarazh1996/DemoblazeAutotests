package utils;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DateUtils {
    public static Date getRandomDate() {
        Random random = new Random();
        Faker faker = new Faker();
        int firstYear = random.nextInt((2 - 1) + 1) + 1;
        int secondYear = random.nextInt((4 - 3) + 1) + 3;
        return faker.date().between(
                Date.from(Instant.now().atZone(ZoneOffset.UTC).plusYears(firstYear).toInstant()),
                Date.from(Instant.now().atZone(ZoneOffset.UTC).plusYears(secondYear).toInstant())
        );
    }

    public static String getMonthByDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }
}
