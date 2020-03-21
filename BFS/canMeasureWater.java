class canMeasureWater{
  public boolean canMeasureWater_1(int x, int y, int z) {
        if (z==0)return true;
        if(x+y<z)return false;
        State initState = new State(0,0);
        Queue<State> queue = new LinkedList<>();
        Set<State> visted = new HashSet<>();
        queue.offer(initState);
        visted.add(initState);
        while (!queue.isEmpty()){
            State head = queue.poll();
            int curX = head.getX();
            int curY = head.getY();
            if (curX==z||curY==z||curX+curY==z)return true;
            List<State> nextStates = getNextStates(curX,curY,x,y);
            for (State state : nextStates){
                if (!visted.contains(state)){
                    queue.offer(state);
                    visted.add(state);
                }
            }
        }
        return false;
    }
    private List<State> getNextStates(int curX,int curY,int x, int y){
        List<State> res = new ArrayList<>(8);
        if (curX < x) res.add(new State(x,curY));
        if (curY < y) res.add(new State(curX,y));
        if (curX > 0) res.add(new State(0,curY));
        if (curY > 0) res.add(new State(curX,0));
        if (curX > y - curY) res.add(new State(curX-(y-curY),y));
        if (curX < y - curY) res.add(new State(0,curY+curX));
        if (curY > x - curX) res.add(new State(x,curY-(x-curX)));
        if (curY < x - curX) res.add(new State(curX+curY,0));
        return res;
    }
}
class State{
    private int x;
    private int y;

    public State(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x == state.x &&
                y == state.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}