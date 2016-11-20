package by.netcracker.test.vad.customers.repository;

import by.netcracker.test.vad.customers.shared.CustomerType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerTypeRepository extends PagingAndSortingRepository<CustomerType, Integer> {
}
