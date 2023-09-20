package spring.controllers;

import app.usecase.BuyCardUseCase;
import app.usecase.FlipLandMarkUseCase;
import app.usecase.RollDiceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.presenter.BuyCardPresenter;
import spring.presenter.FlipLandMarkPresenter;
import spring.presenter.RollDicePresenter;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {
    private final RollDiceUseCase rollDiceUseCase;
    private final BuyCardUseCase buyCardUseCase;
    private final FlipLandMarkUseCase flipLandMarkUseCase;

    @PostMapping("/{gameId}/roll-dice")
    public ResponseEntity<?> rollTheDice(@PathVariable String gameId, @RequestBody RollDiceRequest request) {
        var presenter = new RollDicePresenter();
        rollDiceUseCase.execute(request.toRequest(gameId), presenter);

        return presenter.getViewModel()
                .map(ResponseEntity::ok)
                .orElseGet(noContent()::build);
    }

    @PostMapping("/{gameId}/player:buyCard")
    public ResponseEntity<?> buyCard(@PathVariable String gameId, @RequestBody BuyCardRequest request) {
        var presenter = new BuyCardPresenter();
        buyCardUseCase.execute(request.toRequest(gameId), presenter);

        return presenter.getViewModel()
                .map(ResponseEntity::ok)
                .orElse(noContent().build());
    }

    @PostMapping("/{gameId}/player:flipLandMark")
    public ResponseEntity<?> flipLandMark(@PathVariable String gameId, @RequestBody FlipLandMarkRequest request) {
        var presenter = new FlipLandMarkPresenter();
        flipLandMarkUseCase.execute(request.toRequest(gameId), presenter);

        return presenter.getViewModel()
                .map(ResponseEntity::ok)
                .orElse(noContent().build());
    }


    public record RollDiceRequest(String playerId, int diceCount) {
        public RollDiceUseCase.Request toRequest(String gameId) {
            return new RollDiceUseCase.Request(gameId, playerId, diceCount);
        }
    }


    public record BuyCardRequest(String playerId, String cardName) {
        public BuyCardUseCase.Request toRequest(String gameId) {
            return new BuyCardUseCase.Request(gameId, playerId, cardName);
        }
    }

    public record FlipLandMarkRequest(String playerId, String cardName) {
        public FlipLandMarkUseCase.Request toRequest(String gameId) {
            return new FlipLandMarkUseCase.Request(gameId, playerId, cardName);
        }
    }
}
