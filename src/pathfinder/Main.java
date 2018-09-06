
package pathfinder;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Main extends JFrame{

    static int[][] upWeightForGreedy;
    static int[][] rightWeightForGreedy;
    static int[][] upWeightForDynamic;
    static int[][] rightWeightForDynamic;
    static Greedy greedy;
    static Dynamic dynamic;
    static List<Point> dynamicPath;
    static List<Point> greedyPath;

    public Main(){
        setTitle("Visualisation");
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g){
        for(int i = 0; i < dynamicPath.size() - 1; i++){
            g.drawLine((getDynamicX(i)*25)+25, -(getDynamicY(i)*25)+175, (getDynamicX(i+1)*25)+25 , -(getDynamicY(i+1)*25)+175);
            g.drawOval((getDynamicX(i)*25)+23, -(getDynamicY(i)*25)+173, 4, 4);
        }
        g.drawOval((getDynamicX(dynamicPath.size()-1)*25)+23, -(getDynamicY(dynamicPath.size()-1)*25)+173, 4, 4);

        for(int i = 0; i < greedyPath.size() - 1; i++){
            g.drawLine((getGreedyX(i)*25)+150, -(getGreedyY(i)*25)+175, (getGreedyX(i+1)*25)+150 , -(getGreedyY(i+1)*25)+175);
            g.drawOval((getGreedyX(i)*25)+148, -(getGreedyY(i)*25)+173, 4, 4);
        }
        g.drawOval((getGreedyX(greedyPath.size()-1)*25)+148, -(getGreedyY(greedyPath.size()-1)*25)+173, 4, 4);
    }

    public int getDynamicX(int index){
        return dynamicPath.get(index).getCordX();
    }

    public int getDynamicY(int index){
        return dynamicPath.get(index).getCordY();
    }

    public int getGreedyX(int index){
        return greedyPath.get(index).getCordX();
    }

    public int getGreedyY(int index){
        return greedyPath.get(index).getCordY();
    }

    public static void setArray(){
        for (int x = 0; x <= 4; x++){
            for (int y = 0; y <= 4; y++){
                if (y == 4 && x == 4)
                    dynamic.setPointArray(x, y, -1, -1);
                else if (y == 4)
                    dynamic.setPointArray(x, y, -1, rightWeightForDynamic[y][x]);
                else if (x == 4)
                    dynamic.setPointArray(x, y, upWeightForDynamic[x][y], -1);
                else
                    dynamic.setPointArray(x, y, upWeightForDynamic[x][y], rightWeightForDynamic[y][x]);
            }
        }
    }

    public static void startGreedy(int startX, int startY){
        greedy.setTargetX(startX);
        greedy.setTargetY(startY);
        greedy.designate();
    }

    public static void startDynamic(int startX, int startY){
        setArray();
        //dynamic.getPointArrayAll();
        dynamic.setTargetX(startX);
        dynamic.setTargetY(startY);
        dynamic.designate();
    }

    public static void main(String[] args) {

        upWeightForGreedy = new int[][] { {6,4,3,3,2} , {10,4,2,3,2}, {20,8,3,2,3}, {25,10,4,3,2} };
        rightWeightForGreedy = new int[][] { {4,1,2,3}, {3,2,2,2}, {2,3,2,1}, {1,2,2,2}, {3,3,3,2} };
        upWeightForDynamic = new int[][] { {6,10,20,25}, {4,4,8,10}, {3,2,3,4}, {3,3,2,3}, {2,2,3,2} };
        rightWeightForDynamic = new int[][] { {4,1,2,3} , {3,2,2,2}, {2,3,2,1}, {1,2,2,2}, {3,3,3,2} };
        greedy = new Greedy(upWeightForGreedy, rightWeightForGreedy, 0, 0, 0, 0);
        dynamic = new Dynamic(0,0);
        dynamicPath = new ArrayList();

        startDynamic(4,4);
        startGreedy(4,4);

        greedyPath  = new ArrayList<>(greedy.getPath());

        for(int i = 0; i < dynamic.getPath().size() ; i++)
            dynamicPath.add(dynamic.getPath().get(i));

        Main main = new Main();
    }
}
