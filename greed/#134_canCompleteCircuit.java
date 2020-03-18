class Solution {
public int canCompleteCircuit(int[] gas, int[] cost) {
        int current_load = 0,start=0;
        int total_load = 0;
        int len = gas.length;
        for(int i = 0 ; i < len ; i++){
            total_load += (gas[i]-cost[i]);
            if(current_load>=0){
                current_load += (gas[i]-cost[i]);
            }else{
                current_load = gas[i]-cost[i];
                start = i;
            }
        }
        return total_load>=0? start:-1;
    }
}