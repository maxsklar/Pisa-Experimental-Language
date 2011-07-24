package com.github.pisa.model.node;

public class PisaObjectPrimitive {
	
	public static class Boolean implements PisaNode {
		final private boolean x;
		
		public Boolean(boolean x) { this.x = x; }
		
		@Override
		public int size() {
			return x ? 1 : 0;
		}

		@Override
		public long get(int i) {
			return x ? -1: 0;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}
	
	public static class Byte implements PisaNode {
		final private byte x;
		
		public Byte(byte x) { this.x = x; }
		
		@Override
		public int size() {
			return 8;
		}

		@Override
		public long get(int i) {
			return (((x >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}
	
	public static class Char implements PisaNode {
		final private char x;
		
		public Char(char x) { this.x = x; }
		
		@Override
		public int size() {
			return 16;
		}

		@Override
		public long get(int i) {
			return (((x >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}	
	
	public static class Short implements PisaNode {
		final private short x;
		
		public Short(short x) { this.x = x; }
		
		@Override
		public int size() {
			return 16;
		}

		@Override
		public long get(int i) {
			return (((x >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}
	
	
	public static class Integer implements PisaNode {
		final private int x;
		
		public Integer(int x) { this.x = x; }
		
		@Override
		public int size() {
			return 32;
		}

		@Override
		public long get(int i) {
			return (((x >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}	
	
	public static class Long implements PisaNode {
		final private long x;
		
		public Long(long x) { this.x = x; }
		
		@Override
		public int size() {
			return 64;
		}

		@Override
		public long get(int i) {
			return (((x >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}	
	public static class Float implements PisaNode {
		final private float x;
		
		public Float(float x) { this.x = x; }
		
		@Override
		public int size() {
			return 32;
		}

		@Override
		public long get(int i) {
			return (((java.lang.Float.floatToRawIntBits(x) >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}	
	public static class Double implements PisaNode {
		final private double x;
		
		public Double(double x) { this.x = x; }
		
		@Override
		public int size() {
			return 64;
		}

		@Override
		public long get(int i) {
			return (((java.lang.Double.doubleToRawLongBits(x) >> i) & 1) == 0)? 0 : 1;
		}

		@Override
		public String getParameterName() {
			return null;
		}
	}
}
