package com.example.clinic;

import com.example.clinic.dto.ConsultationDTO;
import com.example.clinic.dto.PatientDTO;
import com.example.clinic.dto.user.UserDTO;
import com.example.clinic.model.ERole;
import com.example.clinic.model.Patient;
import com.example.clinic.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TestCreationFactory {

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls) {
        return listOf(cls, (Object[]) null);
    }

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls, Object... parameters) {
        int nrElements = new Random().nextInt(10) + 5;
        Supplier<?> supplier;

        if (cls.equals(User.class)) {
            supplier = TestCreationFactory::newUser;
        } else if (cls.equals(Patient.class)) {
            supplier = TestCreationFactory::newPatient;
        } else if (cls.equals(PatientDTO.class)) {
            supplier = TestCreationFactory::newPatientDTO;
        } else if (cls.equals(UserDTO.class)) {
            supplier = TestCreationFactory::newUserDTO;
        } else if (cls.equals(ConsultationDTO.class)) {
            supplier = TestCreationFactory::newConsultationDTO;
        } else {
            supplier = () -> new String("You failed.");
        }

        Supplier<?> finalSupplier = supplier;
        return IntStream.range(0, nrElements).mapToObj(i ->
                (T) finalSupplier.get()
        ).collect(Collectors.toSet()) // remove duplicates in case of Long for example
                .stream().collect(toList());
    }

    private static User newUser() {
        return User.builder()
                .id(randomLong())
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .build();
    }

    private static UserDTO newUserDTO() {
        return UserDTO.builder()
                .id(randomLong())
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .role(ERole.ADMIN)
                .build();
    }

    private static Patient newPatient() {
        return Patient.builder()
                .id(randomLong())
                .name(randomString())
                .address(randomString())
                .cardNumber(randomString())
                .cnp(randomString())
                .birthDate(new Date())
                .build();
    }

    private static PatientDTO newPatientDTO() {
        return PatientDTO.builder()
                .id(randomLong())
                .name(randomString())
                .address(randomString())
                .cardNumber(randomString())
                .cnp(randomString())
                .birthDate("17-10-1999")
                .build();
    }

    private static ConsultationDTO newConsultationDTO() {
        return ConsultationDTO.builder()
                .id(randomLong())
                .time("12-05-2020 00:00")
                .patient_id(randomLong())
                .user_id(randomLong())
                .description(randomString())
                .build();
    }

    public static String randomEmail() {
        return randomString() + "@" + randomString() + ".com";
    }

    public static byte[] randomBytes() {
        byte[] bytes = new byte[Math.toIntExact(randomLong())];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public static long randomLong() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Calendar getCalendar(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        calendar.setTime(date);

        return calendar;
    }
}
