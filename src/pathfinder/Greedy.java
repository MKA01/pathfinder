
package pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    private int[][] rightWeight,upWeight;
    private int targetX,targetY,partialWeight,currentX,currentY,totalWeight;
    private List<Point> path = new ArrayList();
    private Point point;

    public Greedy(int[][] upWeight, int[][] rightWeight, int targetX, int targetY, int currentX, int currentY) {
        this.rightWeight = rightWeight;
        this.upWeight = upWeight;
        this.targetX = targetX;
        this.targetY = targetY;
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public List<Point> getPath() {
        return path;
    }



    public void designate(){
        path.add(point = new Point(0,0,-1,-1));
        while(currentX != targetX || currentY != targetY){
            if(currentX == targetX){
                partialWeight = upWeight[currentY][currentX];
                currentY++;
            }else if (currentY == targetY){
                partialWeight = rightWeight[currentY][currentX];
                currentX++;
            }else{
                partialWeight = rightWeight[currentY][currentX];
                if(partialWeight > upWeight[currentY][currentX]){
                    partialWeight = upWeight[currentY][currentX];
                    path.add(point = new Point(currentX,currentY,-1,-1));
                    currentY++;
                }else
                    currentX++;
            }
            path.add(point = new Point(currentX,currentY,-1,-1));
            totalWeight = totalWeight + partialWeight;
        }
        System.out.println("Greedy algorithm results: ");
        System.out.println("    Target cords: (" + currentX + "," + currentY +")");
        System.out.println("    Total weight: " + totalWeight);
        System.out.println("    Path taken:   " + path.toString());
    }
}
