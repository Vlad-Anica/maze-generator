import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Maze {
    int n, m;
    int freedomDegree;
    ArrayList<Pair<Integer, Integer>> walls;
    ArrayList<ArrayList<Integer>> graph;

    public Maze(int n, int m) {
        this.n = n;
        this.m = m;
        this.freedomDegree = 0;
        wallEverything();
     }

    public int transformCellInNr(int l, int c) {
        return l * m + c;
    }

    public Pair<Integer, Integer> transformNrInCell(int nr) {
        int l = nr / this.m;
        int c = nr % this.m;

        return new Pair<>(l, c);
    }

    /**
     * resets the maze by placing walls between every room
     */
    public void wallEverything() {
        this.walls = new ArrayList<>();
        graph = new ArrayList<>();
        for (int l = 0; l < n - 1; l++) {
            for (int c = 0; c < m - 1; c++) {
                walls.add(new Pair<>(transformCellInNr(l, c), transformCellInNr(l + 1, c)));
                walls.add(new Pair<>(transformCellInNr(l, c), transformCellInNr(l, c + 1)));
            }
            walls.add(new Pair<>(transformCellInNr(l, m - 1), transformCellInNr(l + 1, m - 1)));

        }
        for (int c = 0; c < m - 1; c++) {
            walls.add(new Pair<>(transformCellInNr(n - 1, c), transformCellInNr(n - 1, c + 1)));
        }
        for (int i = 0; i < n * m; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public ArrayList<Pair<Integer, Integer>> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Pair<Integer, Integer>> walls) {
        this.walls = walls;
    }

    /**
     * generates maze based on what freedom degree it gets as a parameter
     * @param freedomDegree between 0 and 100
     */
    public void generateMaze(int freedomDegree) {
        wallEverything();
        int extraDeletions = (2 * n * m + 1 - n - m - n * m) * freedomDegree / 100;
        ArrayList<Pair<Integer, Integer>> newWalls = new ArrayList<>();

        Collections.shuffle(walls);
        DisjointSet cells = new DisjointSet(n * m);
        for (int i = 0; i < walls.size(); i++) {
            if (cells.find(walls.get(i).getT1()) != cells.find(walls.get(i).getT2())) {
                graph.get(walls.get(i).getT1()).add(walls.get(i).getT2());
                graph.get(walls.get(i).getT2()).add(walls.get(i).getT1());
                cells.union(walls.get(i).getT1(), walls.get(i).getT2());
            } else if (extraDeletions > 0) {
                extraDeletions--;
                graph.get(walls.get(i).getT1()).add(walls.get(i).getT2());
                graph.get(walls.get(i).getT2()).add(walls.get(i).getT1());
                cells.union(walls.get(i).getT1(), walls.get(i).getT2());
            } else {
                newWalls.add(new Pair<>(walls.get(i).getT1(), walls.get(i).getT2()));
            }
        }

        walls = newWalls;
    }
///I have tried to create a visualization in the console, but the "|" and "_" are not equal in length in all editors, so it only looks nice on certain apps.
    public String visualize() {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += "_";
        }
        for (int l = 0; l < n - 1; l++) {
            res += "\n";
            res += "|";
            for (int c = 0; c < m - 1; c++) {
                if (!graph.get(transformCellInNr(l, c)).contains(transformCellInNr(l + 1, c))) {
                    res += "_";
                } else {
                    res += " ";
                }
                if (!graph.get(transformCellInNr(l, c)).contains(transformCellInNr(l, c + 1))) {
                    res += "|";
                }
            }
            if (!graph.get(transformCellInNr(l, m - 1)).contains(transformCellInNr(l + 1, m - 1))) {
                res += "_";
            } else {
                res += " ";
            }
            res += "|";
        }
        res += "\n";
        res += "|";
        for (int c = 0; c < m - 1; c++) {
            res += "_";
            if (!graph.get(transformCellInNr(n - 1, c)).contains(transformCellInNr(n - 1, c + 1))) {
                res += "|";
            }
        }
        res += "|";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        return n == maze.n && m == maze.m && Objects.equals(walls, maze.walls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, m, walls);
    }
}
