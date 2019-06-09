package top.pyg.shop.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import top.pyg.manager.service.SellerService;
import top.pyg.pojo.TbSeller;


public class UserDetailServiceImpl implements UserDetailsService {

	private SellerService sellerService;
	
	
	public SellerService getSellerService() {
		return sellerService;
	}


	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//定义角色封装集合 
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//添加角色
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		//调用商家服务对象
		TbSeller seller = sellerService.findOne(username);
		
		return new User(username, seller.getPassword(), authorities);
	}

	
}
