package org.dicegroup.basilisk.jobsManagingService.web.controllers;

import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepo;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepoType;
import org.dicegroup.basilisk.jobsManagingService.services.repo.GitRepoService;
import org.dicegroup.basilisk.jobsManagingService.web.controllers.repo.GitRepoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GitRepoController.class)
@TestPropertySource("classpath:application-integrationtest.yml")
public class GitRepoControllerRequestTest {

    private final String jsonBody = "{\"repo_name\": \"tentris\", \"repo_owner\": \"dice-group\"}";
    private final GitRepo repo = new GitRepo();
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private GitRepoService gitRepoService;
    @SpyBean
    private ModelMapper modelMapper;
    @InjectMocks
    private GitRepoController gitRepoController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        this.repo.setId(1L);
        this.repo.setRepoName("tentris");
        this.repo.setRepoOwner("dice-group");
    }

    @Test
    public void requestReleaseTest() {
        try {
            when(this.gitRepoService.addRepo(any(), GitRepoType.RELEASE)).thenReturn(this.repo);
            mockMvc.perform(post("/hooks/git/release")
                            .content(jsonBody)
                            .contentType("application/json"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"repo_id\":1,\"repo_name\":\"tentris\",\"repo_owner\":\"dice-group\",\"is_private\":false,\"oAuth_token\":null,\"branch_name\":null}"));
            verify(this.gitRepoService).addRepo(any(), GitRepoType.RELEASE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
