package CustomTree;

import java.io.Serializable;
import java.util.*;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    private transient ArrayList<Entry<String>> queue;

    public CustomTree() {
        root = new Entry<>(null);
        root.newLineRootElement = true;
        root.lineNumber = 0;
    }

    public String getParent(String value) {
        if (queue == null) {
            return "";
        }
        for (Entry<String> stringEntry : queue) {
            if (stringEntry.lineNumber != 1) {
                if (stringEntry.elementName.equals(value)) {
                    return stringEntry.parent.elementName;
                }
            }
        }

        return "";
    }

    public boolean add(String elementName) {
        setUpCollection(root);
        for (Entry<String> stringEntry : queue) {
            stringEntry.checkChildren();
            if (stringEntry.isAvailableToAddChildren()) {
                createChild(stringEntry, elementName);
                setValidCollection();
                return true;
            }
        }
        return false;
    }

    private void setUpCollection(Entry<String> root) {
        queue = new ArrayList<>();
        Queue<Entry<String>> subQueue = new LinkedList<>();
        queue.add(root);
        subQueue.add(root);
        do {
            if (!subQueue.isEmpty()) {
                root = subQueue.poll();
            }
            if (root.leftChild != null) {
                subQueue.add(root.leftChild);
                queue.add(root.leftChild);
            }
            if (root.rightChild != null) {
                subQueue.add(root.rightChild);
                queue.add(root.rightChild);
            }

        } while (!subQueue.isEmpty());


    }

    private void createChild(Entry<String> parent, String childElementName) {
        Entry<String> newOne = new Entry<String>(childElementName);
        newOne.parent = parent;
        newOne.lineNumber = parent.lineNumber + 1;
        setChildren(parent, newOne);
    }

    private void setChildren(Entry<String> parent, Entry<String> child) {
        if (parent.availableToAddLeftChildren) {
            parent.leftChild = child;
            parent.availableToAddLeftChildren = false;
            if (parent.newLineRootElement) {
                List<Entry<String>> list = getNewLineRootElementsCollection(parent);
                for (Entry<String> entry : list) {
                    entry.newLineRootElement = false;
                }
                child.newLineRootElement = true;
            }
        } else {
            parent.rightChild = child;
            parent.availableToAddRightChildren = false;
            if (parent.newLineRootElement) {
                List<Entry<String>> list = getNewLineRootElementsCollection(parent);
                for (Entry<String> entry : list) {
                    entry.newLineRootElement = false;
                }
                child.newLineRootElement = true;
            }

        }
    }

    private List<Entry<String>> getNewLineRootElementsCollection(Entry<String> parent) {
        ArrayList<Entry<String>> list = new ArrayList<>();
        list.add(parent);
        if ((parent.parent != null) && (parent.parent.newLineRootElement)) {
            list.addAll(getNewLineRootElementsCollection(parent.parent));
        }
        return list;
    }

    private void setValidCollection() {
        setUpCollection(root);
        queue.remove(0);
    }

    @Override
    public int size() {
        if (queue != null) {
            return queue.size();
        }
        return 0;
    }

    public boolean remove(Object o) {
        String toRemove;
        try {
            toRemove = (String) o;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException();
        }

        if (queue == null||toRemove==null) {
            return false;
        }
        for (Entry<String> stringEntry : queue) {
            if ( (stringEntry.lineNumber != 1) && (stringEntry.elementName.equals(toRemove))){
                System.out.println("found "+stringEntry.elementName);
                List<Entry<String>> list = collectChildren(stringEntry);
                System.out.println("Items to be deleted "+list);
                for (Entry<String> entry : list) {
                    entry=null;
                }
                return true;
            }
        }

        return false;
    }

    private List<Entry<String>> collectChildren(Entry<String> parent){
        ArrayList<Entry<String>> list = new ArrayList<>();
        list.add(parent);
        if(parent.leftChild!=null){
            list.addAll(collectChildren(parent.leftChild));
        }
        if(parent.rightChild!=null){
            list.addAll(collectChildren(parent.rightChild));
        }
        return list;
    }


    // UnsupportedOperations
    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {

        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        boolean newLineRootElement;
        Entry<T> parent, leftChild, rightChild;

        private Entry(String name) {
            elementName = name;
            newLineRootElement = false;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        private boolean isAvailableToAddChildren() {
            return this.availableToAddRightChildren || this.availableToAddLeftChildren;
        }

        private void checkChildren() {
            if (this.leftChild != null) {
                this.availableToAddLeftChildren = false;
            }
            if (this.rightChild != null) {
                this.availableToAddRightChildren = false;
            }
        }
        @Override
        public String toString() {
            return elementName;
        }
    }
}
