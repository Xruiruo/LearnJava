package sia.tacoscloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacoscloud.dao.data.IngredientRepository;
import sia.tacoscloud.dao.data.TacoRepository;
import sia.tacoscloud.dao.data.UserRepository;
import sia.tacoscloud.entity.Ingredient;
import sia.tacoscloud.entity.Order;
import sia.tacoscloud.entity.Taco;
import sia.tacoscloud.entity.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;

    private TacoRepository designRepository;

    private UserRepository userRepository;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository designRepo, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepo;
        this.designRepository = designRepo;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        log.info("      --- Designing taco");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }
    //TODO 需新增提交POST请求处理

    @PostMapping
    public String processDesign(
            @Valid Taco design, Errors errors,
            @ModelAttribute Order order) {

        log.info("      --- Saving taco");

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }
}
