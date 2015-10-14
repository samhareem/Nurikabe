##Instructions:

To solve a Nurikabe puzzle, you create a "river" that passes through the board. The areas left over are known as "islands". The complete rules are

1. Each island contains exactly one clue.
2. The number of squares in each island equals the value of the clue.
3. All islands are isolated from each other horizontally and vertically.
4. There are no river areas of 2x2 or larger.
5. When completed, the river has a single, continuous path.

To start a new puzzle, choose the level from the main menu and click the "Start" button. This will open a new puzzle for you. In the puzzle view, clicking a grid will attempt to mark it as a river tile. If you were correct, the grid will turn black. If you made a mistake, the river will remain white and your mistake count will increase. When the puzzle is complete, you will get a message congratulating you!

From the puzzle view, you can also reset the puzzle with the "Reset" button or exit it with the "Exit" button.

To exit the program, you can use the "Quit" button in the main menu or close the window.

##Adding a puzzle:

Puzzles are stored in .txt files. A valid level file contains 81 integers between 0 and 81, one for each grid of the puzzle. Each integer is written on it's own line. The integers are read in order, that is to say the first 9 integers equal the first 9 grids on the puzzle board, the following 9 integers equal the second row, etc. The program uses the following rules when parsing a file.

0 denotes a grid that will be painted black on the finished board.
1 denotes a grid that will be left white on the finished board.
Any number between 2 and 81 will be considered a clue, and the grid will contain a label containing the number - 1 (for example, the number 6 would be translated to a 5 on the gameboard).

When adding puzzles, it is important to keep the files names consistent (a running number, beginning with 1) and to update the numberOfLevels variable in the Nurikabe class when adding levels. All levels are stored in the /src/main/resources/levels folder.

