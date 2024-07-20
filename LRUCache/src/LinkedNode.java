import java.time.temporal.ValueRange;

public class LinkedNode<Key, Value> extends AbstractLinkedNode<Key, Value> {
    public LinkedNode(Key key, Value value){
        super(null, null, key, value);
    }
}
