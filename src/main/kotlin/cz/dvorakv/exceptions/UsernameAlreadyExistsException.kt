package cz.dvorakv.exceptions

class UsernameAlreadyExistsException(
    username: String
) : RuntimeException("Username '$username' already exists")

