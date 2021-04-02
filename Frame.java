import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Frame extends JFrame implements MouseListener {
    private Player playerOne = new Player();
    private Player playerTwo = new Player();
    private Monster[] playerOneStart = new Monster[5];
    private Monster[] playerTwoStart = new Monster[5];
    private Monster[][] board = new Monster[12][12];
    private int startingPlacementTurn = 0;
    private Monster selectedMonster;
    private int colorStartHelper=0;
    private int initialRow;
    private int initialCol;


    public Frame(){
        generate(playerOneStart);
        generate(playerTwoStart);
        firstPlayerTurn();
        this.setSize(700,700);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    public void paint(Graphics g){
        super.paintComponents(g);
        for(int i = 0;i<12;i++){
            for(int j=0;j<12;j++){
                if(i%2== 1&&j%2==0||i%2==0&&j%2==1){
                    Tile tile = new Tile(i,j,Color.GREEN);
                    tile.graphics(g);
                } else {
                    Tile tile = new Tile(i,j,Color.RED);
                    tile.graphics(g);
                }
            }
        }

        if(startingPlacementTurn<10) {
            if (startingPlacementTurn % 2 == 0) {
                for (int i = 0; i < 5; i++) {
                    Monster monster = getMonsterForRender(i, playerOneStart);
                    if (monster != null) {
                        monster.graphics(g, 12, i);
                    }
                }
                g.setColor(Color.BLACK);
                g.drawString("PlayerOne Monsters", 75, 660);
            } else {
                for (int i = 0; i < 5; i++) {
                    Monster monster = getMonsterForRender(i, playerTwoStart);
                    if (monster != null) {
                        monster.graphics(g, 12, i);
                    }
                }
                g.drawString("PlayerTwo Monsters", 75, 660);
            }
        }

        for(int i = 0;i<12;i++){
            for(int j = 0 ; j<12;j++){
                if(board[i][j] !=null) {
                    Monster monster = getMonsterBoard(i, j);
                    monster.graphics(g,i,j);
                }
            }
        }

        if(startingPlacementTurn == 10){
            if(playerOne.isMyTurn()){
                g.drawString("PlayerOne Turn", 75, 660);
            } else if(playerTwo.isMyTurn()){
                g.drawString("PlayerTwo Turn", 75, 660);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardCoordinates(e.getY());
        int col = this.getBoardCoordinates(e.getX());

        if(startingPlacementTurn<10) {
        if(this.selectedMonster != null){
            if(row<12&&col<12){
                if(row<6&& startingPlacementTurn%2==0) {
                    this.board[row][col] = this.selectedMonster;
                    this.selectedMonster = null;
                    this.repaint();
                    startingPlacementTurn++;
                    return;
                } else if(row>=6 && startingPlacementTurn%2==1){
                    this.board[row][col] = this.selectedMonster;
                    this.selectedMonster = null;
                    this.repaint();
                    startingPlacementTurn++;
                    return;
                }
            }
        }

            if (startingPlacementTurn % 2 == 0) {
                this.selectedMonster = getMonster(row, col, playerOneStart);
                playerOneStart[col] = null;
            } else {
                this.selectedMonster = getMonster(row, col, playerTwoStart);
                playerTwoStart[col] = null;
            }
        }
        if(startingPlacementTurn == 10){

            if(selectedMonster != null){
                Monster monster = selectedMonster;
                if(monster.isMoveValid(row,col,initialRow,initialCol)){
                    this.board[row][col] = selectedMonster;
                    this.board[initialRow][initialCol] = null;
                    this.selectedMonster = null;
                    return;
                }
                if(monster.getColor().equals(Color.YELLOW)){
                    playerOne.setMyTurn(true);
                    playerTwo.setMyTurn(false);
                }
                if(monster.getColor().equals(Color.BLUE)){
                    playerOne.setMyTurn(false);
                    playerTwo.setMyTurn(true);
                }
            }

            Monster monster = getMonsterBoard(row,col);

            if(monster != null && monster.getColor().equals(Color.BLUE) && playerOne.isMyTurn()){
               this.selectedMonster = monster;
               initialRow = row;
            } else if(monster != null && monster.getColor().equals(Color.YELLOW) && playerTwo.isMyTurn()){
                this.selectedMonster = monster;
                initialCol = col;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void generate(Monster[] monstersStart){
        Color color;
        if(colorStartHelper == 0){
            color = Color.BLUE;
            colorStartHelper++;
        } else {
            color = Color.YELLOW;
        }
        for (int i = 0;i<5;i++){
            int min = 1;
            int max = 6;
            int random = (int) Math.floor(Math.random()*(max - min +1)+ min);
            switch (random){
                case 1:
                    Monster drunkenKnight = new Monster(5,5,5,5,"DK",color);
                    monstersStart[i] = drunkenKnight;
                    break;
                case 2:
                    Monster sofisticatedSam = new Monster(10,5,4,1,"SS",color);
                    monstersStart[i] = sofisticatedSam;
                    break;
                case 3:
                    Monster sandTurtle = new Monster(5,10,1,4,"ST",color);
                    monstersStart[i] = sandTurtle;
                    break;
                case 4:
                    Monster magicCat = new Monster(8,5,10,1,"MC",color);
                    monstersStart[i] = magicCat;
                    break;
                case 5:
                    Monster recklessCanibal = new Monster(4,6,8,10,"RC",color);
                    monstersStart[i] = recklessCanibal;
                    break;
                case 6:
                    Monster dogEatingBug = new Monster(10,2,8,5,"DEB",color);
                    monstersStart[i] = dogEatingBug;
                    break;
            }
        }
    }

    private Monster getMonsterForRender(int index, Monster[] monsters){
        if(monsters[index] !=null){
            return monsters[index];
        }
        return null;
    }

    private Monster getMonster(int row,int col,Monster[] monsters){
        if(row == 12 && monsters[col] !=null){
            return monsters[col];
        }
        return null;
    }

    private Monster getMonsterBoard(int row,int col){
        if(board[row][col] != null){
            return board[row][col];
        }
        return null;
    }

    private int getBoardCoordinates(int coordinates){
        return  coordinates/50;
    }

    private void firstPlayerTurn() {
        int min = 1;
        int max = 2;
        int random = (int) Math.floor(Math.random()*(max - min +1)+ min);
        if(random == 1){
            playerOne.setMyTurn(true);
            playerTwo.setMyTurn(false);
        } else {
            playerTwo.setMyTurn(true);
            playerOne.setMyTurn(false);
        }
    }
}