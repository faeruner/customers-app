package by.netcracker.test.vad.customers.server;

import by.netcracker.test.vad.customers.repository.CustomerTypesRepository;
import by.netcracker.test.vad.customers.client.CustomerTypesService;
import by.netcracker.test.vad.customers.shared.CustomerTypes;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@Service("custumerTypesService")
public class CustomerTypesServiceImpl implements CustomerTypesService {

    @Autowired
    private CustomerTypesRepository customerTypesRepository;

    public CustomerTypes findOne(Integer id) {
        return customerTypesRepository.findOne(id);
    }

    public List<CustomerTypes> findAll() {
        return Lists.newArrayList(customerTypesRepository.findAll());
    }
}
