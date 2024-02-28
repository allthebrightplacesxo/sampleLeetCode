import java.util.*;
import javafx.util.*;
class Solution {
    List<Integer> memo = new ArrayList<>();
    public int helper(int[] nums, int target, int index, boolean positive)
    {
        if(target == 0 && index >= nums.length) return 1;
        if(index == nums.length - 1 || target == 0) return 0;
        int val = positive ? nums[index] : - nums[index];

        return helper(nums, target - val, index + 1, true) + helper(nums, target - val, index + 1, false);
    }

    public int findTargetSumWays(int[] nums, int target) {
        //[1,1,1,1,1], 3, 0, +
        return helper(nums, target, 0, false) ;
    }

    public int[] countBits(int n) {
        memo.add(0);
        int[] result = new int[n+1];
        result[0] = 0;
        int offset = 1;
        for(int i = 1; i <= n; i++)
        {
            if(i-1 < memo.size())
            {
                if(i % 2 == 0) {

                    result[i] = memo.get(offset-1);
                    memo.add(memo.get(offset-1));
                    offset = offset*2;
                }
                else{
                    result[i] = memo.get(offset-1) + 1;
                    memo.add(memo.get(offset-1) + 1);
                }
            }
        }
        return result;
    }

    public int findCircleNum(int[][] isConnected) {
        //AdjList - other than diagnol any value = 1, add that index
        List<List<Integer>> adjlist = new ArrayList<>();
        for(
                int i=0; i<isConnected.length; i++)
        {
            var temp = new ArrayList<Integer>();
            for(int j=0; j<isConnected[i].length; j++)
            {
                if(i==j) continue;
                else if(isConnected[i][j] == 1)
                {
                    temp.add(j);
                }
            }
            adjlist.add(temp);
        }

        return 0;
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int min = Integer.MAX_VALUE;
        Queue<Pair<Integer,Integer>> q = new LinkedList<>();
        for(int[] flight : flights)
        {
            if(flight[0] == src) q.add(new Pair (flight[1],flight[2]));
        }
        while(!q.isEmpty())
        {
            var pair = q.remove();
            if(pair.getKey() == dst)
            {
                min = Math.min(min, pair.getValue());
                continue;
            }
            for(int i=0; i<flights.length; i++)
            {
                if(pair.getKey() == flights[i][0])
                {
                    q.add(new Pair(flights[i][1], pair.getValue() + flights[i][2]));
                }
            }
        }
        return min;
    }
}