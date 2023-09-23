package spring.repositories.data;

import domain.Bank;
import domain.Game;
import domain.Player;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static domain.StreamUtils.mapToList;

@Builder
@Getter
public class GameData {
    protected String id;

    protected int bankCoins;
    protected List<PlayerData> players;
    protected int currentDicePoint;
    protected String turnPlayerId;
    protected MarketplaceData marketplace;

    public Game toDomain() {
        List<Player> players = mapToList(this.players, PlayerData::toDomain);
        Player turnPlayer = players.stream().filter(player -> player.getId().equals(turnPlayerId)).findFirst().orElseThrow();
        return Game.builder()
                .id(id)
                .bank(new Bank(bankCoins))
                .players(players)
                .currentDicePoint(currentDicePoint)
                .turnPlayer(turnPlayer)
                .marketplace(marketplace.toDomain())
                .build();
    }

    public static GameData toData(Game game) {
        return GameData.builder()
                .id(game.getId())
                .bankCoins(game.getBank().getTotalCoin())
                .players(mapToList(game.getPlayers(), PlayerData::toData))
                .currentDicePoint(game.getCurrentDicePoint())
                .turnPlayerId(game.getTurnPlayer().getId())
                .marketplace(MarketplaceData.toData(game.getMarketplace()))
                .build();
    }


}
