import java.util.List;

public class Utils {
    // pos -> current position
    // grid -> objects in the world (checker board)
    // path -> append the new move to the front of the list
    public static boolean moveOnce(Point pos, PathingMain.GridValues[][] grid, List<Point> path)
    {
        Point rightN = new Point(pos.x +1, pos.y ); // right neighbor

        //test if this is a valid grid cell
        if (withinBounds(rightN, grid)  &&
                grid[rightN.y][rightN.x] != PathingMain.GridValues.OBSTACLE &&
                grid[rightN.y][rightN.x] != PathingMain.GridValues.SEARCHED) {
            //check if my right neighbor is the goal
            if (grid[rightN.y][rightN.x] == PathingMain.GridValues.GOAL) {
                path.add(0, rightN);
                return true;
            }
            grid[rightN.y][rightN.x] = PathingMain.GridValues.SEARCHED;
        }
        return false;
    }

    public static boolean moveFive(Point pos, PathingMain.GridValues[][] grid, List<Point> path) {
        for (int i = 0; i < 5; i++) {
            if (grid[pos.x][pos.y + i] != PathingMain.GridValues.GOAL &&
                    grid[pos.x][pos.y + i] != PathingMain.GridValues.SEARCHED) {
                grid[pos.x][pos.y + i] = PathingMain.GridValues.SEARCHED;
                path.add(new Point(pos.y + i, pos.x)); // NOTE that the x, y are flipped here
            }
        }
        return false;
    }


    private static boolean withinBounds(Point p, PathingMain.GridValues[][] grid)
    {
        return p.y >= 0 && p.y < grid.length &&
                p.x >= 0 && p.x < grid[0].length;
    }
}
