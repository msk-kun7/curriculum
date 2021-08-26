package com.example.security.springsecurity.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//問４－１ コントローラーを意味するアノテーションを記述
@Controller //画面遷移用のコントローラーに付与
public class AuthController {

	@Autowired //特定のアノテーションを付与したクラスのインスタンスを使用できるようにする
	private AccountRepository repository;

	public List<Account> get() {
		return (List<Account>) repository.findAll();
	}

	@RequestMapping("/") //{/}で動的にパラメーターを取得
	public String index() {
		return "redirect:/top";
	}

	@GetMapping("/login") //HTTPリクエストのGETメソッドを受け付けるためのメソッドに付与
	public String login() {
		return "login";
	}

	@PostMapping("/login") //HTTPリクエストのPOSTメソッドを受け付けるためのメソッドに付与
	public String loginPost() {
		return "redirect:/login-error";
	}

	@GetMapping("/login-error") //HTTPリクエストのGETメソッドを受け付けるためのメソッドに付与
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping("/top") //{/top}で動的にパラメーターを取得
	public String top() {
		return "/top";
	}

}
 