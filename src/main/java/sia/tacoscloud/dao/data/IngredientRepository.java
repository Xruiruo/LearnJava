package sia.tacoscloud.dao.data;

import sia.tacoscloud.entity.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
