class FindIntersec{
    //google面筋
    public String findIntersection(List<List<String>> input) {
        HashMap<String, Integer> strToFreq = new HashMap<>();
        int size = input.size();
        String res = "xy";
        for(List<String> list : input) {
            HashSet<String> visited = new HashSet<>();
            for(int i = 0; i < list.size(); i++) {
                String curr = list.get(i);
                if(!strToFreq.containsKey(curr) && visited.add(curr)) {
                    strToFreq.put(curr, 1);
                } else if(strToFreq.containsKey(curr) && visited.add(curr)){
                    strToFreq.put(curr,strToFreq.get(curr)+ 1);
                    if(strToFreq.get(curr) == size) {
                        res = curr;
                    }
                }
            }
            
        }
        return res;
    }
}