/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> solution = new ArrayList<>();

        MazeCell current = maze.getEndCell();

        while(current != maze.getStartCell()){
            solution.add(0, current);
            current = current.getParent();
        }

        solution.add(0, maze.getStartCell());
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        MazeCell current = maze.getStartCell();

        searchNeighbors(current);

        return getSolution();
    }

    public void searchNeighbors(MazeCell current){
        Stack<MazeCell> toVisit = new Stack<>();
        ArrayList<MazeCell> neighbors = new ArrayList<>();

        current.setExplored(true);

        //N
        if(maze.isValidCell(current.getRow() - 1, current.getCol())){
            neighbors.add(maze.getCell(current.getRow() - 1, current.getCol()));
        }
        //E
        if(maze.isValidCell(current.getRow(), current.getCol() + 1)){
            neighbors.add(maze.getCell(current.getRow(), current.getCol() + 1));
        }
        //S
        //if(maze.isValidCell(current.getRow() + 1, current.getCol())){
          //  neighbors.add(maze.getCell(current.getRow() + 1, current.getCol()));
        //}
        //W
        if(maze.isValidCell(current.getRow(), current.getCol() + 1)){
            neighbors.add(maze.getCell(current.getRow(), current.getCol() + 1));
        }

        for(int i = 0; i < neighbors.size(); i++){
            neighbors.get(i).setParent(current);
            if(maze.isValidCell(neighbors.get(i).getRow(), neighbors.get(i).getCol())){
                toVisit.push(neighbors.get(i));
            }
        }

        current = toVisit.pop();
        searchNeighbors(current);
    }


    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        ArrayList<MazeCell> solution = new ArrayList<>();

        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        //sol = ms.solveMazeBFS();
        //maze.printSolution(sol);
    }
}
