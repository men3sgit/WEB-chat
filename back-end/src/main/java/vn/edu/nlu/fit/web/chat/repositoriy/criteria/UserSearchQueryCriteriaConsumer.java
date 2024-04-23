package vn.edu.nlu.fit.web.chat.repositoriy.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root<?> root;

    @Override
    public void accept(SearchCriteria param) {
        String operation = param.getOperation();
        String key = param.getKey();
        Object value = param.getValue();

        predicate = switch (operation) {
            case ">" -> builder.and(predicate, builder.greaterThanOrEqualTo(root.get(key), value.toString()));
            case "<" -> builder.and(predicate, builder.lessThanOrEqualTo(root.get(key), value.toString()));
            case ":" -> {
                Predicate equalPredicate = builder.equal(root.get(key), value);
                yield value instanceof String ? builder.like(root.get(key), "%" + value + "%") : equalPredicate;
            }
            default -> predicate;
        };
    }
}
