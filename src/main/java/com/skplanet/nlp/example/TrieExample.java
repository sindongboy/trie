package com.skplanet.nlp.example;

import com.skplanet.nlp.trie.TokenTrie;
import com.skplanet.nlp.trie.Trie;

import java.util.HashSet;

/**
 * Sample demo program for {@link Trie}<br></br>
 * <br>Created by Donghun Shin<br>
 * Contact: donghun.shin@sk.com, sindongboy@gmail.com<br>
 * Date: 7/12/13<br>
 */
public class TrieExample {
    public static void main(String[] args) {

        //----------------------
        // General Trie Test.
        //----------------------
        System.out.println("General Trie Test");

        // Trie Declaration.
        Trie trie = new Trie();

        // add item to the trie
        //trie.put("abc", "abc");
        trie.put("abc한글", "abc한글");
        System.out.println("\"abc한글\" is now added to the trie!");

        // try general matching
        System.out.println("call \"match()\" : ");
        System.out.print("try : \"abd\" ==> ");
        printResult(trie.match("abd"));
        System.out.print("try : \"abc\" ==> ");
        printResult(trie.prefixMatch("abc"));
        System.out.println();


        // try longest match
        System.out.println("call \"prefixMatch()\" : ");
        int offset = trie.prefixMatch("abcde").getLength();
        System.out.println("try : \"abcde\" ==> " + offset);
        offset = trie.prefixMatch("abdec").getLength();
        System.out.println("try : \"abdec\" ==> " + offset);
        offset = trie.prefixMatch("ab c한글de").getLength();
        System.out.println("try : \"ab c한글de\" ==> " + offset);
        System.out.println();


        //----------------------
        // Token Trie Test.
        //----------------------
        System.out.println("Token Trie Test");

        // Token Trie Declaration.
        TokenTrie tokenTrie = new TokenTrie();
        String[] key = {"abc", "def", "ghi"};
        String val = "abc def ghi";

        System.out.println("Key: \"abc def ghi\" added");
        tokenTrie.put(key, val);

        System.out.print("Try matching : \"abc def zhi\" ==> ");
        String[] q1 = {"abc", "def", "zhi"};
        printResult(tokenTrie.match(q1));

        System.out.print("Try matching : \"abc def ghi\" ==> ");
        String[] q2 = {"abc", "def", "ghi"};
        printResult(tokenTrie.match(q2));

        System.out.print("Try prefix-matching : \"abc def ghi jkl mlp\" ==> ");
		String[] q3 = {"abc", "def", "ghi", "jkl","mlp"};
		offset = tokenTrie.prefixMatch(q3);
		System.out.println("Matching offset: " + offset);

        System.out.print("Try prefix-relaxed matching : \"abc def ghi jkl mlp\" ==> ");
		String[] q4 = {"abc", "def", "ghi", "jkl","mlp"};
		key = new String[]{"abc", "def", "ghi", "mlp"};
		tokenTrie.put(key , "abc def ghi jkl mlp");
		HashSet<Integer> skipPoint = new HashSet<Integer>();
		skipPoint.add(3);
		offset = tokenTrie.prefixRelaxedMatch(q4, 0, skipPoint);
		System.out.println("Matching offset: " + offset);

        System.out.print("Try relaxed matching : \"abc def ghi jkl mlp\" ==> ");
        printResult(tokenTrie.RelaxedMatch(q4, skipPoint));
    }

    static void printResult(Object res) {
        if (res == null) {
            System.out.println("Not Matched");
        } else {
            System.out.println("Matched");
        }
    }
}
