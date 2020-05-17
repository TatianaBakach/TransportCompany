package by.itacademy.tatabakach.transportcompany.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IEmployeeService employeeService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

		IEmployee employee = employeeService.getByUserName(username);
		if (employee == null) {
			throw new BadCredentialsException("1000");
		}

		
		//TODO использовать зашифрованные пароли
		if (!employee.getPassword().equals(password)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = employee.getId(); 

		List<String> userRoles = new ArrayList<>();
		// TODO get list of user's
// roles
		userRoles.add("ROLE_" + "admin"); 
		// !!! ROLE_ prefix is required

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}

		ExtendedToken token = new ExtendedToken(username, password, authorities);
		token.setId(userId);
		return token;

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}