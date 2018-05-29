package sda.pl.repository;

import junit.framework.Assert;
import org.junit.Test;
import sda.pl.domain.User;

import java.beans.Transient;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void findByEmailAndPassword() {
        Optional<User> user = UserRepository.findByEmailAndPassword("brian@mail.com","imbrian");
        Assert.assertTrue(user.isPresent());
    }
    @Test
    public void findUserById(){
        Optional<User>user=UserRepository.findUserById(1L);
        org.junit.Assert.assertTrue(user.isPresent());
    }

}
