
import java.util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class Search {
    // TODO
    public static boolean WithinBounds(Point p, PathingMain.GridValues[][] grid){
        return p.y >= 0 && p.y < grid.length &&
                p.x >= 0 && p.x < grid[0].length;
    }
    public static boolean IsValidPoint(Point p , PathingMain.GridValues[][] grid){
        if (!WithinBounds(p, grid)||grid[p.y][p.x] == PathingMain.GridValues.OBSTACLE || grid[p.y][p.x] == PathingMain.GridValues.SEARCHED
                ){
            return false;
        }
        else{
            return true;
        }
    }
    public static List<Point> neighbours(Point p){
        Point right = new Point(p.x+1,p.y);
        Point down = new Point(p.x,p.y+1);
        Point left = new Point(p.x-1,p.y);
        Point up = new Point(p.x,p.y-1);
        ArrayList<Point> Arr = new ArrayList<>();
        Arr.add(right);
        Arr.add(down);
        Arr.add(left);
        Arr.add(up);
        return Arr;
    }
    public static boolean BFS(Point pos, PathingMain.GridValues[][] grid, List<Point> path) {
        Queue<Point> Q = new LinkedList<>();
        HashMap<Point, Point> ParentMap = new HashMap<>();
        ParentMap.put(pos,null);
        Q.add(pos);
        while(!Q.isEmpty()){
           Point current =  Q.poll();
           if ( IsValidPoint(current, grid)){
               if(grid[current.y][current.x]== PathingMain.GridValues.GOAL){
                   current = ParentMap.get(current);
                   while(ParentMap.get(current)!= null){
                       path.add(current);
                       current=ParentMap.get(current);

                   }
                   Collections.reverse(path);
                   return true;
               }
               else{
                   grid[current.y][current.x] = PathingMain.GridValues.SEARCHED;
                   for (Point p: neighbours(current)){
                       if (IsValidPoint(p,grid)){
                           Q.add(p);
                           ParentMap.put(p,current);
                       }

                   }
               }
           }
        }
        return false;


    }


    // TODO
    public static boolean DFS(Point pos, PathingMain.GridValues[][] grid, List<Point> path) {

        Stack<Point> S = new Stack<>();
        HashMap<Point, Point> ParentMap = new HashMap<>();
        ParentMap.put(pos,null);
        S.add(pos);
        while(!S.isEmpty()){
            Point current =  S.pop();
            if ( IsValidPoint(current, grid)){
                if(grid[current.y][current.x]== PathingMain.GridValues.GOAL){
                    current = ParentMap.get(current);
                    while(ParentMap.get(current)!= null){
                        path.add(current);
                        current=ParentMap.get(current);

                    }
                    Collections.reverse(path);
                    return true;
                }
                else{
                    grid[current.y][current.x] = PathingMain.GridValues.SEARCHED;
                    for (Point p: neighbours(current)){
                        if (IsValidPoint(p,grid)){
                            S.add(p);
                            ParentMap.put(p,current);
                        }

                    }
                }
            }
        }
        return false;

    }
}


