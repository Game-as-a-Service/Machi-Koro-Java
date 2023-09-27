package spring.requests;

import app.usecase.CreateGameUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateGameRequest {
    private String playerId;
    private String playerName;

    public CreateGameUseCase.Request toRequest() {
        return new CreateGameUseCase.Request(playerId, playerName);
    }
}