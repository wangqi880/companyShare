package com.rabbitmq.listener;

import java.io.Serializable;

public class User {
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	private String name;
	private int age;
	
	public User() {}
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
