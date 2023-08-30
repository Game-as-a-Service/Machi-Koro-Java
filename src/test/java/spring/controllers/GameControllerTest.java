package spring.controllers;

import app.output.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Bank;
import domain.Game;
import domain.Marketplace;
import domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ObjectMapper objectMapper;

    String gameId = "createGameId";

    @Test
    void rollDiceApiTest() throws Exception {
        createGame();

        GameController.RollDiceRequest request = new GameController.RollDiceRequest("123", 1);

        var result = mockMvc.perform(post("/api/games/{gameId}/roll-dice", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        var actualGame = gameRepository.findById(gameId).orElseThrow();

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("dicePoint").value(actualGame.getCurrentDicePoint()))
                .andReturn();
    }

    public void createGame() {
        Player a = Player.builder().name("playerA").id("123").build();
        Player b = Player.builder().name("playerB").id("124").build();
        Player c = Player.builder().name("playerC").id("125").build();
        Player d = Player.builder().name("playerD").id("126").build();

        Game game = Game.builder()
                .id(gameId)
                .bank(new Bank())
                .players(List.of(a, b, c, d))
                .currentDicePoint(3)
                .turnPlayer(a)
                .marketplace(new Marketplace())
                .build();

        gameRepository.save(game);
    }
}
