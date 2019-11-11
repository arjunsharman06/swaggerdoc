package co.pragra.swaggerbank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity //to store it database
@Table(name="bank_customer") //name of table in db
@Data               //generate Getter & Setter
@NoArgsConstructor  //generate default constructor
@AllArgsConstructor //generate all constructor
public class Customer {

    //will generate the ID by JPA automatically
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(name="ID",nullable = false)
    private Long id;
    @Column(name="Name",nullable = false,length = 255)
    private String name;
    @Column(name="Phone")
    //Pattern validation
    //@Pattern(regexp="^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
    private String phone;

    @Column(name="CreateDate")
    private Instant createDate;
}
