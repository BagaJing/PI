class Twitter {
    class tweet{
        int id;
        int time;
        public tweet(int id, int time){
            this.id = id;
            this.time = time;
        }
    }
    class User{
        int userId;
        List<tweet> tweets;
        Set<User> followings;
        public User(int userId){
            this.userId = userId;
            tweets = new ArrayList<>();
            followings = new HashSet<>();
            followings.add(this);
        }
        public void post(int tweetId,int time){
            tweets.add(new tweet(tweetId,time));
        }
        public List<Integer> get(){
            List<tweet> all = new ArrayList<>();
            for(User following : followings){
                all.addAll(following.tweets);
            }
            Collections.sort(all,new Comparator<>(){
                public int compare(tweet t1, tweet t2){
                    return t1.time - t2.time;
                }
            });
            List<Integer> res = new ArrayList<>();
            int cap = 1;
            while(cap <= 10&& all.size()-cap>=0){
                res.add(all.get(all.size()-cap).id);
                cap++;
            }
            return res;
        }
    }
    /** Initialize your data structure here. */
    int time;
    Map<Integer,User> userMap;
    public Twitter() {
        this.time = 0;
        this.userMap = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)){
            userMap.put(userId,new User(userId));
        }
        userMap.get(userId).post(tweetId,time);
        time++;
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if(!userMap.containsKey(userId)) return new ArrayList<>();
        else return userMap.get(userId).get();
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId) return;
        if(!userMap.containsKey(followerId)) userMap.put(followerId,new User(followerId));
        if(!userMap.containsKey(followeeId)) userMap.put(followeeId,new User(followeeId));
        userMap.get(followerId).followings.add(userMap.get(followeeId));
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId == followeeId) return;
        if(!userMap.containsKey(followerId)) userMap.put(followerId,new User(followerId));
        if(!userMap.containsKey(followeeId)) userMap.put(followeeId,new User(followeeId));
        userMap.get(followerId).followings.remove(userMap.get(followeeId));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */