class reconstructQueue {
    public int[][] reconstructQueue_1(int[][] people) {
        Arrays.sort(people,new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2) {
               return o1[0]==o2[0]? o1[1]-o2[1]:o2[0]-o1[0];
           }
        });
        List<int[]> greedy = new ArrayList<>();
        for(int[] arr : people)
            greedy.add(arr[1],arr);
        for(int i = 0; i < people.length ; i++)
            people[i] = greedy.get(i);
        return people;
    }
}