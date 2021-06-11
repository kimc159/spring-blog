package com.project.chat.board;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		byte[] temp = new byte[10];
		
		input.read(temp, 0, temp.length);
		output.write(temp, 5, 5);
		
		
		outSrc = output.toByteArray();
		
		System.out.println(Arrays.toString(inSrc));
		System.out.println(Arrays.toString(temp));
		System.out.println(Arrays.toString(outSrc));
	}
}
