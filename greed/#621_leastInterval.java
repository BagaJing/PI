class leastInterval {
    public int leastInterval_1(char[] tasks, int n) {
        int[] taskMap = new int[26];
        for(char c : tasks)
            taskMap[c-'A']++; //统计不同种类任务的个数
        Arrays.sort(taskMap);
        int time = 0;
        //贪心思想:每次安排数量最多的任务
        while(taskMap[25]>0){
            for(int i = 0; i <= n ; i++){
                if(taskMap[25]==0)break;
                if(i<26&&taskMap[25-i]>0)
                    taskMap[25-i]--;
                time++;
            }
            Arrays.sort(taskMap);
        }
        return time;
    }
}