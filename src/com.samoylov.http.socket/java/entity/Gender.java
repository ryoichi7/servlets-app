package entity;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    male, female;

    public static Optional<Gender> find(String gender){
        return Arrays.stream(values())
                .filter(gnd -> gnd.name().equals(gender))
                .findFirst();
    }
}
