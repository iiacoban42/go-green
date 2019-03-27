package client.entities;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FriendTest {

    @Test
    public void testSetName(){

        Friend friend = new Friend();
        friend.setName("user");

        assertEquals("user" , friend.getName());
    }

    @Test
    public void testGetName() {
        Friend friend = new Friend("user");

        assertEquals("user" , friend.getName());
    }
}
