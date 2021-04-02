import java.awt.*;

public class Monster {
    private int AP;
    private int DP;
    private int MP;
    private int S;
    private String name;
    private int height = 50;
    private int width = 50;
    private Color color;

    public Monster(int AP,int DP,int MP,int S,String name,Color color){
        this.AP = AP;
        this.DP = DP;
        this.MP = MP;
        this.S = S;
        this.name = name;
        this.color = color;
    }
    public void graphics(Graphics graphics,int row,int col){
        int tileRow = row*height;
        int tileCol = col*width;
        int rowOffset = 30;
        int colOffset = 15;

        graphics.setColor(this.color);
        graphics.fillRect(tileCol,tileRow,width,height);
        graphics.setColor(Color.BLACK);
        graphics.drawString(name,tileCol+colOffset,tileRow+rowOffset);
    }

    public Color getColor() {
        return color;
    }

    public boolean isMoveValid(int moveRow,int moveCol,int initialRow,int initialCol){
        int rowCoefficient =  Math.abs(moveRow-initialRow);
        int colCoefficient =  Math.abs(moveCol-initialCol);

        return rowCoefficient == 0 && colCoefficient <= this.S || rowCoefficient <= this.S && colCoefficient == 0;
    }
}