class Solution {
    public int countRotations(String s, int k) {

        int n = s.length();
        int ans = 0;

        for (int rot = 0; rot < n; rot++) {

            String cur = s.substring(rot) + s.substring(0, rot);

            int score = 0;

            for (int i = 0; i < n - 1; i++) {
                if (cur.charAt(i) == cur.charAt(i + 1)) {
                    score++;
                }
            }

            if (score == k) {
                ans++;
            }
        }

        return ans;
    }
}


// class Solution {
//     public int countRotations(String s, int k) {

//         int n = s.length(), cnt = 0;

//         for (int i = 0; i < n; i++) {

//             if (check(s, k)) {
//                 cnt++;
//             }

//             if (i < n - 1) {
//                 s = reverse(s, 0, 0);
//                 s = reverse(s, 1, n - 1);
//                 s = reverse(s, 0, n - 1);
//             }
//         }

//         return cnt;
//     }

//     String reverse(String s, int left, int right) {
//         char[] arr = s.toCharArray();

//         while (left < right) {
//             char temp = arr[left];
//             arr[left] = arr[right];
//             arr[right] = temp;
//             left++;
//             right--;
//         }

//         return new String(arr);
//     }

//     boolean check(String s, int k) {
//         int cnt = 0;

//         for (int i = 0; i < s.length() - 1; i++) {
//             if (s.charAt(i) == s.charAt(i + 1)) {
//                 cnt++;
//             }
//         }

//         return cnt == k;
//     }
// }