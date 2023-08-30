package spring.repositories.data;

import domain.Bank;
import domain.Game;
import domain.Marketplace;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class GameDataTest {

    final String ID = "gameId";

    @Test
    void testToData() {
        var player1 = Player.builder().id("playerId1").build();
        var player2 = Player.builder().id("playerId2").build();
        var player3 = Player.builder().id("playerId3").build();
        var player4 = Player.builder().id("playerId4").build();

        List<Player> players = List.of(player1, player2, player3, player4);

        Game game = Game.builder()
                .id(ID)
                .bank(new Bank())
                .players(players)
                .currentDicePoint(1)
                .turnPlayer(player2)
                .marketplace(new Marketplace())
                .build();
        game.setUp();

        GameData gameData = GameData.toData(game);

        assertThat(gameData.getId()).isEqualTo(game.getId());
        assertThat(gameData.getPlayers().size()).isEqualTo(game.getPlayers().size());
        assertThat(gameData.getTurnPlayer().getId()).isEqualTo(game.getTurnPlayer().getId());
    }


    @Test
    void testToDomain() {

        var player1 = PlayerData.builder()
                .id("player1")
                .handCard(HandCardData.builder()
                        .establishments(List.of(new EstablishmentData(EstablishmentData.Type.APPLE_ORCHARD)))
                        .landmarks(List.of(new LandmarkData(false, LandmarkData.Type.AMUSEMENT_PARK)))
                        .build())
                .build();
        var player2 = PlayerData.builder()
                .id("player2")
                .handCard(HandCardData.builder().establishments(List.of(new EstablishmentData(EstablishmentData.Type.APPLE_ORCHARD)))
                        .landmarks(List.of(new LandmarkData(false, LandmarkData.Type.AMUSEMENT_PARK)))
                        .build())
                .build();
        var player3 = PlayerData.builder()
                .id("player3")
                .handCard(HandCardData.builder().establishments(List.of(new EstablishmentData(EstablishmentData.Type.APPLE_ORCHARD)))
                        .landmarks(List.of(new LandmarkData(false, LandmarkData.Type.AMUSEMENT_PARK)))
                        .build())
                .build();
        var player4 = PlayerData.builder()
                .id("player4")
                .handCard(HandCardData.builder().establishments(List.of(new EstablishmentData(EstablishmentData.Type.APPLE_ORCHARD)))
                        .landmarks(List.of(new LandmarkData(false, LandmarkData.Type.AMUSEMENT_PARK)))
                        .build())
                .build();
        GameData gameData = GameData.builder()
                .id(ID)
                .bank(BankData.builder().coins(300).build())
                .players(List.of(player1, player2, player3, player4))
                .currentDicePoint(1)
                .turnPlayer(player3)
                .marketplace(MarketplaceData.builder().establishments(List.of(new EstablishmentData(EstablishmentData.Type.APPLE_ORCHARD))).build())
                .build();

        Game game = gameData.toDomain();

        assertThat(game.getId()).isEqualTo(gameData.getId());
        assertThat(game.getPlayers().size()).isEqualTo(gameData.getPlayers().size());
        assertThat(game.getTurnPlayer().getId()).isEqualTo(gameData.getTurnPlayer().getId());
    }
}
