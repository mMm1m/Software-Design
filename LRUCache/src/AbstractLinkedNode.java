import java.util.Objects;

public abstract class AbstractLinkedNode<Key,Value> {
    private AbstractLinkedNode<Key, Value> prev;
    private AbstractLinkedNode<Key, Value> next;
    private Key key;
    private Value value;

    public AbstractLinkedNode(AbstractLinkedNode<Key,Value>prev, AbstractLinkedNode<Key,Value>next,
    Key key, Value value){
        this.prev = prev;
        this.next =next;
        this.key = key;
        this.value = value;
    }
    public void setKey(Key key){
        this.key = key;
    }
    public void setValue(Value value){
        this.value = value;
    }
    public Value getValue(){
        return value;
    }
    public Key getKey(){
        return key;
    }

    public void setPrev(AbstractLinkedNode<Key, Value> node){
        this.prev = node.getPrev();
    }
    public void setNext(AbstractLinkedNode<Key,Value> node){
        this.next = node.getNext();
    }
    public AbstractLinkedNode<Key,Value> getPrev(){
        return this.prev;
    }
    public AbstractLinkedNode<Key,Value> getNext(){
        return this.next;
    }

    public static <Key, Value> void setLinks(final AbstractLinkedNode<Key, Value> first, final AbstractLinkedNode<Key, Value> second) {
        if (Objects.nonNull(first)) {
            first.next = second;
        }
        if (Objects.nonNull(second)) {
            second.prev = first;
        }
    }

}
