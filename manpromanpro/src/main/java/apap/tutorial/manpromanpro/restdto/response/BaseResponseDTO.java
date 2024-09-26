package apap.tutorial.manpromanpro.restdto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponseDTO<T> {
    private int status;
    private T data;
    private String message;
    private Date timestamp;
}
