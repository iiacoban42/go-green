package client.requests;

import client.entities.FriendScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestClientResponseException;

import static client.requests.FriendRequests.addFriendRequest;
import static client.requests.LoginRequests.sendLoginCredentials;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class FriendRequestsTestWithMockito {

    @Before
    public void setUp() throws JsonProcessingException {

        sendLoginCredentials("userForTests" , "test");

    }

    private FriendRequests mockFriendRequests = mock(FriendRequests.class);
    private FriendScore friendScore = new FriendScore("user" , 50);
    private FriendRequests friendRequests = new FriendRequests(friendScore);


    @Test(expected = RestClientResponseException.class)
    public void testAddFriendRequests_Exception() {
        Mockito.when(mockFriendRequests.getScoreAdNameOfFriend())
                .thenThrow(new RestClientResponseException("" , 403 ,"", null, null , null));
        mockFriendRequests.getScoreAdNameOfFriend();
    }

    @Test
    public void testGetScoreAndNameOfFriend() {
        mockFriendRequests.getScoreAdNameOfFriend();
        verify(mockFriendRequests).getScoreAdNameOfFriend();
        verify(mockFriendRequests , times(1)).getScoreAdNameOfFriend();
        verifyNoMoreInteractions(mockFriendRequests);
    }

    @Test
    public  void testSetFriendScore(){
        mockFriendRequests.setFriendScore(friendScore);
        verify(mockFriendRequests).setFriendScore(friendScore);
        verify(mockFriendRequests , times(1)).setFriendScore(friendScore);
        verifyNoMoreInteractions(mockFriendRequests);

    }

    @Test
    public  void testGetFriendScore(){
        friendRequests.setFriendScore(friendScore);
        assertEquals(friendScore , friendRequests.getFriendScore());
        mockFriendRequests.getFriendScore();
        verify(mockFriendRequests).getFriendScore();
        verify(mockFriendRequests , times(1)).getFriendScore();
        verifyNoMoreInteractions(mockFriendRequests);

    }

    @Test
    public void testGetFriendScore_unsuccessful() {

        FriendRequests friendRequestsTest =  new FriendRequests();
        friendRequestsTest.setFriendScore(new FriendScore("user" , 60));
        assertNotEquals(friendRequestsTest.getFriendScore() ,friendRequests.getFriendScore());
    }


    @Test
    public void testAddFriendRequest() throws JsonProcessingException {
       String response =  addFriendRequest("userForTests2");
       assertEquals("200 OK", response);

    }

    @Test
    public void testGetScoreAndNameOfFriend_successful() throws JsonProcessingException {
        addFriendRequest("userForTests2");
        String response = friendRequests.getScoreAdNameOfFriend();
        assertEquals("200 OK"  , response);
    }




}
