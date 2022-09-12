package sia.tacoscloud.dao.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacoscloud.entity.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
