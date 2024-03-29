package apap.tugaskelompok.sirekrutmen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/api/v1/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/lowongan/detail/**").hasAnyAuthority("Pelamar","Kepala Bagian","Kepala Departemen HR", "Staff Rekrutmen")
			.antMatchers("/lowongan/hapus/**").hasAnyAuthority("Kepala Bagian","Kepala Departemen HR")
			.antMatchers("/user/create/**").hasAnyAuthority("Kepala Departemen HR", "Kepala Bagian")
			.antMatchers("/pelamar/update/**").hasAnyAuthority("Pelamar")
			.antMatchers("/pelamar/create/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll()
				.and()
				.cors()
				.and()
				.csrf()
				.disable();

	}
	
//	@Autowired
//	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.passwordEncoder(encoder())
//			.withUser("nadiem").password(encoder().encode("makarim"))
//			.roles("Kepala Departemen HR");
//	}	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

}
