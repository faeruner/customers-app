package by.netcracker.test.vad.customers.shared;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_types")
public class CustomerType implements Serializable {
    @Id
    @SequenceGenerator(name = "customerTypeSequence", sequenceName = "customer_types_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customerTypeSequence")
    @Column(name = "customer_type_id")
    private Integer id;

    @Column(name = "customer_type_caption")
    private String customerTypeCaption;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerTypeCaption() {
        return customerTypeCaption;
    }

    public void setCustomerTypeCaption(String customerTypeCaption) {
        this.customerTypeCaption = customerTypeCaption;
    }
}
