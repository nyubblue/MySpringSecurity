package com.oiobi.SecuritySpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oiobi.SecuritySpring.model.News;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

	@RequestMapping(method = RequestMethod.GET)
	public List<News> news() {
		// Tham khảo FakeUtils mình có viết trong package Utils để fake dữ liệu nhé
		return getAllViews();
	}

	private List<News> getAllViews() {
		List<News> newsList = new ArrayList<News>();
		newsList.add(new News("Title 1", "Framgia - From asian to the world", "Hong Son"));
		newsList.add(new News("Title 2", "Framgia - We make it awesome", "Son Nguyen"));
		newsList.add(new News("Title 3", "Framgia - 2012 to 2017", "Mr Son"));


		return newsList;
	}
}