package sia.tacoscloud.dao.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacoscloud.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
