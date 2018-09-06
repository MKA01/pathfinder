
package pathfinder;


public class Point {

    private int cordX,cordY, upWeight, rightWeight;

    public Point(int cordX, int cordY, int upWeight, int rightWeight) {
        this.cordX = cordX;
        this.cordY = cordY;
        this.upWeight = upWeight;
        this.rightWeight = rightWeight;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public int getUpWeight() {
        return upWeight;
    }

    public int getRightWeight() {
        return rightWeight;
    }

    @Override
    public String toString() {
        return "(" + cordX + "," + cordY + ")";
    }
}
