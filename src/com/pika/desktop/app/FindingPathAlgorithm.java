package com.pika.desktop.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public final class FindingPathAlgorithm {

  public boolean find(Pikachu[][] grid, Pikachu start, Pikachu end) {
    if (hasNotSameId(start, end)) {
      return false;
    }

    setAllowCross(end, true);

    int rows = grid.length;
    int cols = grid[0].length;

    Queue<Pikachu> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rows][cols];

    addToQueue(queue, start, visited);

    while (!queue.isEmpty()) {
      Pikachu current = queue.poll();

      if (isValidItem(current, end)) {
        return true;
      }

      exploreNeighbors(grid, current, queue, visited);
    }

    resetAllowCross(end);
    return false;
  }

  private boolean hasNotSameId(Pikachu start, Pikachu end) {
    return start.getId() != end.getId();
  }

  private void setAllowCross(Pikachu pikachu, boolean allowCross) {
    pikachu.getPosition().setAllowCross(allowCross);
  }

  private void resetAllowCross(Pikachu pikachu) {
    setAllowCross(pikachu, false);
  }

  private void addToQueue(Queue<Pikachu> queue, Pikachu start, boolean[][] visited) {
    queue.add(start);
    visited[start.getPosition().getRowIndex()][start.getPosition().getColumnIndex()] = true;
  }

  private void exploreNeighbors(
      Pikachu[][] grid, Pikachu current, Queue<Pikachu> queue, boolean[][] visited) {
    int x = current.getPosition().getRowIndex();
    int y = current.getPosition().getColumnIndex();

    for (Direction direction : Direction.values()) {
      int newX = x + direction.getDx();
      int newY = y + direction.getDy();

      if (isValid(grid, newX, newY, visited)) {
        queue.add(grid[newX][newY]);
        visited[newX][newY] = true;
      }
    }
  }

  private boolean isValidItem(Pikachu source, Pikachu target) {
    return source.getPosition().getRowIndex() == target.getPosition().getRowIndex()
        && source.getPosition().getColumnIndex() == target.getPosition().getColumnIndex()
        && source.getId() == target.getId();
  }

  private boolean isValid(Pikachu[][] grid, int x, int y, boolean[][] visited) {
    if (!isWithinBounds(grid, x, y)) {
      return false;
    }

    return grid[x][y].getPosition().isAllowCross() && !visited[x][y];
  }

  private boolean isWithinBounds(Pikachu[][] grid, int x, int y) {
    int rows = grid.length;
    int cols = grid[0].length;

    return x >= 0 && x < rows && y >= 0 && y < cols;
  }

  public List<Position> findWithPath(Pikachu[][] grid, Pikachu start, Pikachu end) {
    if (hasNotSameId(start, end)) {
      return Collections.emptyList(); // Return empty if no path found
    }

    setAllowCross(end, true);

    int rows = grid.length;
    int cols = grid[0].length;

    Queue<Pikachu> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rows][cols];
    Map<Pikachu, Pikachu> previousNodeMap =
        new HashMap<>(); // To store the previous Pikachu in the path

    addToQueue(queue, start, visited);

    while (!queue.isEmpty()) {
      Pikachu current = queue.poll();

      if (isValidItem(current, end)) {
        List<Position> path = buildPath(previousNodeMap, current); // Build and return the path
        setAllowCross(end, false);
        return path;
      }

      exploreNeighborsWithPathTracking(grid, current, queue, visited, previousNodeMap);
    }

    setAllowCross(end, false);
    return Collections.emptyList(); // No path found
  }

  // Track the previous nodes and keep the trail of how you reached the current node
  private void exploreNeighborsWithPathTracking(
      Pikachu[][] grid,
      Pikachu current,
      Queue<Pikachu> queue,
      boolean[][] visited,
      Map<Pikachu, Pikachu> previousNodeMap) {
    int x = current.getPosition().getRowIndex();
    int y = current.getPosition().getColumnIndex();

    for (Direction direction : Direction.values()) {
      int newX = x + direction.getDx();
      int newY = y + direction.getDy();

      if (isValid(grid, newX, newY, visited)) {
        Pikachu nextPikachu = grid[newX][newY];
        queue.add(nextPikachu);
        visited[newX][newY] = true;
        previousNodeMap.put(nextPikachu, current); // Store the path (from current to next)
      }
    }
  }

  // Build the path by backtracking using the previousNodeMap
  private List<Position> buildPath(Map<Pikachu, Pikachu> previousNodeMap, Pikachu current) {
    List<Position> path = new LinkedList<>();

    while (current != null) {
      path.add(0, current.getPosition()); // Add the position to the front (reverse backtracking)
      current = previousNodeMap.get(current);
    }

    return path;
  }
}
