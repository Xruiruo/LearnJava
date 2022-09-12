package sia.tacoscloud.dao.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacoscloud.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
