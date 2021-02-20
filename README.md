# lazy-tester

Latest result:

```
package com.github.lazy.tester.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BestEverServiceTest {

    @Mock
    IdValidator idValidator;
    @Mock
    ContactService contactService;
    @InjectMocks
    BestEverService bestEverService;

    @Test
    void shouldJustDoIt()
        throws Exception
    {
    }

    @Test
    void shouldGetBestNumber()
        throws Exception
    {
        Mockito.mock(contactService.getEmail()).thenReturn("some value to return");
        Mockito.mock(idValidator.beautify()).thenReturn("some value to return");
    }

}
```