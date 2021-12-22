package edu.miracosta.cs113;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
	
	Node<Character> root = new Node<Character>(null);
	
	
	public MorseCodeTree()
	{
		buildMorseTree();
	}
	
	public String print()
	{
		return root.toString();
	}
	
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
        Node<Character> current = root;
        String message = "";
        
        
        String[] codes = morseCode.split(" ");
        
        for (String s : codes)
        {
	        if (s.length() > 4 || s.length() < 1)
	        {
	        	throw new IllegalArgumentException();
	        }
	        
	        for (int i = 0; i < s.length(); i++)
	        {
	        	if (s.charAt(i) != '*' || s.charAt(i) != '-')
	        	{
	        		throw new IllegalArgumentException();
	        	}
	        }
	        
	        for (int j = 0; j < s.length(); j++)
	        {
	        	if (s.charAt(j) == '*')
	        	{
	        		current = current.left;
	        	}
	        	
	        	else if(s.charAt(j) == '-')
	        	{
	        		current = current.right;
	        	}
	        }
	        
	        message.concat(current.data.toString());
        }
       
        return message;
    	
    	
    }
    
    
    private void buildMorseTree()
    {
	    File file = new File("C:\\Users\\Tony\\Documents\\GitHub\\DS-MorseCodeTree\\src\\edu\\miracosta\\cs113\\MorseCode.txt");
	    String input;
	    char letter;
	    String code;
	    try
	    {
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	    	
	    	 while ((input = br.readLine()) != null)
	         {
	         	letter = input.charAt(0);
	         	code = input.substring(2);
	         	
	         	add(letter, code);	         	
	         }
	    	 
	         br.close();
	    }
	    catch (Exception e)
	    {
	    	System.out.println("File not found.");
	    }
    }
    
    public void add(char symbol, String code){
		Node<Character> current = root;
		for (int i = 0; i < code.length(); i++)
		{
			if (code.substring(i, i + 1).equals("*"))
			{
				if (current.left == null)
				{
					current.left = new Node<Character>(null);
				}
				current = current.left;
			}
			else if (code.substring(i, i + 1).equals("-"))
			{
				if (current.right == null)
				{
					current.right = new Node<Character>(null);
				}
				current = current.right;
			}
		}
		current.data = symbol;
	}

} // End of class MorseCodeTree