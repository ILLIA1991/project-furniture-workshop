package project.furnitureworkshop.demo.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> violations;

    public ValidationException(String message) {
        super(message);
        this.violations = violations;
    }

    public List<String> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return getMessage() + "{" +
                "violations=" + violations +
                '}';
    }
}
