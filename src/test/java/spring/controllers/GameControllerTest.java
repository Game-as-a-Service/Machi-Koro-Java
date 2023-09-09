package spring.controllers;

import app.output.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Bank;
import domain.Game;
import domain.Marketplace;
import domain.Player;
import domain.card.establishment.WheatField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void playerBuyTrainStationTest() throws Exception {
        Player playerA = Player.builder().name("playerA").id("A").build();
        Player playerB = Player.builder().name("playerB").id("B").build();
        Player playerC = Player.builder().name("playerC").id("C").build();
        Player playerD = Player.builder().name("playerD").id("D").build();

        playerA.setTotalCoin(1);

        Game game = Game.builder()
                .id(gameId)
                .bank(new Bank())
                .players(List.of(playerA, playerB, playerC, playerD))
                .currentDicePoint(3)
                .turnPlayer(playerA)
                .marketplace(new Marketplace())
                .build();

        gameRepository.save(game);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("A","Establishment","火車站");

        var result = mockMvc.perform(post("/api/games/{gameId}/player:buyCard", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        var actualGame = gameRepository.findById(gameId).orElseThrow();

        Player turnPlayer = actualGame.getTurnPlayer();
        Bank bank = actualGame.getBank();

        assertEquals(0,turnPlayer.getTotalCoins());
        assertEquals(List.of(new WheatField()),turnPlayer.getHandCard().getEstablishments());
        assertEquals(bank.getTotalCoin(),283);

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("玩家 player-a 花費 1 元 購買了 小麥田"))
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
