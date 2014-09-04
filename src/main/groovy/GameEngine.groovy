import static Tile.EMPTY_TILE

class GameEngine extends Observable {

    private boolean running = true
    private GameView view
    private Tile[][] tiles = new Tile[4][4]

    GameEngine(final GameView view) {
        this.view = view
        //Initialize the board to completely empty
        tiles.eachWithIndex { row, rowIndex ->
            row.eachWithIndex { tile, tileIndex ->
                row[rowIndex][tileIndex] = EMPTY_TILE
            }
        }
        //select two random tiles to be '2's
        Random numGen = new Random()
        final tileOneRowNum = numGen.nextInt(4)
        final tileOneColumnNum = numGen.nextInt(4)
        final tileTwoRowNum = numGen.nextInt(4)
        final tileTwoColumnNum = numGen.nextInt(4)
        //Find those tiles, and merge them with a new number 2 tile
        tiles[tileOneRowNum][tileOneColumnNum].mergeTile(Tile.createTile(2))
        tiles[tileTwoRowNum][tileTwoColumnNum].mergeTile(Tile.createTile(2))
    }

    boolean getRunning() {
        //The game ends when all tiles are not empty
        final tileCollection = tiles as Collection
        return tileCollection.flatten().find {
            it == EMPTY_TILE
        } != null
    }

    void moveLeft() {
        tiles.eachWithIndex{ row, rowIndex ->
            row.eachWithIndex{ tile, tileIndex ->
                //If it's not the last tile in the row, can we merge the tile adjacent?
                if(tileIndex != 3 && tile.canMergeTile(tileIndex + 1)) {
                    tiles[rowIndex][tileIndex] = tile.mergeTile(row[tileIndex + 1])
                }
            }
        }
        view.update(this, tiles)
    }

    void moveRight() {

    }

    void moveUp() {

    }

    void moveDown() {

    }

}
