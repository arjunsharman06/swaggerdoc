package co.pragra.swaggerbank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok notation
@Data  //calling getter setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private int code;
    private String description;
}
