package by.netcracker.test.vad.customers.client;

import by.netcracker.test.vad.customers.shared.CustomerType;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("customerTypeService")
public interface CustomerTypeService extends RemoteService {

    CustomerType findOne(Integer id);

    List<CustomerType> findAll();
}
