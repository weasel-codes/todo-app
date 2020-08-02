package co.nitin.todo.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import co.nitin.todo.model.entity.User;

public class SecurityUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private User user;
	private List<String> userRoles;
	
	public SecurityUserDetails(User user, List<String> userRoles) {
		super();
		this.user = user;
		this.userRoles = userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		String authority = StringUtils.collectionToCommaDelimitedString(this.userRoles); //roles to comma strings
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authority); //comma string to authority
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
