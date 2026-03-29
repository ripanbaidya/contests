import java.util.*;

class EventManager {

  private static class Node {
    int id, p;

    Node(int id, int p) {
      this.id = id;
      this.p = p;
    }
  }

  TreeSet<Node> set;
  HashMap<Integer, Integer> map;

  public EventManager(int[][] events) {
    set = new TreeSet<>((a, b) -> {
      if (a.p != b.p)
        return b.p - a.p; // higher first
      return a.id - b.id;
    });

    map = new HashMap<>();

    int[][] arr = events; // just alias

    for (int i = 0; i < arr.length; i++) {
      int id = arr[i][0];
      int pr = arr[i][1];

      map.put(id, pr);
      set.add(new Node(id, pr));
    }
  }

  public void updatePriority(int eventId, int newPriority) {
    int old = map.get(eventId);

    set.remove(new Node(eventId, old)); // remove old
    set.add(new Node(eventId, newPriority)); // add new

    map.put(eventId, newPriority);
  }

  public int pollHighest() {
    if (set.size() == 0)
      return -1;

    Node top = set.pollFirst();
    map.remove(top.id);

    return top.id;
  }
}