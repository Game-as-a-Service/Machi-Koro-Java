package domain.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GameOverEvent extends DomainEvent {
    public String message;

}
