package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * The cash card REST API
 *
 * @author Felipe Gutierrez
 * @author Josh Cummings
 */
@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    private final CashCardRepository cashCards;

    public CashCardController(CashCardRepository cashCards) {
        this.cashCards = cashCards;
    }

    @GetMapping("/{requestedId}")
    @PostAuthorize("returnObject.body.owner == authentication.name")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        return cashCards.findById(requestedId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CashCard> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb, @CurrentOwner String owner) {
        CashCard newCashCard = new CashCard(newCashCardRequest.amount(), owner);
        CashCard savedCashCard = cashCards.save(newCashCard);
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).body(savedCashCard);
    }

    @GetMapping
    public ResponseEntity<Iterable<CashCard>> findAll() {
        return ResponseEntity.ok(cashCards.findAll());
    }
}