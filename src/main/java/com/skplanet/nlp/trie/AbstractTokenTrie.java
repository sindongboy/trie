package com.skplanet.nlp.trie;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Token-based Trie Implementation
 * <br>Created by Donghun Shin<br>
 * Contact: donghun.shin@sk.com, sindongboy@gmail.com<br>
 * Date: 7/12/13<br>
 */
public abstract class AbstractTokenTrie {
    protected TrieNode root;

    protected AbstractTokenTrie() {
        this.root = new TrieNode("", false, null);
    }

    /**
     * Node class for the character tree.
     */
    protected class TrieNode implements Comparable<TrieNode> {
        protected TrieNode[] children;
        protected LinkedList<TrieNode> childrenList;
        //protected char nodeChar;
        protected String nodeStr;

        protected boolean terminal;
        protected Object data;

        /**
         * Creates a new TrieNode, which contains the given
         * <code>nodeChar</code>.  If <code>isTerminal</code> is
         * <code>true</code>, the new node is a <em>terminal</em> node in
         * the ds.
         */
        //TrieNode(char nodeChar, boolean isTerminal, Object data) {
        TrieNode(String nodeStr, boolean isTerminal, Object data) {
            //this.nodeChar= nodeChar;
            this.nodeStr = nodeStr;
            this.terminal = isTerminal;

            //--> added by Donghun Shin. : Dec. 13. 2013.
            if (data != null) {
                this.data = data;
            } else {
                this.data = null;
            }
            //--> added by Donghun Shin.

            this.childrenList = new LinkedList<TrieNode>();
        }

        /**
         * Returns <code>true</code> if this node is a <em>terminal</em>
         * node in the ds.
         */
        boolean isTerminal() {
            return terminal;
        }

        /**
         * Returns the child node of this node whose node-character is
         * <code>nextChar</code>.  If no such node exists, one will be is
         * added.  If <em>isTerminal</em> is <code>true</code>, the node
         * will be a terminal node in the ds.
         */
        //TrieNode getChildAddIfNotPresent(char nextChar, boolean isTerminal, Object data) {
        TrieNode getChildAddIfNotPresent(String nextStr, boolean isTerminal, Object data) {
            if (childrenList == null) {
                childrenList = new LinkedList<TrieNode>();
                childrenList.addAll(Arrays.asList(children));
                children = null;
            }

            if (childrenList.size() == 0) {
                TrieNode newNode = new TrieNode(nextStr, isTerminal, data);
                childrenList.add(newNode);
                return newNode;
            }

            ListIterator<TrieNode> iter = childrenList.listIterator();
            TrieNode node = iter.next();
            //while ( (node.nodeChar < nextChar) && iter.hasNext() )
            while (isLessThan(node.nodeStr, nextStr) && iter.hasNext())
                node = iter.next();

            //if (node.nodeChar == nextChar) {
            if (isEqual(node.nodeStr, nextStr)) {
                node.terminal = node.terminal | isTerminal;
                if (isTerminal)
                    node.data = data;
                return node;
            }

            //if (node.nodeChar > nextChar)
            if (isGreaterThan(node.nodeStr, nextStr))
                iter.previous();

            //TrieNode newNode= new TrieNode(nextChar, isTerminal, data);
            TrieNode newNode = new TrieNode(nextStr, isTerminal, data);
            iter.add(newNode);
            return newNode;
        }

        /**
         * Returns the child node of this node whose node-character is
         * <code>nextChar</code>.  If no such node exists,
         * <code>null</code> is returned.
         */
        //TrieNode getChild(char nextChar) {
        TrieNode getChild(String nextStr) {
            if (children == null) {
                children = childrenList.toArray(new TrieNode[childrenList.size()]);
                childrenList = null;
                Arrays.sort(children);
            }

            int min = 0;
            int max = children.length - 1;
            int mid;
            while (min < max) {
                mid = (min + max) / 2;
                //if (children[mid].nodeChar == nextChar)
                if (isEqual(children[mid].nodeStr, nextStr))
                    return children[mid];
                if (isLessThan(children[mid].nodeStr, nextStr))
                    min = mid + 1;
                else // if (children[mid].nodeChar > nextChar)
                    max = mid - 1;
            }

            if (min == max)
                //if (children[min].nodeChar == nextChar)
                if (isEqual(children[min].nodeStr, nextStr))
                    return children[min];

            return null;
        }

        public int compareTo(TrieNode other) {
            //if (this.nodeChar < other.nodeChar)
            if (isLessThan(this.nodeStr, other.nodeStr))
                return -1;
            if (isEqual(this.nodeStr, other.nodeStr))
                return 0;
//    if (this.nodeChar > other.nodeChar)
            return 1;
        }
    }

    protected boolean isEqual(String a, String b) {
        int val = compareNode(a, b);
        return val == 0;
    }

    protected boolean isLessThan(String a, String b) {
        int val = compareNode(a, b);
        return val == -1;
    }

    protected boolean isGreaterThan(String a, String b) {
        int val = compareNode(a, b);
        return val == 1;
    }

    protected int compareNode(String a, String b) {
        int val = a.compareTo(b);

        if(val > 0)
            return 1;
        else if(val < 0)
            return -1;
        else
            return 0;
        /*
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int aval = 0;
        for (char c : ac) {
            aval += c;
        }
        int bval = 0;
        for (char c : bc) {
            bval += c;
        }

        if (aval < bval) {
            return -1;
        } else if (aval == bval) {
            return 0;
        } else {
            return 1;
        }
        */
    }

    /**
     * Returns the next {@link TrieNode} visited, given that you are at
     * <code>node</code>, and the the next character in the input is
     * the <code>idx</code>'th character of <code>s</code>.
     */
    /*
    protected final TrieNode matchChar(TrieNode node, String s, int idx) {
        return node.getChild(s.charAt(idx));
    }
    */

    /**
     * Adds any necessary nodes to the ds so that the given
     * <code>String</code> can be decoded and the last character is
     * represented by a terminal node.  Zero-length <code>Strings</code>
     * are ignored.
     */
    //protected final void addPatternForward(String s, Object d) {
    protected final void addPatternForward(String[] s, Object d) {
        TrieNode node = root;
        //int stop= s.length() - 1;
        int stop = s.length - 1;
        int i;
        //if (s.length() > 0) {
        if (s.length > 0) {
            for (i = 0; i < stop; i++)
                //node= node.getChildAddIfNotPresent(s.charAt(i), false, null);
                node = node.getChildAddIfNotPresent(s[i], false, null);
            //node= node.getChildAddIfNotPresent(s.charAt(i), true, d);
            node = node.getChildAddIfNotPresent(s[i], true, d);
        }
    }

    /**
     * Adds any necessary nodes to the ds so that the given
     * <code>String</code> can be decoded <em>in reverse</em> and the
     * first character is represented by a terminal node.  Zero-length
     * <code>Strings</code> are ignored.
     */
    //protected final void addPatternBackward(String s, Object d) {
    protected final void addPatternBackward(String[] s, Object d) {
        TrieNode node = root;
        if (s.length > 0) {
            for (int i = s.length - 1; i > 0; i--)
                //node= node.getChildAddIfNotPresent(s.charAt(i), false, null);
                node = node.getChildAddIfNotPresent(s[i], false, null);
            //node= node.getChildAddIfNotPresent(s.charAt(0), true, d);
            node = node.getChildAddIfNotPresent(s[0], true, d);
        }
    }

    /**
     * Returns data if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return null.
     */
    public abstract Object match(String[] input);

    /**
     * Returns true if the given <code>String</code> is matched by a
     * pattern in the ds, otherwise return false.
     */
    public abstract boolean contains(String[] input);


    /**
     * Returns the shortest substring of <code>input<code> that is
     * matched by a pattern in the ds, or <code>null<code> if no match
     * exists.
     */
    public abstract Object shortestMatch(String[] input);

    /**
     * Returns the longest substring of <code>input<code> that is
     * matched by a pattern in the ds, or <code>null<code> if no match
     * exists.
     */
    public abstract Object longestMatch(String[] input);

    /**
     * Add Key, Value pair to the {@link Trie}
     *
     * @param key key ( must be {@link String} )
     * @param val value ( any object )
     * @return true if successed otherwise false
     */
    public abstract boolean put(String []key, Object val);
}
