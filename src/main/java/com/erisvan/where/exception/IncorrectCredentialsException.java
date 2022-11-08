package com.erisvan.where.exception;

public class IncorrectCredentialsException extends RuntimeException {
  public IncorrectCredentialsException() {
    super("Incorrect Credentials.");
  }
}
