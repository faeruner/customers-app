package by.netcracker.test.vad.customers.shared;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_types")
public class CustomerTypes implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "customer_type_id")
    private Integer customerTypeId;

    @Column(name = "customer_type_caption")
    private String customerTypeCaption;

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeCaption() {
        return customerTypeCaption;
    }

    public void setCustomerTypeCaption(String customerTypeCaption) {
        this.customerTypeCaption = customerTypeCaption;
    }
}
