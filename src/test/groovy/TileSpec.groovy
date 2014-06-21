import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static Tile.EMPTY_TILE

class TileSpec extends Specification {

    @Shared
    def zero = Tile.createTile(0)

    @Shared
    def two = Tile.createTile(2)

    @Shared
    def four = Tile.createTile(4)

    def "Correct tiles are created using factory"() {
        expect:
        Tile.createTile(0) == EMPTY_TILE
        Tile.createTile(2) != EMPTY_TILE
    }

    def "Can not create a tile with a value that is not a factor of two"() {
        when:
        Tile.createTile(3)

        then:
        thrown(IllegalArgumentException)
    }

    @Unroll
    def "Test correct mergability behavior"() {
        expect:
        tileOne.canMergeTile(tileTwo) == canMerge

        where:
        tileOne  |  tileTwo  |  canMerge
        zero     |  zero     |  true
        zero     |  two      |  true
        two      |  zero     |  true
        two      |  two      |  true

        two      |  four     |  false
        four     |  two      |  false
    }

    @Unroll
    def "Test correct merge result"() {
        expect:
        tileOne.mergeTile(tileTwo) == tileThree

        where:
        tileOne  |  tileTwo  |  tileThree
        zero     |  zero     |  zero
        zero     |  two      |  two
        two      |  zero     |  two
        two      |  two      |  four
    }
}
