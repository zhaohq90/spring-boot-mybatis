package in.aprilfish;

import in.aprilfish.mapper.User;
import in.aprilfish.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Spy和 @SpyBean的区别， @Mock 和 @MockBean的区别
 * spy和mock生成的对象不受spring管理
 * SpyBean和MockBean生成的对象受spring管理，相当于自动替换对应类型bean的注入，比如@Autowired等注入
 * 对于未指定mock的方法，spy默认会调用真实的方法，有返回值的返回真实的返回值，而mock默认不执行，有返回值的，默认返回null
 */
public class ControllerTest extends AbstractContextControllerTest {

    @MockBean
    private UserService userService;

    @Test
    public void index() throws Exception{
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/")
                .header("Authorization","xxx")
                .param("q","1"))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        System.out.println(content);
    }

    @Test
    public void user() throws Exception {
        User user=new User();
        user.setId(1L);
        user.setName("may");

        Mockito.when(userService.findById(anyInt())).thenReturn(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("may"))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        System.out.println(content);
    }

}
