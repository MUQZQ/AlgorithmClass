package class08;

public interface Trie {
    /**
     * 添加某个字符串，可以重复添加，每次算1个
     *
     * @param word
     */
    void insert(String word);

    /**
     * 删掉某个字符串，可以重复删除，每次算1个
     *
     * @param word
     */
    void delete(String word);

    /**
     * 查询某个字符串在结构中还有几个
     *
     * @param word
     * @return
     */
    int search(String word);

    /**
     * 查询有多少个字符串，是以str做前缀的
     *
     * @param pre
     * @return
     */
    int prefixNumber(String pre);
}
