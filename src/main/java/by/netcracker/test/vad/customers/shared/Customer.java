package by.netcracker.test.vad.customers.shared;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @SequenceGenerator(name="customerSequence", sequenceName="\"CUSTOMERS_CUSTOMER_ID_seq\"")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customerSequence")
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

//    @Column
//    private String firstNameMetaphone;

    @Column(name = "last_name")
    private String lastName;

//    @Column
//    private String lastNameMetaphone;

    @Column(name = "modified_when")
    private Date modifiedWhen;

    @ManyToOne
    @JoinColumn(name = "type")
    private CustomerType type;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getModifiedWhen() {
        return modifiedWhen;
    }

    public void setModifiedWhen(Date modifiedWhen) {
        this.modifiedWhen = modifiedWhen;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getDisplayName()
    {
        return getFirstName() + " " + getLastName();
    }
}
