package domain;

import java.util.List;

public class Game {
    private final Bank bank;
    private final List<Player> players;
    private Integer dices;
    private Integer currentDicePoint;
    private Player turnPlayer;
    private final Marketplace marketplace;

    public Game(Bank bank, List<Player> players, List<Dice> dices, Marketplace marketplace) {
        this.bank = bank;
        this.players = players;
//        this.dices = dices;
        this.marketplace = marketplace;
    }

    public void distributeResources(List<Integer> twoDicePoint) {
        this.setCurrentDicePoint(twoDicePoint);
        this.getPlayers().forEach(player -> {
            player.ownedEstablishmentTakeEffect(this);
            if (player.getOwnedLandmark().get(1).isCardsideTurnfront()) {
                player.getOwnedLandmark().get(1).takeEffect(this);
            }
        });
    }

    public Integer getCurrentDicePoint() {
        return currentDicePoint;
    }

    public Player getTurnPlayer() {
        return turnPlayer;
    }

    public void setTurnPlayer(Player turnPlayer) {
        this.turnPlayer = turnPlayer;
    }

    public boolean isTurnPlayer(Player player) {
        return this.getTurnPlayer().equals(player);
    }

    public void setCurrentDicePoint(List<Integer> twoDicePoint) {
        int sumDice = twoDicePoint.stream().reduce(Integer::sum).orElse(0);
        this.currentDicePoint = sumDice;
    }

    public Bank getBank() {
        return bank;
    }
    public void setChooseNumberOfDice(int dices) {
        this.dices = dices;
    }
    public void setUp() {

    }

    public void start() {

    }

    public void startNewTurn() {

    }

    public void end() {

    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getOtherPlayers(Player targetPlayer) {
        return getPlayers()
                .stream()
                .filter(player -> !player.equals(targetPlayer))
                .toList();
    }

}
