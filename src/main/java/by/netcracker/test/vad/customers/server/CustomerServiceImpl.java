package by.netcracker.test.vad.customers.server;

import by.netcracker.test.vad.customers.client.CustomerService;
import by.netcracker.test.vad.customers.repository.CustomerRepository;
import by.netcracker.test.vad.customers.shared.Customer;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.SerializationException;
import org.apache.commons.codec.language.Metaphone;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.spring4gwt.server.SpringGwtRemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@Service("customerService")
@SuppressWarnings("serial")
public class CustomerServiceImpl extends SpringGwtRemoteServiceServlet implements CustomerService {

    private Logger log = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String processCall(String payload) throws SerializationException {
        log.info("[HOST:" + getThreadLocalRequest().getRemoteHost() + "]");
        return super.processCall(payload);
    }


    public Customer findOne(Integer id) {
        log.info("find by id: " + id);
        return customerRepository.findOne(id);
    }

    public List<Customer> findByName(String str) {
        log.info("find by Metaphone: " + str);
        if (str.isEmpty())
            return customerRepository.findByOrderByModifiedWhenDesc(new PageRequest(0, 10));
        else {
            Metaphone metaphone = new Metaphone();
            String code = "%" + metaphone.encode(str) + "%";
            log.info("find by Metaphone code: " + code);
            return customerRepository.findByFirstNameMetaphoneLikeOrLastNameMetaphoneLikeOrderByModifiedWhenDesc(code, code, new PageRequest(0, 20));
        }
    }

    public List<Customer> findPage(Integer pageNumber, Integer pageSize) {
        Pageable page = new PageRequest(pageNumber, pageSize);
        Page<Customer> resultPage = customerRepository.findAll(page);
        if (page.getPageNumber() > 0 && resultPage.getTotalPages() < page.getPageNumber() + 1) {
            page = new PageRequest(page.getPageNumber() - 1, page.getPageSize());
            return findPage(page.getPageNumber(), page.getPageSize());
        }
        return resultPage.getContent();
    }

    public List<Customer> findAll() {
        log.info("find all");
        return Lists.newArrayList(customerRepository.findByOrderByModifiedWhenDesc(new PageRequest(0, 10)));
    }

    @Transactional
    public void delete(Customer entity) {
        log.info("delete customer: " + entity.getFirstName() + " " + entity.getLastName());
        customerRepository.delete(entity);
    }

    @Transactional
    public Customer save(Customer entity) {
        log.info("save customer: " + entity.getFirstName() + " " + entity.getLastName());
        entity.setModifiedWhen(new Date());
        Metaphone metaphone = new Metaphone();
        metaphone.setMaxCodeLen(50);
        entity.setFirstNameMetaphone(metaphone.encode(entity.getFirstName()));
        entity.setLastNameMetaphone(metaphone.encode(entity.getLastName()));
        return customerRepository.save(entity);
    }

}
