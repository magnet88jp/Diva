package controllers;

import models.*;

class Security extends Secure.Security {

  static boolean authentify(String username, String password) {
    return User.connect(username, password) != null;
  }
}
