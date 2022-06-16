package com.clean.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;


@Component
public class JwtConverter implements Converter<Jwt, Collection<GrantedAuthority>> { @Override
	public Collection<GrantedAuthority> convert(Jwt token) {
		Map<String, Object> realmsAcess = token.getClaimAsMap("realm_access");
		if(realmsAcess == null || realmsAcess.isEmpty()) {
			return new ArrayList<GrantedAuthority>();
		}
		List<GrantedAuthority> auths = ((List<String>) realmsAcess.get("roles"))
				.stream()
				.map(r -> "ROLE_" + r)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		String scopes = token.getClaim("scope");
		StringTokenizer st = new StringTokenizer(scopes);
		while(st.hasMoreTokens()) {
			auths.add(new SimpleGrantedAuthority("SCOPES_" + st.nextToken()));
		}
		return auths;
	}
}

