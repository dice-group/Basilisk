package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.services.HooksServices.GitHooksService;
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

/**
 * @author Fabian Rensing
 */

@RunWith(SpringRunner.class)
@WebMvcTest(GitHooksController.class)
@TestPropertySource("classpath:application-integrationtest.properties")
public class GitHooksControllerRequestTest {

    private final String jsonBody = "{\"repo_name\": \"tentris\", \"repo_owner\": \"dice-group\"}";
    private final GitRepo repo = new GitRepo();
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private GitHooksService gitHooksService;
    @SpyBean
    private ModelMapper modelMapper;
    @InjectMocks
    private GitHooksController gitHooksController;

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
            when(this.gitHooksService.addGitRepoForRelease(any())).thenReturn(this.repo);
            mockMvc.perform(post("/hooks/git/release")
                            .content(jsonBody)
                            .contentType("application/json"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"repo_id\":1,\"repo_name\":\"tentris\",\"repo_owner\":\"dice-group\",\"is_private\":false,\"oAuth_token\":null,\"branch_name\":null}"));
            verify(this.gitHooksService).addGitRepoForRelease(any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}