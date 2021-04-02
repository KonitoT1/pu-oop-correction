public class Player {
    private int monsters = 0;
    private int MP = 10;
    private boolean isMyTurn;

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getMonsters() {
        return monsters;
    }

    public void setMonsters(int monsters) {
        this.monsters = monsters;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }
}