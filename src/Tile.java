import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    private int tileId;
    private String type;
    private String property;
    private ImageView imageView;
    private String eventName;

    public Tile() {

    }

    public Tile(int id, String type, String property) {
        this.tileId = id;
        this.type = type;
        this.property = property;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTileId() {
        return tileId;
    }

    public void setId(int id) {
        this.tileId = id;
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

//    public int getRow() {
//        return (tileId - 1) / 4;
//    }
//
//    public int getColumn() {
//        return (tileId - 1) % 4;
//    }

    public boolean isMovable() {
        return !(UnmovableTiles.getUnmovableTiles().contains(type)) && ! (this.type.equals("Empty") && this.property.equals("Free"));

    }

    public boolean isEmptyFreeTile() {

        return type.equals("Empty") && property.equals("Free");
    }

    @Override
    public String toString() {
        return "type= " + type + "" + property;

    }


}
