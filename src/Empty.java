public class Empty extends Tile {

    public Empty(int id, String type, String property) {
        super(id, type, property);
    }

    public boolean isStatic() {
        return false;
    }


}