package example.cashcard;

import org.springframework.data.repository.CrudRepository;

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
}
