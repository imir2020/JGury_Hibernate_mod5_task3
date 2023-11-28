package entity;

import java.util.Arrays;
import java.util.Optional;

public enum Status {
    ADMIN, MANAGER;

    public static Optional<Status> find(String status){
        return Arrays.stream(values())
                .filter(user-> user.name().equals(status))
                .findFirst();
    }
}
