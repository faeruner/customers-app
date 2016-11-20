package by.netcracker.test.vad.customers.client;

import by.netcracker.test.vad.customers.shared.CustomerTypes;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("customerTypeService")
public interface CustomerTypesService  extends RemoteService {

    CustomerTypes findOne(Integer id);

    List<CustomerTypes> findAll();
}
