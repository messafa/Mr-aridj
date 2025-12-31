import java.util.*;

class BranchBoundKnapsack {

    static int maxValue = 0;

    static int bound(int index, int weight, int value, int capacity, Item[] items) {
        int totalValue = value;
        int totalWeight = weight;

        for (int i = index; i < items.length; i++) {
            if (totalWeight + items[i].weight <= capacity) {
                totalWeight += items[i].weight;
                totalValue += items[i].value;
            } else {
                break;
            }
        }
        return totalValue;
    }

    static void knapsack(int index, int weight, int value, int capacity, Item[] items) {
        if (weight > capacity) return;

        maxValue = Math.max(maxValue, value);

        if (index == items.length) return;

        int b = bound(index, weight, value, capacity, items);
        if (b <= maxValue) return; // ✂ Pruning

        // Branch: take item
        knapsack(index + 1,
                 weight + items[index].weight,
                 value + items[index].value,
                 capacity, items);

        // Branch: skip item
        knapsack(index + 1,
                 weight, value,
                 capacity, items);
    }
}


class Item {
    int value;
    int weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) {

        Item[] items = {
            new Item(2, 40),
            new Item(3, 50),
            new Item(4, 65)
        };

        int capacity = 6;

        BranchBoundKnapsack.knapsack(0, 0, 0, capacity, items);

        System.out.println("أفضل قيمة = " + BranchBoundKnapsack.maxValue);
    }
}
