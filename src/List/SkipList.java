package List;

public class SkipList {

}

class SkipNode<E>{

    private E data;
    private SkipNode down;
    private SkipNode right;

    public SkipNode(E data) {
        this.data = data;
        this.down = null;
        this.right = null;
    }

    public SkipNode(E data, SkipNode down, SkipNode right) {
        this.data = data;
        this.down = down;
        this.right = right;
    }

    public void setDown(SkipNode down) {
        this.down = down;
    }

    public void setRight(SkipNode right) {
        this.right = right;
    }
}