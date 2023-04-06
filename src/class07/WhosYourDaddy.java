package class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WhosYourDaddy {
    private final int daddyLimit;
    private final HashMap<Integer, Customer> customers;
    private final HeapGreater<Customer> candHeap;
    private final HeapGreater<Customer> daddyHeap;

    public WhosYourDaddy(int limit) {
        customers = new HashMap<>();
        candHeap = new HeapGreater<>(new CandidateComparator());
        daddyHeap = new HeapGreater<>(new DaddyComparator());
        daddyLimit = limit;
    }

    // 当前处理i号事件，arr[i] -> id,  buyOrRefund
    public void operate(int time, int id, boolean buyOrRefund) {
        if (!buyOrRefund && !customers.containsKey(id)) {
            return;
        }
        if (!customers.containsKey(id)) {
            customers.put(id, new Customer(id, 0));
        }
        Customer c = customers.get(id);
        if (buyOrRefund) {
            c.buy++;
        } else {
            c.buy--;
        }
        if (c.buy == 0) {
            customers.remove(id);
        }
        if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
            if (daddyHeap.size() < daddyLimit) {
                c.enterTime = time;
                daddyHeap.push(c);
            } else {
                c.enterTime = time;
                candHeap.push(c);
            }
        } else if (candHeap.contains(c)) {
            if (c.buy == 0) {
                candHeap.remove(c);
            } else {
                candHeap.resign(c);
            }
        } else {
            if (c.buy == 0) {
                daddyHeap.remove(c);
            } else {
                daddyHeap.resign(c);
            }
        }
        daddyMove(time);
    }

    public List<Integer> getDaddies() {
        List<Customer> customers = daddyHeap.getAllElements();
        List<Integer> ans = new ArrayList<>();
        for (Customer c : customers) {
            ans.add(c.id);
        }
        return ans;
    }

    private void daddyMove(int time) {
        if (candHeap.isEmpty()) {
            return;
        }
        if (daddyHeap.size() < daddyLimit) {
            Customer p = candHeap.pop();
            p.enterTime = time;
            daddyHeap.push(p);
        } else {
            if (candHeap.peek().buy > daddyHeap.peek().buy) {
                Customer oldDaddy = daddyHeap.pop();
                Customer newDaddy = candHeap.pop();
                oldDaddy.enterTime = time;
                newDaddy.enterTime = time;
                daddyHeap.push(newDaddy);
                candHeap.push(oldDaddy);
            }
        }
    }

}