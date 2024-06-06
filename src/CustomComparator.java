import java.util.Comparator;

public class CustomComparator implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        return Integer.compare(p1.getScore(), p2.getScore());
    }

    // https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
}