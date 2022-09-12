package sia.tacoscloud.dao.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacoscloud.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
