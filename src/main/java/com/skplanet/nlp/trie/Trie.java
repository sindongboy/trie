package com.skplanet.nlp.trie;

/**
 * General Trie Implementation, which extends {@link AbstractTrie}
 * <br><br>Created by Donghun Shin<br>
 * Contact: donghun.shin@sk.com, sindongboy@gmail.com<br>
 * Date: 7/12/13<br>
 */
public class Trie extends AbstractTrie {
    private static final String SYMBOLS = "\"'-#.";

    /**
     * Constructor
     */
    public Trie() {
        super();
    }

    /**
     * Exact Match
     *
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
     */
    @Override
    public TrieData match(String input) {
        TrieNode node = this.root;
        String key = "";
        Object value;
        TrieData result = null;
        for (int i = 0; i < input.length(); i++) {
            key += input.charAt(i);
            if (input.charAt(i) == ' ') {
                continue;
            }
            node = node.getChild(input.charAt(i));
            if (node == null) {
                return null;
            }
            if (node.isTerminal()) {
                value = node.data;
                result = new TrieData();
                result.setKey(key);
                result.setValue(value);
                result.setLength(i);
            }
        }
        return result;
    }

    /**
     * Prefix Trie match
     * @param input String to be looked up
     * @return End index of matching part, -1 if doesn't match at all
     */
    public TrieData prefixMatch(String input) {
        TrieNode node = this.root;
        String key = "";
        Object value;
        TrieData result = new TrieData();
        for (int i = 0; i < input.length(); i++) {
            key += input.charAt(i);
            if (input.charAt(i) == ' ') {
                continue;
            }
            node = node.getChild(input.charAt(i));
            if (node == null) {
                return result;
            }
            result.setKey(key);
            result.setLength(i);
            if (node.isTerminal()) {
                value = node.data;
                result.setValue(value);
            }
        }
        return result;
    }

    /**
     * Relaxed Trie Match.
     *
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
     *
     * @param input input string to be looked up
     * @return data node
     */
    public TrieData matchRelaxed(String input) {
        TrieNode node = this.root;
        TrieNode pNode;
        String key = "";
        TrieData result = new TrieData();

        for (int i = 0; i < input.length(); i++) {
            key += input.charAt(i);
            if (input.charAt(i) == ' ') {
                continue;
            }
            pNode = node;
            node = node.getChild(input.charAt(i));
            if (node == null) {
                // symbol remove
                if (SYMBOLS.indexOf(input.charAt(i)) > -1) {
                    node = pNode;
                    continue;
                }
                // symbol addition
                for (int j = 0; j < SYMBOLS.length(); j++) {
                    node = pNode.getChild(SYMBOLS.charAt(j));
                    if (node != null) {
                        break;
                    }
                }
                if (node != null) {
                    i--;
                    continue;
                }
                break;
            }
            result.setKey(key);
            result.setLength(i);
            if (node.isTerminal()) {
                result.setValue(node.data);
            }
        }
        return result;
    }

    /**
     * Returns true if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return false.
     */
    @Override
    public boolean contains(String input) {
        TrieNode node = this.root;
        for (int i = 0; i < input.length(); i++) {
            node = node.getChild(input.charAt(i));
            if (node == null) {
                return false;
            }
            if (node.isTerminal()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the shortest substring of <code>input<code> that is
     * matched by a pattern in the ds, or <code>null<code> if no match
     * exists.
     */
    @Override
    public TrieData shortestMatch(String input) {
        TrieNode node = this.root;
        String key = "";
        for (int i = 0; i < input.length(); i++) {
            key += input.charAt(i);
            node = node.getChild(input.charAt(i));
            if (node == null) {
                return null;
            }
            if (node.isTerminal()) {
                TrieData result = new TrieData();
                result.setKey(key);
                result.setValue(node.data);
                result.setLength(i);
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the longest substring of <code>input<code> that is
     * matched by a pattern in the ds, or <code>null<code> if no match
     * exists.
     */
    @Override
    public TrieData longestMatch(String input) {
        TrieNode node = this.root;
        String key = "";
        TrieData result = new TrieData();
        for (int i = 0; i < input.length(); i++) {
            key += input.charAt(i);
            node = node.getChild(input.charAt(i));
            if (node == null) {
                break;
            }
            result.setKey(key);
            result.setLength(i);
            if (node.isTerminal()) {
                result.setValue(node.data);
            }
        }
        return result;
    }

    /**
     * Add Key, Value pair to the {@link com.skplanet.nlp.trie.AbstractTrie}
     *
     * @param key key ( must be {@link String} )
     * @param val value ( any object )
     * @return true if successed, otherwise false
     */
    @Override
    public boolean put(String key, Object val) {
        if (key == null || key.length() == 0 || val == null) {
            return false;
        }
        this.addPatternForward(key, val);
        return true;
    }


}

