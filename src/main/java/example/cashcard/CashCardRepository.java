package example.cashcard;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The cash card repository.
 *
 * <p>Spring Data JDBC provides the implementation for this interface
 * at runtime.
 *
 * @author Felipe Gutierrez
 * @author Josh Cummings
 */
@Repository
public interface CashCardRepository extends CrudRepository<CashCard, Long> {
    @Query("select * from cash_card cc where cc.owner = :#{authentication.name}")
    Iterable<CashCard> findAll();
}
