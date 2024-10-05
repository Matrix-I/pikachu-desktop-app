package com.pika.desktop.app;

import java.util.LinkedList;
import java.util.Queue;

public class PathfindingBFS {
  // Direction vectors for moving up, down, left, and right
  private static final int[][] DIRECTIONS = {
    {0, 1}, // Right
    {1, 0}, // Down
    {0, -1}, // Left
    {-1, 0} // Up
  };

  public static void main(String[] args) {
    int[][] grid = {
      {1, 0, 1, 1},
      {1, 1, 1, 0},
      {0, 1, 1, 1},
      {1, 1, 0, 1}
    };

    int[] start = {0, 0}; // Starting point
    int[] end = {3, 3}; // End point

    int result = bfs(grid, start, end, 2); // Allow at most 2 breaks

    if (result != -1) {
      System.out.println("Path found with length: " + result);
    } else {
      System.out.println("No valid path found.");
    }
  }

  // BFS algorithm to find the shortest path in a grid, allowing up to 'maxBreaks' breaks
  public static int bfs(int[][] grid, int[] start, int[] end, int maxBreaks) {
    int rows = grid.length;
    int cols = grid[0].length;

    // Queue to hold the current position, number of steps, and number of breaks used
    Queue<int[]> queue = new LinkedList<>();
    boolean[][][] visited =
        new boolean[rows][cols][maxBreaks + 1]; // Track visited cells with different break counts

    // Add the starting point to the queue with 0 breaks used
    queue.add(new int[] {start[0], start[1], 0}); // [row, col, breaks]
    visited[start[0]][start[1]][0] = true;

    int steps = 0;

    // BFS to explore all possible paths
    while (!queue.isEmpty()) {
      int queueSize = queue.size();

      // Explore all cells at the current level
      for (int i = 0; i < queueSize; i++) {
        int[] current = queue.poll();
        int x = current[0];
        int y = current[1];
        int breaks = current[2];

        // If we reached the end point, return the number of steps
        if (x == end[0] && y == end[1]) {
          return steps;
        }

        // Explore the four possible directions (up, down, left, right)
        for (int[] direction : DIRECTIONS) {
          int newX = x + direction[0];
          int newY = y + direction[1];

          // Check if the new position is valid
          if (isValid(grid, newX, newY)) {
            // If the new cell is not blocked and not visited, continue without breaking
            if (grid[newX][newY] == 1 && !visited[newX][newY][breaks]) {
              queue.add(new int[] {newX, newY, breaks});
              visited[newX][newY][breaks] = true;
            }
            // If the new cell is blocked and we haven't exceeded the max breaks, break and continue
            else if (grid[newX][newY] == 0
                && breaks < maxBreaks
                && !visited[newX][newY][breaks + 1]) {
              queue.add(new int[] {newX, newY, breaks + 1});
              visited[newX][newY][breaks + 1] = true;
            }
          }
        }
      }

      steps++; // Increment the step count after exploring each level
    }

    // If no path was found, return -1
    return -1;
  }

  // Check if the next move is valid (within bounds)
  private static boolean isValid(int[][] grid, int x, int y) {
    int rows = grid.length;
    int cols = grid[0].length;

    return x >= 0 && x < rows && y >= 0 && y < cols;
  }
}
