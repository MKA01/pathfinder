
package pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Dynamic {

    private List<Integer> weights = new ArrayList();
    private List<Point> points = new ArrayList();
    private List<List<Point>> path = new ArrayList<List<Point>>();
    private Point[][] pointArray = new Point[5][5];
    private int targetX, targetY, currentX, currentY, lowestWeight, pointNumber;

    public Dynamic(int currentX, int currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void setPointArray(int x, int y, int upWeight, int rightWeight){
        pointArray[x][y] = new Point(x,y,upWeight,rightWeight);
    }

    public void getPointArrayAll(){
        for (int i = 0; i <= 4; i++){
            for(int j = 0; j <= 4; j++){
                System.out.print("x = " + pointArray[i][j].getCordX());
                System.out.print(" ; y = " + pointArray[i][j].getCordY());
                System.out.print(" ; upWeight = " + pointArray[i][j].getUpWeight());
                System.out.println(" ; rightWeight = " + pointArray[i][j].getRightWeight());
            }
            System.out.println(" ");
        }
    }

    public List<Point> getPath() {
        return path.get(pointNumber);
    }

    public void designate(){
        int weight;
        currentX = 0;
        currentY = 0;
        if(weights.isEmpty()){
            pointNumber = 0;
            weights.add(pointArray[currentX][currentY].getRightWeight());
            points.add(pointArray[currentX+1][currentY]);
            path.add(new ArrayList<>());
            path.get(0).add(pointArray[0][0]);
            path.get(0).add(points.get(0));
            weights.add(pointArray[currentX][currentY].getUpWeight());
            points.add(pointArray[currentX][currentY+1]);
            path.add(new ArrayList<>());
            path.get(1).add(pointArray[0][0]);
            path.get(1).add(points.get(1));
            lowestWeight = weights.get(0);
        }
        while(currentX != targetX || currentY != targetY){
            for(int i = 0; i < weights.size(); i++){
                if(lowestWeight > weights.get(i)){
                    lowestWeight = weights.get(i);
                    pointNumber = i;
                }
            }
            currentX = points.get(pointNumber).getCordX();
            currentY = points.get(pointNumber).getCordY();
            if(currentX == targetX){
                weight = weights.get(pointNumber);
                weights.set(pointNumber, (weight + pointArray[currentX][currentY].getUpWeight()));
                points.set(pointNumber, pointArray[currentX][currentY+1]);
                lowestWeight = weights.get(pointNumber);
                path.get(pointNumber).add(points.get(pointNumber));
            }else if(currentY == targetY){
                weight = weights.get(pointNumber);
                weights.set(pointNumber, (weight + pointArray[currentX][currentY].getRightWeight()));
                points.set(pointNumber, pointArray[currentX+1][currentY]);
                lowestWeight = weights.get(pointNumber);
                path.get(pointNumber).add(points.get(pointNumber));
            }else{
                weight = weights.get(pointNumber);
                if (points.get(pointNumber).getRightWeight() != -1 && points.get(pointNumber).getUpWeight() != -1){
                    weights.set(pointNumber, (weight + pointArray[currentX][currentY].getRightWeight()));
                    points.set(pointNumber, pointArray[currentX+1][currentY]);
                    path.add(new ArrayList<>(path.get(pointNumber)));
                    path.get(pointNumber).add(points.get(pointNumber));
                    lowestWeight = weights.get(pointNumber);
                    weights.add(weight + pointArray[currentX][currentY].getUpWeight());
                    points.add(pointArray[currentX][currentY+1]);
                    path.get(path.size() - 1).add(points.get(points.size() - 1));
                }
            }
            for(int i = 0; i < weights.size(); i++){
                if(lowestWeight > weights.get(i)){
                    lowestWeight = weights.get(i);
                    pointNumber = i;
                }
            }
            currentX = points.get(pointNumber).getCordX();
            currentY = points.get(pointNumber).getCordY();
        }
        for(int i = 0; i < weights.size(); i++){
            if(lowestWeight > weights.get(i)){
                lowestWeight = weights.get(i);
                pointNumber = i;
            }
        }
        System.out.println("Dynamic algorithm results: ");
        System.out.println("    Target cords: (" + currentX + "," + currentY +")");
        System.out.println("    Total weight: " + lowestWeight);
        System.out.println("    Path taken:   " + path.get(pointNumber).toString());
    }
}