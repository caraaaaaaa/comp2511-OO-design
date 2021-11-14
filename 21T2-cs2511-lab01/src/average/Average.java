package average;

public class Average {
    /**
     * Returns the average of an array of numbers
     * 
     * @param the array of integer numbers
     * @return the average of the numbers
     */
    public float computeAverage(int[] nums) {
        float result = 0;
        // Add your code
        int sums = 0;
        for (int i =0; i< nums.length;i++) {
            sums = sums + nums[i];
        }
        result = sums /( nums.length);
        return result;
    }

    public static void main(String[] args) {
        // Add your code
        Average a = new Average();
        int[] nums = {1, 5, 3};
        float re=a.computeAverage(nums);
        System.out.println(re);
    }
}
