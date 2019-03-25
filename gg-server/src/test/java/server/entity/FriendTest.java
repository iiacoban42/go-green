package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendTest {

    @Test
    public void getName() {
        Friend friend = new Friend("John");
        assertEquals("John", friend.getName());
    }

    @Test
    public void setName() {
        Friend friend = new Friend("Micheal");
        friend.setName("Natalie");

        assertEquals("Natalie", friend.getName());
    }
}