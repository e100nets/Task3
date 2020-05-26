package ilya.ignatov;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Queue_ {
        Queue<String> queue = new LinkedList<>();

        public void addElement (String value) {
            queue.offer(value);
        }

        public void deleteElement () {
            queue.poll();
        }

        public void clear () {
            queue.clear();
        }

        public void duplicate () {
            final int count = queue.size();
            for (int i = 0; i < count; i++) {
                queue.offer(queue.peek());
                queue.offer(queue.poll());
            }
        }

        public void fromArray(String[] array) {
            Collections.addAll(queue, array);
        }

        public String[] toArray() {
            String[] array = new String[queue.size()];
            return queue.toArray(array);
        }
}
