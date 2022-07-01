package com.oiobi.SecuritySpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oiobi.SecuritySpring.entities.User;
import com.oiobi.SecuritySpring.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Override phương thức trong class UserDetailsService
	 *
	 * @param username
	 * @return UserDetailsImpl là implements của UserDetails (UserDetails là đối
	 *         tượng Spring security sử dụng để authen và authorize)
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
}