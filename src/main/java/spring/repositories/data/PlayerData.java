package spring.repositories.data;

import domain.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PlayerData {
    protected String id;
    protected String name;
    protected int coins;
    protected HandCardData handCard;

    public Player toDomain() {
        return Player.builder()
                .id(id)
                .name(name)
                .coins(coins)
                .handCard(handCard.toDomain())
                .build();
    }

    public static PlayerData toData(Player player) {
        return PlayerData.builder()
                .id(player.getId())
                .name(player.getName())
                .coins(player.getTotalCoin())
                .handCard(HandCardData.toData(player.getHandCard()))
                .build();

    }

}
