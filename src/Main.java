import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the maze length");
        int n = scanner.nextInt();
        System.out.println("Input the maze width");
        int m = scanner.nextInt();
        System.out.println("Input the degree of freedom (between 0 and 100)");
        int degree = scanner.nextInt();

        Maze maze = new Maze(n, m);
        maze.generateMaze(degree);
        for (int i = 0; i < maze.getWalls().size(); i++) {
            Pair<Integer, Integer> p = maze.getWalls().get(i);
            System.out.println(maze.transformNrInCell(p.getT1()) + " -> " + maze.transformNrInCell(p.getT2()));
        }


    }
}