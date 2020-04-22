class Solution {
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        double k_1 ;
        if(end1[0]-start1[0] == 0) k_1 = 0;
        else k_1 = (double)(end1[1]-start1[1])/(end1[0]-start1[0]);
        double k_2;
        if(end2[0]-start2[0]==0) k_2 = 0;
        else k_2 = (double)(end2[1]-start2[1])/(end2[0]-start2[0]);
        //System.out.println(k_1);
        //System.out.println(k_2);
        double c_1 = 0;
        if(k_1 == 0) c_1 = 0;
        else c_1 = ((double)(end1[1]+start1[1])-(double)(end1[0]+start1[0]))/2*k_1;
        double c_2 = 0;
        if(k_2==0) c_2 = 0;
        else  c_2 = ((double)(end2[1]+start2[1])-(double)(end2[0]+start2[0]))/2*k_2;
         //System.out.println(c_1);
        //System.out.println(c_2);
        if(k_1-k_2 == 0){
            return new double[0];
        }
        double x = (c_2-c_1)/(k_1-k_2);
        double y = k_1*x+c_1;
        //System.out.println(x);
        double[] res = new double[2];
        res[0] = x;
        res[1] = y;
      //  System.out.println(Arrays.toString(res));
        return res;
    }
    private boolean 

}