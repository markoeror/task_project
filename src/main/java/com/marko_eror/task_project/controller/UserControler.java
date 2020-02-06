package com.marko_eror.task_project.controller;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserControler {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	private final UserService userService;
	@Autowired
	public UserControler(UserService userService) {
		this.userService = userService;
	}

	// Test method for checking USER authorisation
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	// Test method for checking ADMIN authorisation
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}

	// Method for returning User details
	@PostMapping("/userDetails")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUserDataByPassword(@RequestBody UserDTO user) {
		try {
			UserDTO userDTO = new UserDTO();
			userDTO =  userService.getUserByUsername(user.getUsername());
			logger.debug("User details retrieved.");
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while retrieving User details");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	// Method for returning all Users with role USER
	@GetMapping("/getAllUsers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Set<UserDTO>> getAllUsers() {
		try {

			Set<UserDTO> userDTOS=  userService.getAllUsersWithRoleUSER();
			logger.debug("User details retrieved.");
			return new ResponseEntity<>(userDTOS, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while retrieving User details");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}