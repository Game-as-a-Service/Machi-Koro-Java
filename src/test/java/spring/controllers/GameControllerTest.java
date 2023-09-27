package spring.controllers;

import app.repositories.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;
import domain.card.establishment.BusinessCenter;
import domain.card.establishment.WheatField;
import domain.card.landmark.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import spring.presenter.viewmodel.CreateGameViewModel;
import spring.requests.CreateGameRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void rollDiceApiTest() throws Exception {
        Player a = Player.builder().name("playerA").id("123").build();
        Player b = Player.builder().name("playerB").id("124").build();
        Player c = Player.builder().name("playerC").id("125").build();
        Player d = Player.builder().name("playerD").id("126").build();

        Game game = givenGameStarted(a, b, c, d);

        GameController.RollDiceRequest request = new GameController.RollDiceRequest("123", false);

        var result = mockMvc.perform(post("/api/games/{gameId}/roll-dice", game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        var actualGame = gameRepository.findById(game.getId()).orElseThrow();

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("dicePoint").value(actualGame.getCurrentDicePoint()))
                .andReturn();
    }

    @Test
    @DisplayName(
            """
                    Given 玩家 PlayerA 1 coin 0個建築物,PlayerB,PlayerC,PlayerD，銀行有 282 coins，目前輪到PlayerA要決定建設建築物或Pass
                    When  玩家 PlayerA 決定建設小麥田
                    Then  玩家A手牌擁有小麥田,0 coin
                          TurnPlayer輪到PlayerB
                          銀行有 283 coins           
                    """)
    void playerABuyWheatFieldWithEnoughCoinsTest() throws Exception {
        Player playerA = Player.builder().name("playerA").id("123").coins(1).build();
        Player playerB = Player.builder().name("playerB").id("124").build();
        Player playerC = Player.builder().name("playerC").id("125").build();
        Player playerD = Player.builder().name("playerD").id("126").build();

        Game game = givenGameStarted(playerA, playerB, playerC, playerD);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("123", "WheatField");

        mockMvc.perform(post("/api/games/{gameId}/player:buyCard", game.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("玩家 123 花費了 1 元 建造了 小麥田"));

        var actualGame = findGame(game.getId());
        Player actualPlayerA = actualGame.getPlayer("123");
        Player turnPlayer = actualGame.getTurnPlayer();
        Bank bank = actualGame.getBank();

        assertEquals(0, actualPlayerA.getTotalCoins());
        assertEquals(List.of(new WheatField()), actualPlayerA.getEstablishments());
        assertEquals("124", turnPlayer.getId());
        assertEquals(283, bank.getTotalCoin());
        assertEquals(9, actualGame.getMarketplace().getEstablishmentMap().get("WheatField").size());
    }

    @Test
    @DisplayName(
            """
                    Given 玩家 PlayerA 0 coin 0個建築物,PlayerB,PlayerC,PlayerD，銀行有 282 coins，目前輪到PlayerA要決定建設建築物或Pass
                    When  玩家 PlayerA 決定建設小麥田
                    Then  玩家A購買失敗               
                          TurnPlayer還是PlayerA
                          銀行有 282 coins
                    """)
    void playerABuyWheatFieldWithoutEnoughCoinsTest() throws Exception {
        Player playerA = Player.builder().name("playerA").id("123").build();
        Player playerB = Player.builder().name("playerB").id("124").build();
        Player playerC = Player.builder().name("playerC").id("125").build();
        Player playerD = Player.builder().name("playerD").id("126").build();


        Game game = givenGameStarted(playerA, playerB, playerC, playerD);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("123", "WheatField");

        mockMvc.perform(post("/api/games/{gameId}/player:buyCard", game.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());


        var actualGame = findGame(game.getId());
        Player actualPlayerA = actualGame.getPlayer("123");
        Player turnPlayer = actualGame.getTurnPlayer();
        Bank bank = actualGame.getBank();


        assertEquals(0, actualPlayerA.getTotalCoins());
        assertEquals(Collections.emptyList(), actualPlayerA.getEstablishments());
        assertEquals("123", turnPlayer.getId());
        assertEquals(282, bank.getTotalCoin());
        assertEquals(10, actualGame.getMarketplace().getEstablishmentMap().get("WheatField").size());
    }

    @Test
    @DisplayName(
            """
                    Given 玩家 PlayerA 8 coins,1個商業中心,PlayerB,PlayerC,PlayerD，銀行有 282 coins，目前輪到PlayerA要決定建設建築物或Pass
                    When  玩家 PlayerA 決定建設商業中心
                    Then  購買失敗，玩家A 8 coin,手牌擁有商業中心       
                          TurnPlayer是PlayerA
                          銀行有 282 coins
                    """)
    void playerABuyBusinessCenterWithBusinessCenterTest() throws Exception {
        Player playerA = Player.builder()
                .name("playerA").id("123")
                .coins(8)
                .handCard(new HandCard(List.of(new BusinessCenter()), List.of(new TrainStation(), new AmusementPark(), new ShoppingMall(), new RadioTower())))
                .build();

        Player playerB = Player.builder().name("playerB").id("124").build();
        Player playerC = Player.builder().name("playerC").id("125").build();
        Player playerD = Player.builder().name("playerD").id("126").build();


        Game game = givenGameStarted(playerA, playerB, playerC, playerD);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("123", "BusinessCenter");

        mockMvc.perform(post("/api/games/{gameId}/player:buyCard", game.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        var actualGame = findGame(game.getId());
        Player actualPlayerA = actualGame.getPlayer("123");
        Player turnPlayer = actualGame.getTurnPlayer();
        Bank bank = actualGame.getBank();

        assertEquals(8, actualPlayerA.getTotalCoins());
        assertEquals("123", turnPlayer.getId());
        assertEquals(282, bank.getTotalCoin());
        assertEquals(4, actualGame.getMarketplace().getEstablishmentMap().get("BusinessCenter").size());
    }

    @Test
    @DisplayName(
            """
                    Given 玩家 PlayerA 4 coins 0個建築物,PlayerB,PlayerC,PlayerD，銀行有 282 coins，目前輪到PlayerA要決定建設建築物或Pass
                    When  玩家 PlayerA 決定建設火車站
                    Then  玩家A 0 coin,擁有的火車站已翻成正面             
                          TurnPlayer是PlayerB
                          銀行有 286 coins
                    """)
    void playerABuyTrainStationWithEnoughCoinsTest() throws Exception {
        Player playerA = Player.builder().name("playerA").id("123").coins(4).build();
        Player playerB = Player.builder().name("playerB").id("124").build();
        Player playerC = Player.builder().name("playerC").id("125").build();
        Player playerD = Player.builder().name("playerD").id("126").build();


        Game game = givenGameStarted(playerA, playerB, playerC, playerD);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("123", "火車站");

        mockMvc.perform(post("/api/games/{gameId}/player:flipLandMark", game.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("玩家 123 花費了 4 元 建造了 火車站"));


        var actualGame = findGame(game.getId());
        Player actualPlayerA = actualGame.getPlayer("123");
        Player turnPlayer = actualGame.getTurnPlayer();
        Bank bank = actualGame.getBank();

        assertEquals(0, actualPlayerA.getTotalCoins());
        assertTrue(actualPlayerA.getLandMark("火車站").isFlipped());
        assertEquals("124", turnPlayer.getId());
        assertEquals(286, bank.getTotalCoin());
    }

    @Test
    @DisplayName(
            """
                    Given 玩家 PlayerA 0 coins 0個建築物,PlayerB,PlayerC,PlayerD，銀行有 282 coins，目前輪到PlayerA要決定建設建築物或Pass
                    When  玩家 PlayerA 決定建設火車站
                    Then  玩家A 2 coin,擁有的火車站是背面          
                          TurnPlayer是PlayerA
                          銀行有 282 coins
                    """)
    void playerABuyTrainStationWithoutEnoughCoinsTest() throws Exception {
        Player playerA = Player.builder().name("playerA").id("123").coins(2).build();
        Player playerB = Player.builder().name("playerB").id("124").build();
        Player playerC = Player.builder().name("playerC").id("125").build();
        Player playerD = Player.builder().name("playerD").id("126").build();


        Game game = givenGameStarted(playerA, playerB, playerC, playerD);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("123", "火車站");

        mockMvc.perform(post("/api/games/{gameId}/player:flipLandMark", game.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        var actualGame = findGame(game.getId());
        Player actualPlayerA = actualGame.getPlayer("123");
        Player turnPlayer = actualGame.getTurnPlayer();
        Bank bank = actualGame.getBank();
        Landmark trainStation = turnPlayer.getHandCard().getLandmarks().stream().filter(landmark -> "火車站".equals(landmark.getName())).findFirst().orElseThrow();

        assertEquals(2, actualPlayerA.getTotalCoins());
        assertEquals("123", turnPlayer.getId());
        assertFalse(trainStation.isFlipped());
        assertEquals(282, bank.getTotalCoin());
    }


    @Test
    @DisplayName(
            """
                    Given 玩家 PlayerA 擁有 22 coins ,正面的火車站,主題樂園,購物中心以及反面的廣播電台，目前輪到PlayerA要決定建設建築物或Pass
                    When  玩家 PlayerA 決定建設廣播電台
                    Then  玩家 playerA 勝利
                    """)
    void playerABuyBusinessCenterWithEnoughCoinsTest() throws Exception {
        HandCard handCard = new HandCard(Collections.emptyList(), List.of(new TrainStation(true), new AmusementPark(true), new ShoppingMall(true), new RadioTower()));
        Player playerA = Player.builder().name("playerA").id("123").coins(22).handCard(handCard).build();
        Player playerB = Player.builder().name("playerB").id("124").build();
        Player playerC = Player.builder().name("playerC").id("125").build();
        Player playerD = Player.builder().name("playerD").id("126").build();


        Game game = givenGameStarted(playerA, playerB, playerC, playerD);

        GameController.BuyCardRequest request = new GameController.BuyCardRequest("123", "廣播電台");

        mockMvc.perform(post("/api/games/{gameId}/player:flipLandMark", game.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("玩家 123 勝利"));
    }

    @Test
    @DisplayName("""
            When 玩家 A 創建一局骰子街
            Then 骰子街創建成功
            """)
    void whenPlayerACreateGameThenSuccess() throws Exception {
        Player playerA = Player.builder().name("playerA").id("123").build();

        CreateGameRequest createGameRequest = new CreateGameRequest(playerA.getId(), playerA.getName());

        MvcResult result = mockMvc.perform(post("/api/games/createGame")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createGameRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("gameId").exists())
                .andReturn();

        CreateGameViewModel viewModel = objectMapper.readValue(result.getResponse().getContentAsString(), CreateGameViewModel.class);
        Game game = findGame(viewModel.getGameId());

        Assertions.assertNotNull(game.getPlayer(playerA.getId()));
        Player turnPlayer = game.getTurnPlayer();
        Assertions.assertEquals(turnPlayer, playerA);
    }


    private Game givenGameStarted(Player... player) {
        Game game = Game.builder()
                .id("createGameId")
                .bank(new Bank())
                .players(List.of(player))
                .currentDicePoint(3)
                .turnPlayer(player[0])
                .marketplace(new Marketplace())
                .build();
        return gameRepository.save(game);
    }

    private Game findGame(String gameId) {
        return gameRepository.findById(gameId).orElseThrow();
    }

}
