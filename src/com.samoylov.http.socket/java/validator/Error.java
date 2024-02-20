package validator;

import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String code;
    @Getter
    String message;

}
