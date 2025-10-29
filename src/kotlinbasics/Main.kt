import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())

    // Example input for a problem
    val n = st.nextToken().toInt()
    val arr = IntArray(n)
    val st2 = StringTokenizer(br.readLine())
    for (i in 0 until n) {
        arr[i] = st2.nextToken().toInt()
    }

    // Example usage
    val solver = Solver()
    val result = solver.maxSubArray(arr)
    println(result)
}

// Organize all common array solutions here
internal class Solver {

    // 1. Two Sum (LeetCode 1)
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>() // value -> index
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }

    // 2. Maximum Subarray (Kadane's Algorithm)
    fun maxSubArray(nums: IntArray): Int {
        var currentSum = nums[0]
        var maxSum = nums[0]
        for (i in 1 until nums.size) {
            currentSum = maxOf(nums[i], currentSum + nums[i])
            maxSum = maxOf(maxSum, currentSum)
        }
        return maxSum
    }

    // 3. Contains Duplicate
    fun containsDuplicate(nums: IntArray): Boolean {
        val seen = HashSet<Int>()
        for (num in nums) {
            if (!seen.add(num)) return true
        }
        return false
    }

    // 4. Best Time to Buy and Sell Stock
    fun maxProfit(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = 0
        for (price in prices) {
            minPrice = minOf(minPrice, price)
            maxProfit = maxOf(maxProfit, price - minPrice)
        }
        return maxProfit
    }

    // 5. Product of Array Except Self
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val res = IntArray(n) { 1 }

        var prefix = 1
        for (i in 0 until n) {
            res[i] = prefix
            prefix *= nums[i]
        }

        var suffix = 1
        for (i in n - 1 downTo 0) {
            res[i] *= suffix
            suffix *= nums[i]
        }

        return res
    }
}
