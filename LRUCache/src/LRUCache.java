import java.util.*;
import java.util.stream.Collectors;

public class LRUCache<Key,Value> implements Map<Key, Value>  {
    private final int capacity;
    private final DoublyLinkedList<Key, Value> doublyLinkedList;
    private final Map<Key, AbstractLinkedNode<Key, Value>> map;

    public LRUCache(){
        this.capacity = 0;
        doublyLinkedList = new DoublyLinkedList<>();
        map = new HashMap<>();
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        doublyLinkedList = new DoublyLinkedList<>();
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        if(map.containsKey(o)) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        if(map.containsValue(o)) return true;
        return false;
    }

    @Override
    public Value get(Object o) {
        if(!map.containsKey((Key)o)) return null;
        return map.get(o).getValue();
    }

    @Override
    public Object put(Object key, Object value) {
        assert key != null;
        assert value != null;

        if (map.containsKey((Key)key)) {
            final var node = map.get(key);
            doublyLinkedList.pushFrontFromPosition(node);
            final var old = node.getValue();
            node.setValue((Value) value);
            return old;
        }
        final AbstractLinkedNode<Key,Value> newNode = new LinkedNode<>((Key)key, (Value) value);
        if (capacity == size()) {
            final var lastNode = doublyLinkedList.getTail();
            map.remove(lastNode.getKey());
            doublyLinkedList.popLast();
        }
        doublyLinkedList.prepend(newNode);
        map.put((Key)key, newNode);
        return value;
    }

    @Override
    public Value remove(Object key) {
        if (!map.containsKey((Key) key)) return null;
        final var node = map.get((Key) key);
        map.remove(key);
        doublyLinkedList.remove(node);
        return node.getValue();
    }

    @Override
    public void putAll(Map<? extends Key, ? extends Value> m) {
        assert m.size() <= capacity;
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        map.clear();
        doublyLinkedList.setHead(null);
        doublyLinkedList.setTail(null);
    }

    @Override
    public Set<Key> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Value> values() {
        return map.values().stream().map(AbstractLinkedNode::getValue).collect(Collectors.toList());
    }

    @Override
    public Set<Entry<Key, Value>> entrySet() {
        return map.entrySet().
                stream().
                map(e -> Map.entry(e.getKey(), e.getValue().getValue()))
                .collect(Collectors.toSet());
    }
}
