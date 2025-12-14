package testtask.shift.shopapi.error;

import java.util.List;

public class ErrorResponse {
    public int status;
    public String error;
    public String message;
    public String path;
    public List<FieldViolation> violations;

    public static class FieldViolation {
        public String field;
        public String message;

        public FieldViolation(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}

