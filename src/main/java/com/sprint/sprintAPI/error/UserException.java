package com.sprint.sprintAPI.error;

public class UserException extends RuntimeException {
    private static final UserException USER_NOT_FOUND = new UserException("Utilisateur introuvable.");
    private static final UserException INVALID_USER = new UserException("Utilisateur invalide.");

    public UserException() {
        super();
    }

    private UserException(String message) {
        super(message);
    }

    public static UserException userNotFound() {
        return USER_NOT_FOUND;
    }

    public static UserException invalidUser() {
        return INVALID_USER;
    }
}
