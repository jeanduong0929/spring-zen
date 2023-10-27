package com.jean.zen.utils.custom_exceptions;

public class ResourceConflict extends RuntimeException {
  public ResourceConflict(String message) { super(message); }
}
