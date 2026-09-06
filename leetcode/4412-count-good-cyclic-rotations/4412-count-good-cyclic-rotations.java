class Solution {
    public int countGoodRotations(int[] nums) {

        int n = nums.length;
        int ans = 0;

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < n / 2; i++) {
            sum1 += nums[i];
        }

        for (int i = n / 2; i < n; i++) {
            sum2 += nums[i];
        }

        for (int i = 0; i < n; i++) {

            if (sum1 > sum2) {
                ans++;
            }

            int mid = (i + (n / 2)) % n;

            sum1 += nums[mid];
            sum2 -= nums[mid];

            sum1 -= nums[i];
            sum2 += nums[i];
        }

        return ans;
    }
}