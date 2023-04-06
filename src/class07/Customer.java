package class07;

import java.util.Comparator;

public class Customer {
    public int id;
    public int buy;
    public int enterTime;

    public Customer(int id, int buy) {
        this.id = id;
        this.buy = buy;
        enterTime = 0;
    }
}

class CandidateComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
    }

}

class DaddyComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
    }

}