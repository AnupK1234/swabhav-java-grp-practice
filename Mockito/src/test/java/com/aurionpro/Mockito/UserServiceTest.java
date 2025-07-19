package com.aurionpro.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

	@Mock
	private UserRepository mockRepo;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this); // Initializes mocks
	}

	@Test
	void testGetUserName_WhenUserExists() {
		// Arrange
		User user = new User(1, "Anup");
		when(mockRepo.findById(1)).thenReturn(user);

		// Act
		String name = userService.getUserName(1);

		// Assert
		assertEquals("Anup", name);
		verify(mockRepo).findById(1); // Verifies the method was called
	}

	@Test
	void testGetUserName_WhenUserDoesNotExist() {
		// Arrange
		when(mockRepo.findById(2)).thenReturn(null);

		// Act
		String name = userService.getUserName(2);

		// Assert
		assertEquals("Unknown", name);
		verify(mockRepo).findById(2);
	}
}
