import groovy.transform.AutoClone
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.TypeCheckingMode

@CompileStatic
@EqualsAndHashCode(includeFields = true)
@AutoClone
abstract class Tile {

    final static Tile EMPTY_TILE = new EmptyTile()

    protected int value

    abstract boolean canMergeTile(final Tile otherTile)

    abstract Tile mergeTile(final Tile otherTile)

    static Tile createTile(final int initialValue) {
        if(initialValue%2 != 0) throw new IllegalArgumentException("initialValue must be a factor of 2")
        if(initialValue == 0) {
            return EMPTY_TILE
        }
        return new ExponentTile(value: initialValue)
    }

    private static class EmptyTile extends Tile {

        public EmptyTile() {
            value = 0
        }

        @Override
        boolean canMergeTile(final Tile otherTile) {
            //Another tile can always be merged into an empty tile
            return true
        }

        @Override
        @CompileStatic(TypeCheckingMode.SKIP)
        Tile mergeTile(final Tile otherTile) {
            if(otherTile.is(this)) return this
            return otherTile.clone()
        }
    }

    private static class ExponentTile extends Tile {

        @Override
        boolean canMergeTile(final Tile otherTile) {
            //Can only merge tiles of the same value, or merge an empty tile
            return otherTile.value == this.value || otherTile.is(EMPTY_TILE)
        }

        @Override
        @CompileStatic(TypeCheckingMode.SKIP)
        Tile mergeTile(final Tile otherTile) {
            if(otherTile.is(EMPTY_TILE)) {
                return this.clone()
            }
            return createTile(this.value * otherTile.value)
        }
    }
}
