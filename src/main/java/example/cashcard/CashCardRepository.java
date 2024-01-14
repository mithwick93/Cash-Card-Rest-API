package example.cashcard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * The cash card repository.
 *
 * <p>Spring Data JDBC provides the implementation for this interface
 * at runtime.
 *
 * @author Felipe Gutierrez
 * @author Josh Cummings
 */
public interface CashCardRepository extends CrudRepository<CashCard, Long> {
    Optional<CashCard> findByIdAndOwner(Long id, String owner);

    Iterable<CashCard> findByOwner(String owner);

    @Override
    default Iterable<CashCard> findAll() {
        return findByOwner(getOwner());
    }

    @Override
    default Optional<CashCard> findById(Long id) {
        return findByIdAndOwner(id, getOwner());
    }

    private String getOwner() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getName();
    }
}
