package ro.itschool.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TowerNotFoundException extends Exception {
    public TowerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
