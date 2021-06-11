package com.project.chat.board;

public class MyVector {

	protected Object[] data = null;
	protected int capacity = 0;
	protected int size = 0;
	
	public MyVector(int capacity) {
		if(capacity < 0)
			throw new IllegalArgumentException("유효하지 않은 값입니다. :" + capacity);
		this.capacity = capacity;
		data = new Object[capacity];
	}
	
	public MyVector() {
		this(10);
	}
	
	public void ensureCapacity(int minCapacity) {
		int newCapacity = capacity;
		
		if(minCapacity > capacity) {
			newCapacity = capacity * 2;
		}
		
		if(minCapacity > newCapacity) {
			newCapacity = minCapacity;
		}
		
		setCapacity(newCapacity);
	}
	
	public void setCapacity(int capacity) {
		if(this.capacity == capacity) return;
		
		Object[] tmp = new Object[capacity];
	}
}
