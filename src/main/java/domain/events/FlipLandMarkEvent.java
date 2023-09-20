package domain.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FlipLandMarkEvent extends DomainEvent {
    public String message;
}
