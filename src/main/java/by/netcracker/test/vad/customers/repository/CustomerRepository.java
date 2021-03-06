package by.netcracker.test.vad.customers.repository;

import by.netcracker.test.vad.customers.shared.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    List<Customer> findByFirstNameMetaphoneLikeOrLastNameMetaphoneLikeOrderByModifiedWhenDesc(String firstName, String lastName, Pageable page);

    List<Customer> findByOrderByModifiedWhenDesc(Pageable page);
}
