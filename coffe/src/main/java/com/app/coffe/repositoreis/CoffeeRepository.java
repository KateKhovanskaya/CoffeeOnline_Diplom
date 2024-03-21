package com.app.coffe.repositoreis;

import com.app.coffe.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<CoffeeOrder, Long> {
}
