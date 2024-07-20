public class DoublyLinkedList<Key, Value> {
    private int size;

    private AbstractLinkedNode<Key, Value> tail;
    private AbstractLinkedNode<Key, Value> head;
    public void pushFrontFromPosition(final AbstractLinkedNode<Key, Value> node) {
        if (node.getPrev() == null) return;
        var prevNode = node.getPrev();
        var nextNode = node.getNext();
        boolean isTail = (nextNode == null);
        AbstractLinkedNode.setLinks(node.getPrev(), node.getNext());
        size--;
        prepend(node);
        if (isTail) tail = prevNode;
    }

    public void prepend(final AbstractLinkedNode<Key, Value> node) {
        AbstractLinkedNode.setLinks(node, head);
        if (size == 1) tail = head;
        head = node;
        size++;
    }

    public void popFront() {
        assert size > 0;
        head = head.getNext();
        if (head != null) head.setPrev(null);
        size--;
    }

    public void popLast() {
        assert size > 0;
        if (size == 1) {
            popFront();
            return;
        }
        tail = tail.getPrev();
        tail.setNext(null);
        size--;
    }

    public void remove(final AbstractLinkedNode<Key, Value> node) {
        if (node == head) popFront();
        else if (node == tail) popLast();
        else {
            AbstractLinkedNode.setLinks(node.getPrev(), node.getNext());
            size--;
        }
    }

    public int getSize() {
        return size;
    }

    public AbstractLinkedNode<Key, Value> getTail() {
        return tail;
    }

    public AbstractLinkedNode<Key, Value> getHead() {
        return head;
    }

    public void setHead(final AbstractLinkedNode<Key, Value> head) {
        this.head = head;
    }

    public void setTail(final AbstractLinkedNode<Key, Value> last) {
        this.tail = last;
    }
}
