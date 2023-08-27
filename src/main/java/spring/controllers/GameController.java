package spring.controllers;

import app.usecase.RollDiceUseCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.events.DomainEvent;
import domain.events.RollDiceEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {
    private final RollDiceUseCase rollDiceUseCase;

    @PostMapping("/{gameId}/roll-dice")
    public ResponseEntity<?> rollTheDice(@PathVariable String gameId, @RequestBody RollDiceRequest request) {

        var presenter = new RollDicePresenter();
        rollDiceUseCase.execute(request.toRequest(gameId), presenter);

        return presenter.getViewModel()
                .map(ResponseEntity::ok)
                .orElseGet(noContent()::build);
    }

    @Value
    public static class RollDiceRequest {
        String playerId;
        int diceCount;


        public RollDiceUseCase.Request toRequest(String gameId) {
            return new RollDiceUseCase.Request(gameId, playerId, diceCount);
        }
    }

    public static class RollDicePresenter implements RollDiceUseCase.Presenter {
        private RollDiceViewModel viewModel;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RollDiceViewModel {
            @JsonProperty("dicePoint")
            private int dicePoint;

        }

        @Override
        public void present(List<DomainEvent> events) {
            viewModel = getEvent(events, RollDiceEvent.class)
                    .map(e -> new RollDiceViewModel(e.dicePoint))
                    .orElse(null);
        }

        public Optional<RollDiceViewModel> getViewModel() {
            return ofNullable(viewModel);
        }

        @SuppressWarnings("unchecked")
        private static <T extends DomainEvent> Optional<T> getEvent(List<DomainEvent> events,
                                                                    Class<T> type) {
            return events.stream()
                    .filter(e -> type.isAssignableFrom(e.getClass()))
                    .map(e -> (T) e)
                    .findFirst();
        }
    }

}
