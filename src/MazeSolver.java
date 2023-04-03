/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam & Liliana Dhaliwal
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
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
        // Creates an ArrayList to hold the solution
        ArrayList<MazeCell> solution = new ArrayList<>();

        MazeCell current = maze.getEndCell();

        // Iterates through the solution path by moving from 'child' Cell to 'parent' Cell
        while(current != maze.getStartCell()){
            solution.add(0, current);
            current = current.getParent();
        }

        // Adds the starting Cell as the first cell in the solution
        solution.add(0, maze.getStartCell());

        // Returns the solution
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Sets the current MazeCell to be the StartCell
        MazeCell current = maze.getStartCell();
        current.setExplored(true);

        // Adds the current MazeCell to the Stack of Cells to visit
        Stack<MazeCell> toVisit = new Stack<>();
        toVisit.push(current);

        // Calls the recursive method
        searchNeighborsDFS(toVisit);

        // Returns the solution
        return getSolution();
    }

    public void searchNeighborsDFS(Stack<MazeCell> toVisit){
        // Checks the base cases and returns if they are true
        if(toVisit.empty()){
            return;
        }
        MazeCell current = toVisit.pop();
        if(current == maze.getEndCell()){
            return;
        }

        ArrayList<MazeCell> neighbors = new ArrayList<>();

        //  Checks if each neighbor cell is valid, and if so, adds it to the ArrayList neighbors
        if(maze.isValidCell(current.getRow() - 1, current.getCol())){
            neighbors.add(maze.getCell(current.getRow() - 1, current.getCol()));
        }
        if(maze.isValidCell(current.getRow(), current.getCol() + 1)){
            neighbors.add(maze.getCell(current.getRow(), current.getCol() + 1));
        }
        if(maze.isValidCell(current.getRow() + 1, current.getCol())){
            neighbors.add(maze.getCell(current.getRow() + 1, current.getCol()));
        }
        if(maze.isValidCell(current.getRow(), current.getCol() - 1)){
            neighbors.add(maze.getCell(current.getRow(), current.getCol() - 1));
        }

        // Adds all neighbors to the toVisit Stack and sets them as explored
        for(int i = 0; i < neighbors.size(); i++){
            neighbors.get(i).setParent(current);
            neighbors.get(i).setExplored(true);
            toVisit.push(neighbors.get(i));
        }
        // Recursively calls itself
        searchNeighborsDFS(toVisit);
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Sets the current MazeCell to be the StartCell
        MazeCell current = maze.getStartCell();
        current.setExplored(true);

        // Adds the current MazeCell to the Queue of Cells to visit
        Queue<MazeCell> toVisit = new LinkedList<>();
        toVisit.add(current);

        // Calls the recursive method
        searchNeighborsBFS(toVisit);

        // Returns the solution
        return getSolution();
    }

    public void searchNeighborsBFS(Queue<MazeCell> toVisit){
        // Checks the base cases and returns if they are true
        if(toVisit.isEmpty()){
            return;
        }
        MazeCell current = toVisit.remove();
        if(current == maze.getEndCell()){
            return;
        }

        ArrayList<MazeCell> neighbors = new ArrayList<>();

        //  Checks if each neighbor cell is valid, and if so, adds it to the ArrayList neighbors
        if(maze.isValidCell(current.getRow() - 1, current.getCol())){
            neighbors.add(maze.getCell(current.getRow() - 1, current.getCol()));
        }
        if(maze.isValidCell(current.getRow(), current.getCol() + 1)){
            neighbors.add(maze.getCell(current.getRow(), current.getCol() + 1));
        }
        if(maze.isValidCell(current.getRow() + 1, current.getCol())){
            neighbors.add(maze.getCell(current.getRow() + 1, current.getCol()));
        }
        if(maze.isValidCell(current.getRow(), current.getCol() - 1)){
            neighbors.add(maze.getCell(current.getRow(), current.getCol() - 1));
        }

        // Adds all neighbors to the toVisit Queue and sets them as explored
        for(int i = 0; i < neighbors.size(); i++){
            neighbors.get(i).setParent(current);
            neighbors.get(i).setExplored(true);
            toVisit.add(neighbors.get(i));
        }

        // Recursively calls itself
        searchNeighborsBFS(toVisit);
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze2.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
