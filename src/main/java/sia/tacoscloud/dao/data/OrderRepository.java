package sia.tacoscloud.dao.data;

import sia.tacoscloud.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
