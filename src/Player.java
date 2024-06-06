public class Player {
    private String name;
    private int score;
    private int order;

    public Player (String name, int score, int order) {
        this.name = name;
        this.score = score;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int newOrder) {
        order = newOrder;
    }

    public String toString() {
        return name + "|" + score;
    }

    public void sort() {

    }
}
