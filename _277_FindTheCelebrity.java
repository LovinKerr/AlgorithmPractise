public class _277_FindTheCelebrity extends Relation {
    //celebrity不知道任何人，任何人都知道他
    //Time= O（n)
    //Space = O(1)
        public int findCelebrity(int n) {
        int pos = 0;
            
        for(int i = 1; i < n; i++) {
            if(knows(pos,i)) {
                pos = i;
            }
        }
            
        for(int i = 0; i < n; i++) {
            //2种不存在的情况
            if(i != pos && knows(pos, i)) {
                return -1;
            }
            
            if(i != pos && !knows(i, pos)) {
                return -1;
            }
        }
        return pos;

        }
}