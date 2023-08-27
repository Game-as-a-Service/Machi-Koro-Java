package domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RollDiceEvent extends DomainEvent {
    public int dicePoint;
}
