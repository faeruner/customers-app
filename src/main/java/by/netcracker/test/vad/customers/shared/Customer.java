package by.netcracker.test.vad.customers.shared;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String title;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String firstName;

    @Column(name = "first_name_metaphone")
    private String firstNameMetaphone;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String lastName;

    @Column(name = "last_name_metaphone")
    private String lastNameMetaphone;

    @Column(name = "modified_when")
    private Date modifiedWhen;

    @ManyToOne
    @JoinColumn(name = "type")
    private CustomerType type;

    public Integer getCustomerId() {
        return customerId;
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

    public String getFirstNameMetaphone() {
        return firstNameMetaphone;
    }

    public void setFirstNameMetaphone(String firstNameMetaphone) {
        this.firstNameMetaphone = firstNameMetaphone;
    }

    public String getLastNameMetaphone() {
        return lastNameMetaphone;
    }

    public void setLastNameMetaphone(String lastNameMetaphone) {
        this.lastNameMetaphone = lastNameMetaphone;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
