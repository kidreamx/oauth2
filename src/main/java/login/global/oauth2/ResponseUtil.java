package login.global.oauth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import login.oauth2.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class ResponseUtil {
    private final ObjectMapper objectMapper;

    public void setSuccessResponse(HttpServletResponse response, UserResponseDto userResponseDto) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String message = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userResponseDto);
        response.getWriter().println(message);
        response.sendRedirect("/");
    }
}