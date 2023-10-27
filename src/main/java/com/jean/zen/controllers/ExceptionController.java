package com.jean.zen.controllers;

import com.jean.zen.utils.custom_exceptions.BadRequestException;
import com.jean.zen.utils.custom_exceptions.InvalidAuthException;
import com.jean.zen.utils.custom_exceptions.NotFoundException;
import com.jean.zen.utils.custom_exceptions.ResourceConflictException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, Object>>
  handleNotFoundException(NotFoundException e) {
    Map<String, Object> response = new HashMap<>();
    response.put("message: ", e.getMessage());
    response.put("timestamp: ", new Date());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<Map<String, Object>>
  handleResourceConflicException(ResourceConflictException e) {
    Map<String, Object> response = new HashMap<>();
    response.put("message: ", e.getMessage());
    response.put("timestamp: ", new Date());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Map<String, Object>>
  handleBadRequestException(BadRequestException e) {
    Map<String, Object> response = new HashMap<>();
    response.put("message: ", e.getMessage());
    response.put("timestamp: ", new Date());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(InvalidAuthException.class)
  public ResponseEntity<Map<String, Object>>
  handleInvalidAuthException(InvalidAuthException e) {
    Map<String, Object> response = new HashMap<>();
    response.put("message: ", e.getMessage());
    response.put("timestamp: ", new Date());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }
}
