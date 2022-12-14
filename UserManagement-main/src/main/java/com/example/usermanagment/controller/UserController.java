package com.example.usermanagment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.usermanagment.dao.AddressDao;
import com.example.usermanagment.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.usermanagment.dao.UserDao;
import com.example.usermanagment.model.User;
import com.example.usermanagment.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {	

	
	@Autowired
	UserDao userDao;

	@Autowired
	AddressDao addressDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping
	public String getAllUser(Model model) {
		model.addAttribute("users", userDao.getUsers(""));
		return "users";
	}	
	
	
	@GetMapping("/profile")
	public ModelAndView viewProfile(ModelAndView model,HttpSession session) {
		model.addObject(userDao.findUserByUsername(session.getAttribute("username").toString()));
		model.addObject("addressList", addressDao.getUserAddress(session.getAttribute("username").toString()));
		model.setViewName("profile");
		return model;

	}
	
	@PostMapping("/profile")
	public ModelAndView updateProfile(HttpServletRequest request,ModelAndView model,HttpSession session,@ModelAttribute("user")  User updatedUser,
	        BindingResult result,@RequestParam(value = "delete_arr[]", required=false) List<Integer> to) {

 addressDao.deleteAddreses(to);



	User user = userDao.findUserByUsername(updatedUser.getUsername());
		user.setName(updatedUser.getName());
		user.setEmail(updatedUser.getEmail());
		user.setMobileNo(updatedUser.getMobileNo());
		user.setGender(updatedUser.getGender());
		userDao.saveUser(user);


		if (request.getParameter("count") != null && to!=null) {

			int count = Integer.parseInt(request.getParameter("count")+to.size());

			for (int i = 1; i <= count; i++) {
				Address add = new Address();
				if (request.getParameter("id" + i)!=null) {

					if (Integer.parseInt(request.getParameter("id" + i)) > 0) {
						add.setId(Integer.parseInt(request.getParameter("id" + i)));

					}
				
				add.setUser(user);
				add.setLine1(request.getParameter("line1" + i));
				add.setLine2(request.getParameter("line2" + i));
				add.setState(request.getParameter("state" + i));
				add.setCity(request.getParameter("city" + i));
				add.setPincode(request.getParameter("pincode" + i));
				addressDao.saveAddress(add);
				}
		}}
	    model.addObject("success", true);
	    model.addObject(user);
		model.addObject("addressList", addressDao.getUserAddress(session.getAttribute("username").toString()));

	    model.setViewName("profile");
		return model;
	}
	

	@PostMapping("/updateUserData")
	public ModelAndView updateUserData(ModelAndView model,HttpSession session) {
		model.addObject(userDao.findUserByUsername(session.getAttribute("username").toString()));
		model.addObject("addressList", addressDao.getUserAddress(session.getAttribute("username").toString()));
		model.setViewName("profile");
		return model;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/deleteUser")
	public String deleteUser(Model model,HttpServletRequest request) {
		String username=request.getParameter("deleteUsername");
		userDao.deleteUser(username);
		model.addAttribute("users", userDao.getUsers(""));
		return "users";
	}
	
	
	@GetMapping("/changePassword")
	public String getChangePassword() {
		return "changePassword";
	}
	
	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView model,HttpServletRequest request,HttpSession session,BindingResult result) {
		
		String newPassword=request.getParameter("newPassword").toString();
		String oldPassword=request.getParameter("oldPassword").toString();
		String confirmPassword=request.getParameter("confirmPassword").toString();
		model.setViewName("changePassword");
		boolean error=false;
		if(oldPassword.isEmpty()) {
			model.addObject("oldpasserror", "Enter old Password");
			error=true;
        }
		if(newPassword.isEmpty()) {
			model.addObject("newpasserror", "Enter new Password");
			error=true;
        }
		if(confirmPassword.isEmpty()) {
			model.addObject("cnfpasserror", "Enter Confirm Password");
			error=true;
        }
        if(!newPassword.equals(confirmPassword)) {
        	model.addObject("cnfpasserror",  "Password not matched");
        	error=true;
        }
        if(oldPassword.equals(newPassword)) {
        	model.addObject("newpasserror", "use different password then old one");
        	error=true;
        }

       
       String username= session.getAttribute("username").toString();
       
       User user=userDao.findUserByUsername(username);
       
       if(!passwordEncoder.matches(oldPassword,user.getPassword())) {
    	   model.addObject("oldpasserror", "Enter Valid old Password");
    	   error=true;
       }
       
       
       if(error) {
    	   return model;
           }
       
       user.setPassword(passwordEncoder.encode(newPassword));
       userDao.saveUser(user);
        model.addObject("success", true)	;	
		
		return model;
	}
	
	@PostMapping("/searchUser")
	public String searchUser(Model model,HttpServletRequest request) {
		String name=request.getParameter("name");
		model.addAttribute("users", userDao.getUsers(name));
		model.addAttribute("name", name);
		return "users";
	}

}
