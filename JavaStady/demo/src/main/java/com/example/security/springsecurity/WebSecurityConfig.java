package com.example.security.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.springsecurity.account.AccountService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //アクセス
		.authorizeRequests()
		//認証されたユーザーの認証情報を返却
		.antMatchers("/login", "/login-error").permitAll() 
		//"/login", "/login-error"のページに誰でも入れる
		//antMatchers:指定したパスパターンに一致するリソースを適用対象
		//permitAll:常にtrueを返却する（権限)
		.antMatchers("/**").hasRole("USER") //userしか入れない
		.and()
		.formLogin()
		//フォーム認証の適用
		.loginPage("/login").failureUrl("/login-error");
		//カスタムログインページへの遷移指定
		//カスタムログインページへの遷移不可時の遷移先指定
	}
	//"/login", "/login-error"のページ以降のページに関してはUSERのみアクセス可能。

	//変更点 ロード時に、「admin」ユーザを登録する。
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth //認証
		.userDetailsService(userService)
		//認証するユーザーを設定する
		.passwordEncoder(passwordEncoder()); 
		//パスワードをハッシュ化

		if (userService.findAllList().isEmpty()) { 
			userService.registerAdmin("admin", "secret", "admin@localhost");
			userService.registerManager("manager", "secret", "manager@localhost");
			userService.registerUser("user", "secret", "user@localhost");
		}
		//userService：DBとの処理を行ってくれる
		//findAllList():全てのユーザー情報を取得
		//isEmpty():文字列が空であるかどうかを判定
		//情報を登録するメソッド：DB内にユーザー情報がない場合、Admin・Manager・User情報を入れる。
	}
	//変更点 PasswordEncoder(BCryptPasswordEncoder)メソッド
	@Bean
	public PasswordEncoder passwordEncoder() {
		//
		return new BCryptPasswordEncoder();
	}

}