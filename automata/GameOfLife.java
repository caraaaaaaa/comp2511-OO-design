/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
    // public boolean[][] cell = new boolean[10][10];
    public BooleanProperty[][] cell = new BooleanProperty[10][10];

    public GameOfLife() {
        // TODO At the start all cells are dead
        setCell();
    }

    public BooleanProperty cellProperty(int x, int y){
        return this.cell[x][y];
    }

    public void setCell() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // this.cell[i][j] = false;
                cell[i][j] = new SimpleBooleanProperty(false);
            }
        }
    }

    public void ensureAlive(int x, int y) {
        // TODO Set the cell at (x,y) as alive
        // this.cell[x][y] = true;
        this.cell[x][y].setValue(true);
    }

    public void ensureDead(int x, int y) {
        // TODO Set the cell at (x,y) as dead
        // this.cell[x][y] = false;
        this.cell[x][y].setValue(false);
    }

    public boolean isAlive(int x, int y) {
        // TODO Return true if the cell is alive
        // if (this.cell[x][y]==true) {
        if (this.cell[x][y].getValue()==true) {
            return true;
        } else {
            return false;
        }
        
    }

    

    public void tick() {
        boolean[][] temp = new boolean[10][10];
        // TODO Transition the game to the next generation.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (isAlive(i, j) && (numOfNeighbour(i, j) < 2)) {
                    temp[i][j] = false;
                } else if (isAlive(i, j) && (numOfNeighbour(i, j) > 3)) {
                    temp[i][j] = false;
                } else if (!isAlive(i, j) && (numOfNeighbour(i, j) == 3)) {
                    temp[i][j] = true;
                } else {
                    if (isAlive(i, j)) {
                        temp[i][j] = true;
                    } else {
                        temp[i][j] = false;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (temp[i][j]==true) {
                    ensureAlive(i, j);
                } else {
                    ensureDead(i, j);
                }
            }
        }

    }


    public int numOfNeighbour(int i, int j) {
        int num = 0;
        if (isAlive(i, (j-1+10)%10)) {
            num+=1;
        }
        if (isAlive(i, (j+1)%10)) {
            num++;
        }
        if (isAlive((i-1+10)%10, (j-1+10)%10)) {
            num++;
        }
        if (isAlive((i-1+10)%10, j)) {
            num++;
        }
        if (isAlive((i-1+10)%10, (j+1)%10)) {
            num++;
        }
        if (isAlive((i+1)%10, (j-1+10)%10)) {
            num++;
        }
        if (isAlive((i+1)%10, j)) {
            num++;
        }
        if (isAlive((i+1)%10, (j+1)%10)) {
            num++;
        }
        return num;
    }
}
