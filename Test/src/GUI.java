
import java.io.File;

public class GUI {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\hkthe\\Documents\\GitHub\\CS2013\\StackMazeSolver\\maze1.txt");
        MapSolver solToMap = new MapSolver(file);
        solToMap.pathPrinter();
    }
}