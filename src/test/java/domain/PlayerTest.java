package domain;

import domain.card.establishment.BusinessCenter;
import domain.card.establishment.Establishment;
import domain.card.establishment.WheatField;
import domain.card.landmark.AmusementPark;
import domain.card.landmark.Landmark;
import domain.exceptions.MachiKoroException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private Bank bank;
    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("m1wt9ILw");
        bank = new Bank();
        game = new Game(bank, Collections.singletonList(player), null);
        bank.payCoin(100);
        player.gainCoin(100);
    }


    @Test
    @DisplayName(
            """
            Given 玩家擁有100塊
            When  玩家決定建設小麥田
            Then  玩家剩下99塊，並且手牌擁有小麥田
            """)
    void buyCard() {
        //given
        var originalBalanceOfPlayer = player.getTotalCoins();
        var card = new WheatField();

        //when
        player.buyEstablishment(card, bank);

        //then
        assertThat(player.getTotalCoins()).isEqualTo(originalBalanceOfPlayer - card.getConstructionCost());
        assertThat(player.getHandCard(0)).isEqualTo(card);
    }

    @Test
    @DisplayName(
            """
            Given 玩家擁有尚未翻面的主題樂園
            When  玩家決定將主題樂園翻面
            Then  玩家擁有翻面的主題樂園
            """)
    void flipBackLandMark() {
        //given
        var originalBalanceOfPlayer = player.getTotalCoins();
        Landmark amusementPark = player.getLandMark("主題樂園");

        //when
        player.flipLandMark(amusementPark, bank);

        //then
        assertThat(player.getTotalCoins()).isEqualTo(originalBalanceOfPlayer - 16);
        assertTrue(player.getLandmark(2).isFlipped());
    }

    @Test
    @DisplayName(
            """
            Given 玩家的地標-主題樂園已經翻成正面
            When  玩家決定將主題樂園翻面
            Then  操作失敗，丟出例外
            """)
    void flipFrontLandMark() {
        //given
        var originalBalanceOfPlayer = player.getTotalCoins();
        Landmark amusementPark = player.getLandMark("主題樂園");
        amusementPark.flipped();

        //when
        MachiKoroException actualException = Assertions.assertThrows(MachiKoroException.class,
                () -> player.flipLandMark(amusementPark, bank));

        //then
        assertThat(player.getTotalCoins()).isEqualTo(originalBalanceOfPlayer);
        assertThat(actualException.getMessage()).isEqualTo("此地標已經翻面，無法再重新翻面");
    }


    @Test
    @DisplayName(
            """
            Given 玩家A沒有商業中心，並且有足夠的錢可以支付商業中心的建設費用
            When  玩家A決定蓋商業中心
            Then  玩家擁有商業中心建築物
            """)
    void givenPlayerNoHaveBusinessCenterWhenBuyTheSameCardThenSuccess() {
        //given
        var businessCenter = new BusinessCenter();
        List<Establishment> playerOriginalOwnedEstablishment = player.getEstablishments();
        Assertions.assertFalse(playerOriginalOwnedEstablishment.contains(businessCenter));

        //when
        player.buyEstablishment(businessCenter, bank);

        //then
        assertTrue(player.getEstablishments().contains(businessCenter));
    }

    @Test
    @DisplayName(
            """
            Given 玩家A有商業中心
            When  玩家A決定蓋商業中心
            Then  系統拒絕此動作，只能擁有一座商業中心
            """)
    void givenPlayerHaveBusinessCenterWhenBuyTheSameCardThenReject() {
        //given
        var businessCenter = new BusinessCenter();
        player.addCardToHandCard(businessCenter);
        List<Establishment> playerOriginalOwnedEstablishment = player.getEstablishments();
        assertTrue(playerOriginalOwnedEstablishment.contains(businessCenter));

        //when
        MachiKoroException actualException = Assertions.assertThrows(MachiKoroException.class,
                () -> player.buyEstablishment(businessCenter, bank));

        //then
        List<Establishment> playerOwnedEstablishment = player.getEstablishments();
        long businessCenterCount = playerOwnedEstablishment
                .stream()
                .filter(card -> card.equals(businessCenter))
                .count();

        Assertions.assertEquals(businessCenterCount, 1);
        assertThat(actualException.getMessage()).isEqualTo("您已擁有此重要建築，不得重複建造");
    }
}
