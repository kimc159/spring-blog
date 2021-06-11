package com.project.chat.board;

import java.util.*;

public class ArrayCopy{

	public static void main(String[] args) {
		String[] data = {"A", "K", "A", "K", "D", "K", "D"};
		
		HashMap map = new HashMap();
		
		for(int i=0; i<data.length; i++) {
			if(map.containsKey(data[i])) {
				Integer value = (Integer)map.get(data[i]);
			}
		}
		
	}
	
	static int[] shallowCopy(int[] arr1) {
		return arr1;
	}
	
	static int[] deepCopy(int[] arr1) {
		if(arr1 == null) return null;
		int[] result = new int[arr1.length];
		
		System.arraycopy(arr1, 0, result, 0, arr1.length);
		return result;
	}
	
}

