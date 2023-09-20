package domain.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BuyCardEvent extends DomainEvent {
    public String message;

}
