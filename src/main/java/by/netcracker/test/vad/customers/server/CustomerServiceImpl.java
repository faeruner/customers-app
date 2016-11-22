package by.netcracker.test.vad.customers.server;

import by.netcracker.test.vad.customers.client.CustomerService;
import by.netcracker.test.vad.customers.repository.CustomerRepository;
import by.netcracker.test.vad.customers.shared.Customer;
import com.google.common.collect.Lists;
import org.apache.commons.codec.language.Metaphone;
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
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    public Customer findOne(Integer id) {
        return customerRepository.findOne(id);
    }

    public List<Customer> findByName(String str) {
        if (str.isEmpty())
            return customerRepository.findTop10ByOrderByModifiedWhenDesc();
        else {
            Metaphone metaphone = new Metaphone();
            String code = "%" + metaphone.encode(str) + "%";
            return customerRepository.findTop20ByFirstNameMetaphoneLikeOrLastNameMetaphoneLikeOrderByModifiedWhenDesc(code, code);
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
        return Lists.newArrayList(customerRepository.findTop10ByOrderByModifiedWhenDesc());
    }

    @Transactional
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }

    @Transactional
    public Customer save(Customer entity) {
        entity.setModifiedWhen(new Date());
        Metaphone metaphone = new Metaphone();
        metaphone.setMaxCodeLen(50);
        entity.setFirstNameMetaphone(metaphone.encode(entity.getFirstName()));
        entity.setLastNameMetaphone(metaphone.encode(entity.getLastName()));
        return customerRepository.save(entity);
    }

}
