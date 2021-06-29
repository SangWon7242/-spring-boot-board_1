package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	private int lastArticleId;
	List<Article> articleList;

	HomeController() {
		lastArticleId = 0;
		articleList = new ArrayList<>();
	}

	@RequestMapping("/home/main")
	@ResponseBody
	public String showMain() {
		return "Home";
	}

	@RequestMapping("/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleList;
	}

	@RequestMapping("/home/addArticle")
	@ResponseBody
	public String addArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articleList.add(article);

		lastArticleId = id;

		return id + "번 글이 생성되었습니다.";
	}

	@RequestMapping("/home/editArticle")
	@ResponseBody
	public String editArticle(int id, String title, String body) {
		Article articleToEdit = null;

		for (Article article : articleList) {
			if (article.getId() == id) {
				articleToEdit = article;
				break;
			}
		}

		String msg;

		if (articleToEdit != null) {
			articleToEdit.setTitle(title);
			articleToEdit.setBody(body);

			msg = id + "번 글이 수정되었습니다.";
		} else {
			msg = id + "번 글이 없습니다.";
		}

		return msg;
	}


	@RequestMapping("/home/removeArticle")
	@ResponseBody
	public String removeArticle(int id) {
		Article articleToRemove = null;

		/*
		 * for(int i = 0; i< articleList.size(); i++) { Article article =
		 * articleList.get(i);
		 * 
		 * if(article.getId() == id) { articleToRemove = article; break; } }
		 */

		// 향상된 for 문
		for (Article article : articleList) {
			if (article.getId() == id) {
				articleToRemove = article;
				break;
			}
		}

		String msg;

		if (articleToRemove != null) {
			articleList.remove(articleToRemove);

			msg = id + "번 글이 삭제되었습니다.";
		} else {
			msg = id + "번 글이 없습니다.";
		}

		return msg;
	}

}

class Article {
	private int id;
	private String title;
	private String body;

	public Article(int id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
