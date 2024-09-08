# maze-generator

## Description
This program takes as input the width, length of the maze and a number between 1 and 100. The last number represents how many walls should remain in the maze, where 0 is the maximum amount of walls and 100 is the minimum. The output will be the walls which are present in the new maze, besides the ones on the sides. They will be of the form (l1, c1) -> (l2, c2), meaning that there is a wall between the cells located at line l1, column c1 and line l2 and column c2.

## Why
I have built this program with the idea that it could help with testing algorithms which focus on pathfinding, both when they know in advance the whole map and when they do not know it. 

## How to run
Open a project in your IDE and copy the four classes: DisjointSet, Main, Pair and Maze. Run main and input the length, width and degrees of freedom.
