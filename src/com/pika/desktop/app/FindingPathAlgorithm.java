package com.pika.desktop.app;

import java.util.LinkedList;
import java.util.Queue;

public final class FindingPathAlgorithm {

  public boolean find(Pikachu[][] grid, Pikachu start, Pikachu end, int limitDirection) {
    if (hasNotSameId(start, end)) {
      return false;
    }

    setAllowCross(end, true);

    int rows = grid.length;
    int cols = grid[0].length;

    Queue<PathNode> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rows][cols];

    // Add the start node with 0 direction changes and a null initial direction
    addToQueue(queue, start, visited, null, 0);

    while (!queue.isEmpty()) {
      PathNode currentNode = queue.poll();
      Pikachu current = currentNode.pikachu;

      if (isValidItem(current, end)) {
        resetAllowCross(end);
        return true;
      }

      exploreNeighbors(grid, currentNode, queue, visited, limitDirection);
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

  private void addToQueue(
      Queue<PathNode> queue,
      Pikachu pikachu,
      boolean[][] visited,
      Direction direction,
      int directionChanges) {
    queue.add(new PathNode(pikachu, direction, directionChanges));
    visited[pikachu.getPosition().getRowIndex()][pikachu.getPosition().getColumnIndex()] = true;
  }

  private void exploreNeighbors(
      Pikachu[][] grid,
      PathNode currentNode,
      Queue<PathNode> queue,
      boolean[][] visited,
      int limitDirection) {
    int x = currentNode.pikachu.getPosition().getRowIndex();
    int y = currentNode.pikachu.getPosition().getColumnIndex();
    Direction previousDirection = currentNode.direction;
    int currentDirectionChanges = currentNode.directionChanges;

    for (Direction direction : Direction.values()) {
      int newX = x + direction.getDx();
      int newY = y + direction.getDy();

      // Calculate direction changes: increase if direction has changed
      int newDirectionChanges =
          previousDirection == direction ? currentDirectionChanges : currentDirectionChanges + 1;

      if (isValid(grid, newX, newY, visited) && newDirectionChanges <= limitDirection) {
        queue.add(new PathNode(grid[newX][newY], direction, newDirectionChanges));
        visited[newX][newY] = true;
      }
    }
  }

  private static class PathNode {
    Pikachu pikachu;
    Direction direction;
    int directionChanges;

    PathNode(Pikachu pikachu, Direction direction, int directionChanges) {
      this.pikachu = pikachu;
      this.direction = direction;
      this.directionChanges = directionChanges;
    }
  }
}
