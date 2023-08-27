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
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    String gameId = "createGameId";

    @Test
    void rollDiceApiTest() {
        createGame();

        GameController.RollDiceRequest request = new GameController.RollDiceRequest("123", 1);

        try {
            MvcResult result = mockMvc.perform(post("/api/games/{gameId}/roll-dice", gameId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = result.getResponse().getContentAsString();

            var actualGame = gameRepository.findAll().get(0);
            var expect = "{\"dicePoint\":" + actualGame.getCurrentDicePoint() + "}";
            assertThat(responseContent).isEqualTo(expect);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
