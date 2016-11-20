package by.netcracker.test.vad.customers.repository;

import by.netcracker.test.vad.customers.shared.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
}
