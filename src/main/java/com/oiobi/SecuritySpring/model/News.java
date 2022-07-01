package com.oiobi.SecuritySpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class News {
	public String title;
	public String content;
	public String author;
}
