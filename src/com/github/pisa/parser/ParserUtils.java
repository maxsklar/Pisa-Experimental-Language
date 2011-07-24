package com.github.pisa.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import com.github.pisa.model.PisaNamespace;

public class ParserUtils {
	
	static public void doAssignment(PisaNamespace ns, String var, String exp) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new StringReader(exp));
		
		while(st.nextToken() != StreamTokenizer.TT_EOF);
		
		System.out.println(st.nval);
		System.out.println(st.sval);
		System.out.println(st.ttype);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(StreamTokenizer.TT_NUMBER);
		
		while(true) {
			System.out.print("==> ");
			String line = br.readLine();
			System.out.println();
			if (line.equalsIgnoreCase("quit")) break;
			
			StreamTokenizer st = new StreamTokenizer(new StringReader(line));
			
			while(st.nextToken() != StreamTokenizer.TT_EOF) {			
				System.out.println(st.nval);
				System.out.println(st.sval);
				System.out.println(st.ttype);
				System.out.println();
			}
		}
	}
}
