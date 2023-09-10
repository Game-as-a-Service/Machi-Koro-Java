package spring.repositories.data;

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
    protected BankData bank;
    protected List<PlayerData> players;
    protected int currentDicePoint;
    protected PlayerData turnPlayer;
    protected MarketplaceData marketplace;

    public Game toDomain() {
        List<Player> playersDomain = mapToList(players, PlayerData::toDomain);
        Player turnPlayerDomain = playersDomain.stream().filter(player -> player.getId().equals(turnPlayer.getId())).findFirst().orElseThrow();
        return Game.builder()
                .id(id)
                .bank(bank.toDomain())
                .players(playersDomain)
                .currentDicePoint(currentDicePoint)
                .turnPlayer(turnPlayerDomain)
                .marketplace(marketplace.toDomain())
                .build();
    }

    public static GameData toData(Game game) {
        return GameData.builder()
                .id(game.getId())
                .bank(BankData.toData(game.getBank()))
                .players(mapToList(game.getPlayers(), PlayerData::toData))
                .currentDicePoint(game.getCurrentDicePoint())
                .turnPlayer(PlayerData.toData(game.getTurnPlayer()))
                .marketplace(MarketplaceData.toData(game.getMarketplace()))
                .build();
    }


}
