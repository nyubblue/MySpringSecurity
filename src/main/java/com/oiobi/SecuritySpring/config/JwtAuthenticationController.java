package com.oiobi.SecuritySpring.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oiobi.SecuritySpring.entities.Permission;
import com.oiobi.SecuritySpring.entities.Role;
import com.oiobi.SecuritySpring.entities.User;
import com.oiobi.SecuritySpring.service.UserDetailsServiceImpl;
import com.oiobi.SecuritySpring.service.UserManagementService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		Permission permission1 = new Permission(null, "read");
		Permission permission2 = new Permission(null, "write");
		Permission permission3 = new Permission(null, "excute");
		Set<Permission> permissions = new HashSet<Permission>();
		permissions.add(permission1);
		permissions.add(permission2);
		permissions.add(permission3);
		Role role = new Role(null, "admin", permissions);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		User user = new User(null, "buynlt", "bin159@gmail.com", "abc123", roles);
//		userManagementService.registerUser(user);
		System.out.println(user);

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
