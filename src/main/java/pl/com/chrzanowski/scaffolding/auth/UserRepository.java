package pl.com.chrzanowski.scaffolding.auth;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
