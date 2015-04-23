package load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.commons.lang.StringUtils;

public class Solution {
	
	public static void main(String[] args) {

		String a = moveIt("7N3WX");
		if (!a.equals("2,7")) {
			System.out.println("ERRO A");
		}
		String b = moveIt("7N2X6S3WX");
		if (!b.equals("2,-1")) {
			System.out.println("ERRO B");
		}
		String c = moveIt("7N8X3W2XEE");
		if (!c.equals("-1,0")) {
			System.out.println("ERRO C");
		}
		String d = moveIt("NWE");
		if (!d.equals("0,1")) {
			System.out.println("ERRO D");
		}
		
	}
	
	/*
	 * Makes the movement.
	 * 
	 * 
	 * */
	public static String moveIt(String command) {
		
		Map<String,Integer> commandsMapped = new HashMap<>();
		commandsMapped.put("N", 0);
		commandsMapped.put("S", 0);
		commandsMapped.put("W", 0);
		commandsMapped.put("E", 0);
		
		List<String> splittedCommands = splitTheCommands(command);
		
		String sequence = transformIt(splittedCommands);
		
		sequence = removeX(sequence);
		
		String movement = calculateIt(sequence);
		
		return movement;
		
	}
	
	private static String calculateIt(String sequence) {
		Map<String,Integer> commandsMapped = new HashMap<>();
		commandsMapped.put("N", 0);
		commandsMapped.put("S", 0);
		commandsMapped.put("W", 0);
		commandsMapped.put("E", 0);
		
		for (int i=0; i < sequence.length(); i++) {
			String sub = sequence.substring(i, i+1);
			commandsMapped.put(sub,commandsMapped.get(sub)+1);
		}

		int x = commandsMapped.get("W") -  commandsMapped.get("E");
		int y = commandsMapped.get("N") -  commandsMapped.get("S");
		
		return x + "," + y;
	}

	private static String removeX(String sequence) {

		int indexOf = sequence.toUpperCase().indexOf("X");
		int lastIndexOf = sequence.toUpperCase().lastIndexOf("X");
		
		while (indexOf > 0 || indexOf != lastIndexOf) {
			
			if (indexOf == 0) {
				sequence = sequence.substring(indexOf+1);
			} else {
				sequence = sequence.substring(0, indexOf-1) + sequence.substring(indexOf+1);
			}
			
			indexOf = sequence.toUpperCase().indexOf("X");
			lastIndexOf = sequence.toUpperCase().lastIndexOf("X");
			
		}
		
		return sequence;
	}

	private static String transformIt(List<String> splittedCommands) {
		
		StringBuilder sb = new StringBuilder();
		
		for (String command : splittedCommands) {
			if (command.length() > 1) {
				
				Integer factor = Integer.parseInt(command.substring(0, command.length() - 1));
				
				sb.append(repeat(command.substring(command.length() - 1), factor));
				
			} else {
				sb.append(command);
			}
		}
		
		return sb.toString();
		
	}

	private static String repeat(String substring, Integer factor) {
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<factor; i++) {
			sb.append(substring);
		}
		
		return sb.toString();
	}

	/**
	 * @param command
	 */
	private static List<String> splitTheCommands(String command) {
		List<String> splittedCommands = new ArrayList<>();
		
		StringBuilder sequence = new StringBuilder();
		
		/*
		 * Moves through the command
		 * */
		for (int i=0; i < command.length(); i++) {
			
			String sub = command.substring(i, i+1);			
			
			sequence.append(sub);
			
			if (!isNumeric(sub)) {

				splittedCommands.add(sequence.toString());
				sequence = new StringBuilder();
			}
			
			
		}
		
		return splittedCommands;
	}

	private static boolean isNumeric(String sub) {
		try {
			Integer.parseInt(sub);
			return true;
		} catch (Exception e) {}
		return false;
	}
	
}
