package client.entities;

import client.entities.LoginCredentials;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoginCredentialsMockitoTest {


     private LoginCredentials loginCredentials = mock(LoginCredentials.class);




    @Test
    public void testCallsGetPassword(){

        loginCredentials.getPassword();

        verify(loginCredentials).getPassword();
        verify(loginCredentials , times(0)).setPassword(Mockito.anyString());
        verify(loginCredentials , times(1)).getPassword();
        Mockito.verifyNoMoreInteractions(loginCredentials);

    }

    @Test
    public void testCallsSetUsername(){

        loginCredentials.setUsername(Mockito.anyString());


        verify(loginCredentials).setUsername(Mockito.anyString());
        verify(loginCredentials , times(1)).setUsername(Mockito.anyString());
        verify(loginCredentials , times(0)).getUsername();
        Mockito.verifyNoMoreInteractions(loginCredentials);

    }







}
