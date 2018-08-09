package com.skplanet.nlp.trie;

/**
 * Trie Result Object
 * @author Donghun Shin, donghun.shin@sk.com
 * @date 10/3/14.
 */
public final class TrieData {
    private Object value = null;
    private String key = null;
    private int length = -1;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
