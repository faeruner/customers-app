package by.netcracker.test.vad.customers.server;

import by.netcracker.test.vad.customers.client.CustomerTypeService;
import by.netcracker.test.vad.customers.repository.CustomerTypeRepository;
import by.netcracker.test.vad.customers.shared.CustomerType;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@Service("customerTypeService")
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    public CustomerType findOne(Integer id) {
        return customerTypeRepository.findOne(id);
    }

    public List<CustomerType> findAll() {
        return Lists.newArrayList(customerTypeRepository.findAll());
    }
}
