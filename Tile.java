import java.awt.*;

public class Tile {
    private int row;
    private int col;
    private Color color;
    private int height = 50;
    private int width = 50;

    public Tile(int row, int col, Color color){
        this.row          = row;
        this.col          = col;
        this.color        = color;
    }

    public void graphics(Graphics graphics){
        int tileRow = this.row*height;
        int tileCol = this.col*width;

        graphics.setColor(this.color);
        graphics.fillRect(tileCol,tileRow,width,height);
    }
}
