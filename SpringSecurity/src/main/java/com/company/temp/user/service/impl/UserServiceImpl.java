package com.company.temp.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.temp.user.service.UserService;
import com.company.temp.user.service.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired UserMapper dao;

	@Override
	public int insertUser(UserVO vo) {
		return dao.insertUser(vo);
	}

	@Override
	public int updateUser(UserVO vo) {
		return dao.updateUser(vo);
	}

	@Override
	public int deleteUser(UserVO vo) {
		return dao.deleteUser(vo);
	}

	@Override
	public UserVO getSearchUser(UserVO vo) {
		return dao.getSearchUser(vo);
	}

	@Override
	public List<UserVO> getUser(UserVO vo) {
		return dao.getUser(vo);
	}

	@Override
	public boolean logCheck(UserVO vo) {
		//단건조회
		UserVO userVO = dao.getSearchUser(vo);
		//id가 일치하는지 체크
		if (userVO == null) {
			return false;
		}
		//pw 일치여부 체크
		if (userVO.getPassword().equals(vo.getPassword())) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public int updatePw(UserVO vo) {
		//단건조회
		UserVO userVO = dao.getSearchUser(vo);
		//기존패스워드와 일치하는지 체크
		if (userVO.getPassword().equals(vo.getOldpassword())) {
			return dao.updatePw(vo);
		//패스워드 업데이트
		} else {
			return 0;
		}		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("=========userservice 실행");
		UserVO userVO = new UserVO();
		userVO.setId(username);
		userVO = dao.getSearchUser(userVO);
		
		if(userVO == null) {
			throw new UsernameNotFoundException("no user");
		}
		
		return userVO;
	}
}
