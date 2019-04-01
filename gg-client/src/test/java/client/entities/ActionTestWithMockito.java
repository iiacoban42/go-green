package client.entities;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class ActionTestWithMockito {

    private Action action = mock(Action.class);
    private Action action2 = new Action(15 , "transport" , "user" , 10);
    private Action meal = new Action();

    @Test
    public void testGetIdCalls() {
        action.getId();
        verify(action).getId();
        verify(action , times(1)).getId();
        Mockito.verifyNoMoreInteractions(action);
        assertEquals(15 , action2.getId());
    }

    @Test
    public void testGetScoreCalls() {
        action.getScore();
        verify(action).getScore();
        verify(action , times(1)).getScore();
        Mockito.verifyNoMoreInteractions(action);
        assertEquals(10 , action2.getScore());
    }

    @Test
    public void testGetUserCalls() {
        action.getUser();
        verify(action).getUser();
        verify(action , times(1)).getUser();
        Mockito.verifyNoMoreInteractions(action);
        assertEquals("user" , action2.getUser());

    }


    @Test
    public void testGetActionNameCalls() {
        action.getActionName();
        verify(action).getActionName();
        verify(action , times(1)).getActionName();
        Mockito.verifyNoMoreInteractions(action);
        assertEquals("transport" , action2.getActionName());
    }

    @Test
    public void testSetIdCalls() {
        action.setId(Mockito.anyLong());
        verify(action).setId(Mockito.anyLong());
        verify(action , times(1)).setId(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(action);
    }

    @Test
    public void testSetScoreCalls() {
        action.setScore(Mockito.anyInt());
        verify(action).setScore(Mockito.anyInt());
        verify(action , times(1)).setScore(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(action);
    }

    @Test
    public void testSetUserCalls() {
        action.setActionName(Mockito.anyString());
        verify(action).setActionName(Mockito.anyString());
        verify(action , times(1)).setActionName(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(action);
    }

    @Test
    public void testSetActionNameCalls() {
        action.setUser(Mockito.anyString());
        verify(action).setUser(Mockito.anyString());
        verify(action , times(1)).setUser(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(action);
    }

    @Test
    public void testSetters() {
        meal.setActionName("meal");
        meal.setUser("user");
        meal.setId(150);
        meal.setScore(50);

        assertEquals("meal" , meal.getActionName());
        assertEquals("user" , meal.getUser());
        assertEquals(150  , meal.getId());
        assertEquals(50 , meal.getScore());
    }



}
