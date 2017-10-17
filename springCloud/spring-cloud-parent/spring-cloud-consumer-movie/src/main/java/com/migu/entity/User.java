package com.migu.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class User {

	private Long id;

	private String username;

	private String name;

	private Short age;

	private BigDecimal balance;
}
