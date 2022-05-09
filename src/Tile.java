import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    // Every Tile has a tileId, a type, a property  and event function.

    //Variables
    private int tileId;
    private String type;
    private String property;

    private String eventName;

    //  Constructors
    public Tile() {

    }

    public Tile(int id, String type, String property) {
        this.tileId = id;
        this.type = type;
        this.property = property;
    }



    // isMovable checks whether tile is able to move or not.
    public boolean isMovable() {
        return !(UnmovableTiles.getUnmovableTiles().contains(type)) && ! (this.type.equals("Empty") && this.property.equals("Free"));

    }

    // Checks if tile is EmptyFree or not.
    public boolean isEmptyFreeTile() {

        return type.equals("Empty") && property.equals("Free");
    }

    // toString Method
    @Override
    public String toString() {
        return "type= " + type + "" + property;

    }
    // Getter and Setter Methods

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
