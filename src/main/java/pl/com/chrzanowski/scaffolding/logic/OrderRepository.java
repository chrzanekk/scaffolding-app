package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.data.repository.CrudRepository;

interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
