package tn.esprit.rh.achat.test;


import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.ISecteurActiviteService;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class SecteurActiviteServiceImpmockTest {


    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    ISecteurActiviteService secteurActiviteService = new SecteurActiviteServiceImpl();






    SecteurActivite secteurActivite = new SecteurActivite("secteurActiviteTest","secteurActiviteTest");
    List<SecteurActivite> listSecteurActivites = new ArrayList<SecteurActivite>() {
        {
            add(new SecteurActivite("secteurActiviteTest", "secteurActiviteTest"));
            add(new SecteurActivite("secteurActiviteTest", "secteurActiviteTest"));
        }
    };


    @Test
    void testRetrieveProduitByid() {

        when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(secteurActivite));
        SecteurActivite secteurActiviteq = secteurActiviteService.retrieveSecteurActivite(1L);

        System.out.println(secteurActiviteq);
        Assertions.assertNotNull(secteurActiviteq);
    }
    @Test
    void testRetrieveAllProduit() {

        List<SecteurActivite> secteurActivites = new ArrayList();
        secteurActivites.add(new SecteurActivite());
        when(secteurActiviteRepository.findAll()).thenReturn(secteurActivites);
        List<SecteurActivite> expected = secteurActiviteService.retrieveAllSecteurActivite();
        Assertions.assertEquals(expected, secteurActivites);
        verify(secteurActiviteRepository).findAll();

    }



    @Test
    void testCreateNewObject() {
        SecteurActivite obj = new SecteurActivite("secteurActiviteTest", "secteurActiviteTest");


        when(secteurActiviteRepository.save(isA(SecteurActivite.class))).thenAnswer(invocation -> (SecteurActivite) invocation.getArguments()[0]);
        SecteurActivite returnedObj = secteurActiviteService.addSecteurActivite(obj);
        ArgumentCaptor<SecteurActivite> savedObjectArgument = ArgumentCaptor.forClass(SecteurActivite.class);
        verify(secteurActiviteRepository, times(1)).save(savedObjectArgument.capture());
        verifyNoMoreInteractions(secteurActiviteRepository);

        SecteurActivite savedRestObject = savedObjectArgument.getValue();
        Assertions.assertNotNull(savedRestObject);

    }

    @Test
    void testDeleteObject() {
        SecteurActivite produite = new SecteurActivite();
        produite.setLibelleSecteurActivite("new test");
        produite.setIdSecteurActivite(1L);
        when(secteurActiviteRepository.findById(produite.getIdSecteurActivite())).thenReturn(Optional.of(produite));
        SecteurActivite secteurActiviteq = secteurActiviteService.retrieveSecteurActivite(1L);
        secteurActiviteService.deleteSecteurActivite(secteurActiviteq.getIdSecteurActivite());
        verify(secteurActiviteRepository).deleteById(secteurActiviteq.getIdSecteurActivite());
    }

}