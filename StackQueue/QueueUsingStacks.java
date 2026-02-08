/* Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * You must use only standard operations of a stack, which means only push to top, peek/pop from top,
 * size, and is empty operations are valid.
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time even if one of those operations
 * may take longer. */

class QueueUsingStacks<T> {
    /**
     * Stores the elements as they are pushed to the queue.
     */
    private final Stack<T> s;

    /**
     * Stores the elements from the stack {@link #s}, in the reverse order, for pop and peek operations.
     *
     * @see #shiftStacks()
     */
    private final Stack<T> rev;

    public MyQueue() {
        this.s = new Stack<>();
        this.rev = new Stack<>();
    }

    // O(1)
    public void push(int x) {
        s.push(x);
    }

    // Amortized O(1)
    public int pop() {
        shiftStacks();
        return rev.pop();
    }

    // Amortized O(1)
    public int peek() {
        shiftStacks();
        return rev.peek();
    }

    public boolean empty() {
        return s.isEmpty() && rev.isEmpty();
    }

    public int size() {
        return s.size() + rev.size();
    }

    /**
     * Shifts the elements from stack {@link #s} to stack {@link #rev} only when the
     * reverse stack is empty.
     */
    private void shiftStacks() {
        if (rev.isEmpty()) {
            while (!s.isEmpty()) {
                rev.push(s.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
    }
}
