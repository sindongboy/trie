package com.skplanet.nlp.trie;

import java.util.Set;

/**
 * Token-Based Trie Implementation, which extends {@link AbstractTokenTrie}
 * <br>Created by Donghun Shin<br>
 * Contact: donghun.shin@sk.com, sindongboy@gmail.com<br>
 * Date: 7/12/13<br>
 */
public class TokenTrie extends AbstractTokenTrie {

    /**
     * Constructor
     */
    public TokenTrie() {
        super();
    }

    public TokenTrie(TokenTrie obj) {
        super();
    }

    /**
     * Exact Match<br>
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
     */
    @Override
    public Object match(String[] input) {
        TrieNode node = this.root;
        Object result = null;
        int i = 0;
        while (i < input.length) {
            node = node.getChild(input[i]);
            if (node == null) {
                return null;
            }
            if (node.isTerminal()) {
                result =  node.data;
            }
            i++;
        }
        return result;
    }

	/**
	 * Exact Match<br>
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
	 *
	 * @param input query tokens
	 * @param offset start index
	 * @return matched object
	 */
	public Object match(String[] input, int offset) {
        TrieNode node = this.root;
        Object result = null;
        int i = offset;
        while (i < input.length) {
            node = node.getChild(input[i]);
            if (node == null) {
                return null;
            }
            if (node.isTerminal()) {
                result =  node.data;
            }
            i++;
        }
        return result;
	}

	/**
	 * Exact Match<br>
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
	 *
	 * @param input query tokens
	 * @param skipPoint skip index
	 * @return matched object
	 */
	public Object RelaxedMatch(String[] input, Set<Integer> skipPoint) {
        TrieNode node = this.root;
        Object result = null;
        int i = 0;
        while (i < input.length) {
			if(skipPoint.contains(i)) {
				i++;
				continue;
			}
            node = node.getChild(input[i]);
            if (node == null) {
                return null;
            }
            if (node.isTerminal()) {
                result =  node.data;
            }
            i++;
        }
        return result;
	}

	/**
	 * Exact Match<br>
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
	 *
	 * @param input query tokens
	 * @param offset start index
	 * @param skipPoint skip index
	 * @return matched object
	 */
	public Object RelaxedMatch(String[] input, int offset, Set<Integer> skipPoint) {
        TrieNode node = this.root;
        Object result = null;
        int i = offset;
        while (i < input.length) {
			if(skipPoint.contains(i)) {
				i++;
				continue;
			}
            node = node.getChild(input[i]);
            if (node == null) {
                if (result != null) {
                    return result;
                }
                return null;
            }
            if (node.isTerminal()) {
                result =  node.data;
            }
            i++;
        }
        return result;
	}


    /**
     * Prefix Token Trie Match
     * @param input String to be looked up.
     * @return end index of matching part
     */
    public int prefixMatch(String[] input) {
        TrieNode node = this.root;
        int i = 0;
        while (i < input.length) {
            node = node.getChild(input[i]);
            if (node == null) {
                return i;
            }
            i++;
        }
        return i;
    }

	/**
	 * Prefix Token Trie Match with starting offset
	 *
	 * @param input input string to be looked up.
	 * @param offset start index 
	 * @return end index of matching part
	 */
	public int prefixMatch(String [] input, int offset) {
		TrieNode node = this.root;
		int i = offset;
		while (i < input.length) {
			node = node.getChild(input[i]);
			if (node == null) {
				return i;
			}
			i++;
		}
		return i;

	}

	/**
	 * Prefix Token Trie Match with starting offset
	 *
	 * @param input input string to be looked up.
	 * @param offset start index 
	 * @param skipPoint skip index
	 * @return end index of matching part
	 */
	public int prefixRelaxedMatch(String [] input, int offset, Set<Integer> skipPoint) {
		TrieNode node = this.root;
		int i = offset;
		while (i < input.length) {
			if(skipPoint.contains(i)) {
				i++;
				continue;
			}
			node = node.getChild(input[i]);
			if (node == null) {
				return i;
			}
			i++;
		}
		return i;
	}


	/**
	 * Returns true if the given <code>String</code> is matched by a
	 * pattern in the ds, otherwise return false.
	 */
	@Override
	public boolean contains(String[] input) {
		TrieNode node = this.root;
		int i = 0;
		while (i < input.length) {
			node = node.getChild(input[i]);
			if (node == null) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * Returns the shortest substring of <code>input<code> that is
	 * matched by a pattern in the ds, or <code>null<code> if no match
	 * exists.
	 */
	@Override
	public Object shortestMatch(String[] input) {
		// TODO : shortest token trie match implementation
		return null;
	}

	/**
	 * Returns the longest substring of <code>input<code> that is
	 * matched by a pattern in the ds, or <code>null<code> if no match
	 * exists.
	 */
	@Override
	public Object longestMatch(String[] input) {
		// TODO : longest token trie match implementation
		return null;
	}

	/**
	 * Add Key, Value pair to the {@link com.skplanet.nlp.trie.Trie}
	 *
	 * @param key key ( must be {@link String} )
	 * @param val value ( any object )
	 * @return true if successed otherwise false
	 */
	@Override
	public boolean put(String []key, Object val) {
		if (key.length == 0 || val == null) {
			return false;
		}
		addPatternForward(key, val);
		return true;
	}

}
