class CompareVersionNumbers165 {
    //每次算.前的数； i和j都走到底， 中途也会判断，要是在一个ele内能比较出大小直接return
    //Time = O(n), math.max(version1.length; version2 length)
    //Space = O(1)
    public int compareVersion(String version1, String version2) {
        //找规律，比较大小
        int i = 0;
        int j = 0;
        while(i < version1.length() || j < version2.length()) {
            int integV1 = 0;
            int integV2 = 0;
            while(i < version1.length()&& version1.charAt(i) != '.') {
                integV1 = integV1*10 + version1.charAt(i) - '0';
                i++;
            }
            while(j < version2.length() && version2.charAt(j) != '.') {
                integV2 = integV2*10 + version2.charAt(j) - '0';
                j++;
            }
            if(integV1 > integV2) {
                return 1;
            } else if(integV2 > integV1) {
                return -1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }
}