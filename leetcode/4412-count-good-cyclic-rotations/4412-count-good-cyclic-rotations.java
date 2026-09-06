class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        int half = n / 2;

        long total = 0;
        for (int x : nums) total += x;

        int[] arr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            arr[i + n] = nums[i];
        }

        long window = 0;

        for (int i = 0; i < half; i++) {
            window += arr[i];
        }

        int ans = 0;

        for (int start = 0; start < n; start++) {

            if (2 * window > total) {
                ans++;
            }

            if (start + half < 2 * n) {
                window -= arr[start];
                window += arr[start + half];
            }
        }

        return ans;
    }
}