class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliveryIndex = n - 1, pickupIndex = n - 1;
        while (deliveryIndex >= 0 || pickupIndex >= 0) {
            while (deliveryIndex >= 0 && deliveries[deliveryIndex] == 0) deliveryIndex--;
            while (pickupIndex >= 0 && pickups[pickupIndex] == 0) pickupIndex--;
            int maxDistance = Math.max(deliveryIndex, pickupIndex) + 1;
            processLoad(deliveries, deliveryIndex, cap);
            processLoad(pickups, pickupIndex, cap);
            answer += maxDistance * 2;
        }
        return answer;
    }
    void processLoad(int[] items, int index, int capacity) {
        for (int i = index; i >= 0 && capacity > 0; i--) {
            int handled = Math.min(items[i], capacity);
            items[i] -= handled;
            capacity -= handled;
        }
    }
}