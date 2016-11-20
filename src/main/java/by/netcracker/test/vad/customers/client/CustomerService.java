package by.netcracker.test.vad.customers.client;

import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("customerService")
public interface CustomerService extends RemoteService {

    Customer findOne(Integer id);

    List<Customer> findPage(Integer pageNumber, Integer pageSize);

    List<Customer> findAll();

    void delete(Customer entity);

    Customer save(Customer entity);

    List<Customer> findByFirstName(String str);

}
