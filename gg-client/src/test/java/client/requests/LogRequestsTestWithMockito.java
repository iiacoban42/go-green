package client.requests;

import client.entities.Action;
import client.entities.ActionList;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static client.requests.LoginRequests.sendLoginCredentials;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class LogRequestsTestWithMockito {
    @Before
    public void setUp() throws JsonProcessingException {

        sendLoginCredentials("userForTests" , "test");

    }

    private LogRequests mockLogRequests = mock(LogRequests.class);
    private  LogRequests logRequests = new LogRequests();



    @Test
    public void testGetAllActionsFromTheServer() {

        mockLogRequests.getAllActionsFromTheServer();
        verify(mockLogRequests).getAllActionsFromTheServer();
        verify(mockLogRequests , times(1)).getAllActionsFromTheServer();
        Mockito.verifyNoMoreInteractions(mockLogRequests);
    }

    @Test
    public void testSetActionList() {

        List<Action> actionList = new ArrayList<>();
        logRequests.setActionList(actionList);

        assertEquals(actionList  , logRequests.getActionList());

    }

    @Test
    public void testGetActionList() {

        mockLogRequests.getActionList();
        verify(mockLogRequests).getActionList();
        verify(mockLogRequests , times(1)).getActionList();
        Mockito.verifyNoMoreInteractions(mockLogRequests);
    }


    @Test
    public void testConverter() throws JsonProcessingException {
        List<Action> actionList = new ArrayList<>();
        Action action2 = new Action(15 , "transport" , "user" , 10);
        actionList.add(action2);

        logRequests.setActionList(actionList);
        ActionList result = logRequests.converter();
        ActionList test = new ActionList();
        test.addAction(action2);

       assertEquals(ActionList.class, result.getClass());
       assertEquals(test.getActionList().toString() , result.getActionList().toString());
    }

    @Test
    public void testGetAllActionsFromTheServer_successful() {

        String response =  logRequests.getAllActionsFromTheServer();

        assertEquals(String.class , response.getClass());

    }

    @Test
    public void testConverter_EmptyActionList() throws JsonProcessingException {

        logRequests.setActionList(new ArrayList());
        ActionList actionList = logRequests.converter();
        ActionList expected = new ActionList();

        assertEquals(expected.getActionList().toString() , actionList.getActionList().toString() );
    }


    @Test(expected = Exception.class)
    public  void testConverterException() throws JsonProcessingException {

        logRequests.setActionList(null);
        logRequests.converter();

    }

}
