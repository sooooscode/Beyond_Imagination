package com.beyondImagination.bookChatter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  private String username;
  private boolean isActive;

  // getters and setters
}
